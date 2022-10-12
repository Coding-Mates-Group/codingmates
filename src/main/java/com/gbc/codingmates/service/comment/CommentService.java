package com.gbc.codingmates.service.comment;

import com.gbc.codingmates.domain.comment.Comment;
import com.gbc.codingmates.domain.comment.CommentRepository;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.comment.CommentRequestDto;
import com.gbc.codingmates.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<Long> saveComment(String username, Long id, CommentRequestDto commentRequestDto){
        Member member = memberRepository.findMemberByUsername(username).get();
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such post found" + id));
        commentRequestDto.setMember(member);
        commentRequestDto.setProject(project);
        Comment comment = commentRequestDto.toEntity();
        commentRepository.save(comment);
        return ResponseEntity.ok(comment.getId());
    }
}
