package com.gbc.codingmates.listener;

import com.gbc.codingmates.domain.member.RedisJwtToken;
import com.gbc.codingmates.domain.member.RedisJwtTokenRepository;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.jwt.TokenProvider;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.event.GetMemberInfoByJwtEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetMemberInfoByJwtEventListener {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final RedisJwtTokenRepository redisJwtTokenRepository;
    @EventListener
    @Transactional(readOnly = true)
    public void getMemberInfoByJwtToken(GetMemberInfoByJwtEvent getMemberInfoByJwtEvent) {
        final String jwtToken = getMemberInfoByJwtEvent.getJwtToken();

        if (!tokenProvider.validateToken(jwtToken)) {
            throw new IllegalArgumentException("not valid jwt token is used");
        }

        Optional<RedisJwtToken> redisCacheInfo = redisJwtTokenRepository.findById(jwtToken);
        if(redisCacheInfo.isPresent()){
            getMemberInfoByJwtEvent.saveMemberInfo(redisCacheInfo.get().getData());
            return;
        }
        Long memberId = tokenProvider.getMemberIdByToken(jwtToken);
        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException("can not resolve member by jwt Token")
            );

        getMemberInfoByJwtEvent.saveMemberInfo(Member.getMemberDto(member));
    }
}
