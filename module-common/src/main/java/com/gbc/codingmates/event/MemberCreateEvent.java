package com.gbc.codingmates.event;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MemberCreateEvent extends ApplicationEvent {

    private Long memberId;
    private List<Long> skillIds = new ArrayList<>();

    protected MemberCreateEvent(Object source) {
        super(source);
    }

    public MemberCreateEvent(Object source, Long memberId, List<Long> skillIds) {
        super(source);
        try {
            String.valueOf(source);
        }catch (Exception e){
            throw new IllegalArgumentException(
                "Illegal Event name. It must be changed to string type!");
        }
        this.memberId = memberId;
        if (!isEmpty(skillIds)) {
            this.skillIds = skillIds;
        }
    }

    public String getEventName(){
        return (String) this.source;
    }
}
