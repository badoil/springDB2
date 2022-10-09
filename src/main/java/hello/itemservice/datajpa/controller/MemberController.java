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

    @GetMapping("/membersA")
    public Page<Member> listA(@PageableDefault(size = 10) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    @GetMapping("/membersB")
    public Page<MemberDto> listB(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> pageDto = page.map(MemberDto::new);
        return pageDto;
    }

    @GetMapping("/membersC")
    public Page<MemberDto> listC(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDto::new);
    }
}
