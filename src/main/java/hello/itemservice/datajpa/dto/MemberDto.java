package hello.itemservice.datajpa.dto;

import hello.itemservice.datajpa.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;
    private int age;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public MemberDto(Member m) {
        this.id = m.getId();
        this.username = m.getUsername();

    }
}
