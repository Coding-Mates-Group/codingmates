package com.gbc.codingmates.listener;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import com.gbc.codingmates.domain.skill.MemberSkillRepository;
import com.gbc.codingmates.domain.skill.Skill;
import com.gbc.codingmates.domain.skill.SkillRepository;
import com.gbc.codingmates.event.MemberCreateEvent;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberCreateEventListenerTest {

    private MemberCreateEventListener listener;
    private MemberCreateEvent event;

    @Mock
    private MemberSkillRepository memberSkillRepository;

    @Mock
    private SkillRepository skillRepository;

    @BeforeEach
    public void init() {
        listener = new MemberCreateEventListener(skillRepository, memberSkillRepository);
        event = new MemberCreateEvent("", 1L, Arrays.asList(1L, 2L));
    }

    @Test
    public void memberSkillAdd() {
        //given
        when(skillRepository.findAllById(event.getSkillIds())).thenReturn(
            Arrays.asList(new Skill("SPRING"), new Skill("JAVA")));

        when(memberSkillRepository.saveAll(anyCollection())).thenReturn(null);

        //when
        listener.addSkillToMember(event);
    }

    @Test
    public void skillNotExist() {
        //given
        when(skillRepository.findAllById(event.getSkillIds())).thenReturn(
            Arrays.asList(new Skill("SPRING")));

        //when && then
        assertThatThrownBy(
            () -> listener.addSkillToMember(event)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("invalid input of member skills");
    }
}