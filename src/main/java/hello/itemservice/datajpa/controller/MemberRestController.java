package hello.itemservice.datajpa.controller;


import hello.itemservice.datajpa.dto.MemberSearchCond;
import hello.itemservice.datajpa.dto.MemberTeamDto;
import hello.itemservice.datajpa.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    MemberJpaRepository memberJpaRepository;

    @GetMapping("v2/members")
    public List<MemberTeamDto> search(MemberSearchCond condition) {
        return memberJpaRepository.search(condition);
    }
}
