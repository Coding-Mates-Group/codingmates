package com.gbc.codingmates.dto.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberJoinDto {

    @NotEmpty(message = "user alias must not empty")
    private String userAlias;
    @NotEmpty(message = "token must not empty")
    private String token;
    private List<Long> skillIds = new ArrayList<>();
}
