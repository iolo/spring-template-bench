package kr.iolo.toybox.springtemplatebench;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author iolo
 */
@Component
public class DemoService {
    public List<Comment> getComments() {
        return Arrays.asList(
                new Comment(1L, "author1", "content1"),
                new Comment(2L, "author2", "content2"),
                new Comment(3L, "author3", "content3"));
    }
}
