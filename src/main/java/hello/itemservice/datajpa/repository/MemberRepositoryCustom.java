package hello.itemservice.datajpa.repository;

import hello.itemservice.datajpa.dto.MemberSearchCond;
import hello.itemservice.datajpa.dto.MemberTeamDto;
import hello.itemservice.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();

    List<MemberTeamDto> search(MemberSearchCond condition);
}
