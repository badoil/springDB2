package hello.itemservice.datajpa.repository;

import hello.itemservice.datajpa.dto.MemberSearchCond;
import hello.itemservice.datajpa.dto.MemberTeamDto;
import hello.itemservice.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();

    List<MemberTeamDto> search(MemberSearchCond condition);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCond condition, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCond condition, Pageable pageable);
}
