package com.gbc.codingmates.domain;

import com.gbc.codingmates.domain.comment.Comment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class CommentsManager {

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }
}
