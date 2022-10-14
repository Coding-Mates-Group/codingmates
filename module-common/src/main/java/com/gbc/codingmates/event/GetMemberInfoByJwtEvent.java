package com.gbc.codingmates.event;

import com.gbc.codingmates.dto.member.MemberDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetMemberInfoByJwtEvent extends ApplicationEvent {

    private String jwtToken;
    private MemberDto memberDto;

    protected GetMemberInfoByJwtEvent(Object source) {
        super(source);
    }

    public GetMemberInfoByJwtEvent(String jwtToken) {
        super("GET_MEMBER_INFO_BY_JWT");
        this.jwtToken = jwtToken;
    }

    public void saveMemberInfo(MemberDto memberDto){
        this.memberDto = memberDto;
    }
}
