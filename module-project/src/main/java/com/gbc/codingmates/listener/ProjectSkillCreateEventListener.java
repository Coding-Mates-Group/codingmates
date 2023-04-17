package com.gbc.codingmates.listener;

import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.event.MemberCreateEvent;
import com.gbc.codingmates.event.ProjectSkillCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProjectSkillCreateEventListener {

    private final ProjectRepository projectRepository;

    @EventListener
    @Transactional
    public void add projectSkillToProject(ProjectSkillCreateEvent projectSkillCreateEvent){
        final Long projectSkillid = projectSkillCreateEvent.getProjectSkillid();
        final String skillName = projectSkillCreateEvent.getSkillName();
    }
}
