package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.skill.Skill;
import com.gbc.codingmates.dto.ProjectSkillDto;
import com.gbc.codingmates.event.ProjectSkillCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectSkillService {
    private final ApplicationEventPublisher eventPublisher;

    public ResponseEntity createProjectSkill(ProjectSkillDto projectSkillDto){
        Long projectSkillId = projectSkillDto.getProjectSkillId();
//        Skill skill = projectSkillDto.getSkill();
        String skillName = projectSkillDto.getSkillName();

        ProjectSkillCreateEvent projectSkillCreateEvent = new ProjectSkillCreateEvent("PROJECT_SKILL_CREATE", projectSkillId, skillName);
        eventPublisher.publishEvent(projectSkillCreateEvent);
        return null;
    }
}
