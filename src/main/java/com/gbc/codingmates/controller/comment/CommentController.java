package com.gbc.codingmates.controller.comment;

//import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.comment.CommentRequestDto;
import com.gbc.codingmates.dto.comment.CommentResponseDto;
import com.gbc.codingmates.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/project")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    public ResponseEntity saveComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentService.saveComment(commentRequestDto.getMember().getUsername(), id, commentRequestDto));
    }
}
