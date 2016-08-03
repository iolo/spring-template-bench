package kr.iolo.toybox.springtemplatebench;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author iolo
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
