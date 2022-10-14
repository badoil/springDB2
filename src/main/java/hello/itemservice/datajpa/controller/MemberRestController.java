package hello.itemservice.datajpa.controller;


import hello.itemservice.datajpa.dto.MemberSearchCond;
import hello.itemservice.datajpa.dto.MemberTeamDto;
import hello.itemservice.datajpa.repository.MemberJpaRepository;
import hello.itemservice.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    MemberJpaRepository memberJpaRepository;

    MemberRepository memberRepository;

    @GetMapping("v2/members")
    public List<MemberTeamDto> search(MemberSearchCond condition) {
        return memberJpaRepository.search(condition);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCond condition, Pageable pageable) {
        return memberRepository.searchPageComplex(condition, pageable);
    }
}
