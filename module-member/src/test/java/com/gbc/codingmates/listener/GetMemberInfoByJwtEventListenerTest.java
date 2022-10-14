package com.gbc.codingmates.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.domain.member.MemberStatus;
import com.gbc.codingmates.event.GetMemberInfoByJwtEvent;
import com.gbc.codingmates.jwt.TokenProvider;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetMemberInfoByJwtEventListenerTest {

    private GetMemberInfoByJwtEventListener listener;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void init() {
        listener = new GetMemberInfoByJwtEventListener(tokenProvider, memberRepository);
        member = new Member("sjh", "jhsg@naver.com", "asdf", MemberStatus.BASIC, null, "default");
    }

    @Test
    public void getMemberInfoByJwtToken() {
        String token = "TOKEN";
        GetMemberInfoByJwtEvent event = new GetMemberInfoByJwtEvent(token);

        when(tokenProvider.validateToken(token)).thenReturn(true);
        when(tokenProvider.getMemberIdByToken(token)).thenReturn(1L);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        listener.getMemberInfoByJwtToken(event);

        //then
        assertAll(
            () -> assertThat(event.getMemberDto().getUsername()).isEqualTo(member.getUsername()),
            () -> assertThat(event.getMemberDto().getMemberStatus()).isEqualTo(
                member.getMemberStatus().name())
        );
    }

    @Test
    public void tokenIsExpired() {
        //given
        String token = "TOKEN";
        GetMemberInfoByJwtEvent event = new GetMemberInfoByJwtEvent(token);
        when(tokenProvider.validateToken(token)).thenReturn(false);

        //when & then
        assertThatThrownBy(() -> listener.getMemberInfoByJwtToken(event))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("not valid jwt token is used");
    }

    @Test
    public void cannotFindMemberByJwtToken() {
        //given
        String token = "TOKEN";
        GetMemberInfoByJwtEvent event = new GetMemberInfoByJwtEvent(token);
        when(tokenProvider.validateToken(token)).thenReturn(true);
        when(tokenProvider.getMemberIdByToken(token)).thenReturn(1L);
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> listener.getMemberInfoByJwtToken(event))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("can not resolve member by jwt Token");
    }
}