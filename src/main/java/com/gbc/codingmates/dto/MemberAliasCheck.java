package com.gbc.codingmates.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class MemberAliasCheck {

    @NotEmpty(message = "user alias must not empty")
    @Length(min = 4, max = 20, message = "user alias length must be 4 ~ 20 words")
    @Pattern(regexp = "^[a-z0-9]*", message = "user alias can be made by numbers and lower spelling")
    private String userAlias;
}
