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

    private Long projectId;

    protected ProjectCreateEvent(Object source){
        super(source);
    }

    public ProjectCreateEvent(Object source, Long projectId){
        super(source);
        try {
            String.valueOf(source);
        }catch (Exception e){
            throw new IllegalArgumentException(
                    "Illegal Event name. It must be changed to string type!");
        }

        this.projectId = projectId;

    }

}
