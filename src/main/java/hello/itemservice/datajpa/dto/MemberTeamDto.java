package hello.itemservice.datajpa.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private Long memberId;
    private String username;
    private String teamName;
    private int age;
    private Long teamId;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, String teamName, int age, Long teamId) {
        this.memberId = memberId;
        this.username = username;
        this.teamName = teamName;
        this.age = age;
        this.teamId = teamId;
    }
}
