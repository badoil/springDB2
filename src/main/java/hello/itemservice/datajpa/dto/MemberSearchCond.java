package hello.itemservice.datajpa.dto;

import lombok.Data;

@Data
public class MemberSearchCond {

    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}