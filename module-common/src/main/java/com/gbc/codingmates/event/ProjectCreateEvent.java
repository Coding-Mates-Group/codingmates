package com.gbc.codingmates.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

//@RequiredArgsConstructor
@Getter
public class ProjectCreateEvent extends ApplicationEvent {

//    private final ApplicationEventPublisher applicationEventPublisher;

    private Long memberId;

    private List<Long> skillIds = new ArrayList<>();

    private String title;

    private String content;

    private String recruitmentStatus;

    private String userAlias;

    protected ProjectCreateEvent(Object source){
        super(source);
    }

    public ProjectCreateEvent(Object source, Long memberId, List<Long> skillIds, String title, String content,
                              String recruitmentStatus, String userAlias){
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
        this.title = title;
        this.content = content;
        this.recruitmentStatus = recruitmentStatus;
        this.userAlias = userAlias;
    }

}
