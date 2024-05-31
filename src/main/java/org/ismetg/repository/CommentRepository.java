package org.ismetg.repository;

import org.ismetg.entity.Comment;

public class CommentRepository extends RepositoryManager<Comment , Long> {
    public CommentRepository() {
        super(Comment.class);
    }
}
