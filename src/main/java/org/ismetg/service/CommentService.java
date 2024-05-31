package org.ismetg.service;

import org.ismetg.repository.CommentRepository;

public class CommentService {
    CommentRepository commentRepository;

    public CommentService()
    {
        this.commentRepository = new CommentRepository();
    }
}
