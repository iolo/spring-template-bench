package kr.iolo.toybox.springtemplatebench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author iolo
 */
@Component
public class DemoService {

    private final CommentRepository commentRepository;

    @Autowired
    public DemoService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
}
