package hello.itemservice.datajpa.repository;

import hello.itemservice.datajpa.dto.MemberDto;
import hello.itemservice.datajpa.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom  {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);


    @Query("select new hello.itemservice.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();


    Page<Member> findByAge(int age, Pageable pageable);

    //이 옵션 없이 회원을 findById로 다시 조회하면 영속성 컨텍스트에 과거 값이 남아서 문제가 될 수 있다. 만약 다시 조회해야 하면 꼭 영속성 컨텍스트를 초기화 하자
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);


    // 연관된 엔티티를 한번에 조회하려면 페치 조인이 필요하다
    // n+1 문제 해결위한 페치조인
    // 그런데 jpql 써야 해서 귀찮을 수 있음
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // 스프링 데이터 JPA는 JPA가 제공하는 엔티티 그래프 기능을 편리하게 사용하게 도와준다.
    // 이 기능을 사용하면 JPQL 없이 페치 조인을 사용할 수 있다. (JPQL + 엔티티 그래프도 가능)

    //공통 메서드 오버라이드
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    //JPQL + 엔티티 그래프
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    //메서드 이름으로 쿼리에서 특히 편리하다.
    @EntityGraph(attributePaths = {"team"})
    List<Member> findByUsername(String username);


    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);
}
