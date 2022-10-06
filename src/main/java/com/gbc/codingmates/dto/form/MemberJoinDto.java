package com.gbc.codingmates.dto.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberJoinDto {

    @NotEmpty(message = "user alias must not empty")
    @Length(min = 4, max = 20, message = "user alias length must be 4 ~ 20 words")
    @Pattern(regexp = "^[a-z0-9]*", message = "user alias can be made by numbers and lower spelling")
    private String userAlias;

    @NotEmpty(message = "token must not empty")
    private String token;
    private List<Long> skillIds = new ArrayList<>();
}
