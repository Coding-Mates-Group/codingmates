package com.gbc.codingmates.listener;

import com.gbc.codingmates.domain.skill.MemberSkill;
import com.gbc.codingmates.domain.skill.MemberSkillRepository;
import com.gbc.codingmates.domain.skill.Skill;
import com.gbc.codingmates.domain.skill.SkillRepository;
import com.gbc.codingmates.event.MemberCreateEvent;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberCreateEventListener {

    private final SkillRepository skillRepository;
    private final MemberSkillRepository memberSkillRepository;

    @EventListener
    @Transactional
    public void addSkillToMember(MemberCreateEvent memberCreateEvent) {
        final Long memberId = memberCreateEvent.getMemberId();
        final List<Long> skillIds = memberCreateEvent.getSkillIds();

        List<Skill> memberSkills =
            skillRepository.findAllById(skillIds);

        if (memberSkills.size() != skillIds.size()) {
            throw new IllegalArgumentException("invalid input of member skills");
        }

        memberSkillRepository.saveAll(
            memberSkills
                .stream()
                .map(skill -> new MemberSkill(memberId, skill))
                .collect(Collectors.toList())
        );
    }
}
