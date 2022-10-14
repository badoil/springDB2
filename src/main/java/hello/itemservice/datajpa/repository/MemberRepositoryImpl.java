package hello.itemservice.datajpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.datajpa.dto.MemberSearchCond;
import hello.itemservice.datajpa.dto.MemberTeamDto;
import hello.itemservice.datajpa.dto.QMemberTeamDto;
import hello.itemservice.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.itemservice.datajpa.entity.QMember.member;
import static hello.itemservice.datajpa.entity.QTeam.team;
import static org.springframework.util.ObjectUtils.isEmpty;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCond condition) {
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        team.name,
                        member.age,
                        team.id))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .fetch();
    }

    private BooleanExpression usernameEq(String username) {
        return isEmpty(username) ? null : member.username.eq(username);
    }
    private BooleanExpression teamNameEq(String teamName) {
        return isEmpty(teamName) ? null : team.name.eq(teamName);
    }
    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe == null ? null : member.age.goe(ageGoe);
    }
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe == null ? null : member.age.loe(ageLoe);
    }
}
