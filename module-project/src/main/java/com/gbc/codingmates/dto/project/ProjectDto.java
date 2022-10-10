package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.member.MemberDto;
import java.sql.Blob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

    private String title;
    private String content;
    private Blob contentBig;
    private Long views;
    private String recruitmentStatus;
//    private ProjectMember member;
//    private Member member;

    @Builder
    public ProjectDto(Project project) {
        this.title = project.getTitle();
        this.content = project.getContent();
        this.contentBig = project.getContentBig();
        this.views = project.getViews();
        this.recruitmentStatus = project.getRecruitmentStatus();
//        this.member = new ProjectMember(project.getMember());
//        this.member = project.getMember();
    }

    public Project toEntity() {
        return Project.builder()
            .title(title)
            .content(content)
            .contentBig(contentBig)
            .views(views)
            .recruitmentStatus(recruitmentStatus)
            .build();
    }

    @Getter
    static class ProjectMember {

        private Long id;
        //needs to be replaced with Member's nickname, not username
        private String username;
        private String memberStatus;

        public ProjectMember(MemberDto member) {
            this.id = member.getMemberId();
            this.username = member.getUsername();
            this.memberStatus = member.getMemberStatus();
        }
    }
}
