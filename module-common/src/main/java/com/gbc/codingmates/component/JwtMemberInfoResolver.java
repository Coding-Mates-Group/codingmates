package com.gbc.codingmates.component;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.event.GetMemberInfoByJwtEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class JwtMemberInfoResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtMemberInfo.class) != null
            && parameter.getParameterType().equals(MemberDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String bearerToken = webRequest.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String jwtToken = bearerToken.substring(7);
            GetMemberInfoByJwtEvent event = new GetMemberInfoByJwtEvent(jwtToken);
            eventPublisher.publishEvent(event);
            return event.getMemberDto();
        }

        throw new IllegalArgumentException("can not transfer jwt to member");
    }
}
