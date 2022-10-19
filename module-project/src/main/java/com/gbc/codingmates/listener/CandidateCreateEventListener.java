package com.gbc.codingmates.listener;

import com.gbc.codingmates.domain.project.CustomProjectRepositoryImpl;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.event.MemberCreateEvent;
import com.gbc.codingmates.event.ProjectCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateCreateEventListener {

    private final ProjectRepository projectRepository;
    private final CustomProjectRepositoryImpl customProjectRepository;

    @EventListener
    @Transactional
    public void addMemberToProject(MemberCreateEvent memberCreateEvent, ProjectCreateEvent projectCreateEvent){
        final Long memberId = memberCreateEvent.getMemberId();
        final List<Long> skillIds = memberCreateEvent.getSkillIds();

        final Long projectId = projectCreateEvent.getProjectId();

        //user nickname? where is it
//        final String userAlias = MemberCreateEvent.getUserAlias();

        //it should be saving new Candidate with these fields from events
//        projectRepository.save(new Project(memberId, skillIds, projectId, title, content, recruitmentStatus));

    }
}
