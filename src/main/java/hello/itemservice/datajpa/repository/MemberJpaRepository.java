package hello.itemservice.datajpa.repository;


import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.datajpa.entity.Member;
import hello.itemservice.datajpa.entity.QMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static hello.itemservice.datajpa.entity.QMember.*;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // findAll 의 querydsl 버전
    public List<Member> findAllQuerydsl() {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByPages(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public int bulkAgePlus(int age) {
        return em.createQuery("update Member m set m.age = m.age+1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
