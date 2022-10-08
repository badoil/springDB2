package hello.itemservice.datajpa.controller;


import hello.itemservice.datajpa.dto.MemberDto;
import hello.itemservice.datajpa.entity.Member;
import hello.itemservice.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(@PageableDefault(size = 10) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    @GetMapping("/members")
    public Page<MemberDto> list2(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> pageDto = page.map(MemberDto::new);
        return pageDto;
    }

    @GetMapping("/members")
    public Page<MemberDto> list3(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDto::new);
    }
}
