package com.gbc.codingmates.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
public class ProjectSkillCreateEvent extends ApplicationEvent {
    private Long projectSkillid;
    private String skillName;

    protected ProjectSkillCreateEvent(Object source) {
        super(source);
    }

    public ProjectSkillCreateEvent(Object source, Long projectSkillid, String skillName) {
        super(source);
        try {
            String.valueOf(source);
        }catch (Exception e){
            throw new IllegalArgumentException(
                    "Illegal Event name. It must be changed to string type!");
        }
        this.projectSkillid = projectSkillid;
        this.skillName = skillName;
    }

    public String getEventName(){
        return (String) this.source;
    }
}
