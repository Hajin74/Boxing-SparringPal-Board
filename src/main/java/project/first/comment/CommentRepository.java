package project.first.comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.first.post.Post;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepository {

    private final EntityManager entityManager;

    public void save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }

    public Comment findOne(Long commentId) {
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.id = :commentId", Comment.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

    public List<Comment> findByPostId(Long postId) {
        return entityManager.createQuery("select c from Comment c where c.post.id = :postId", Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    public void delete(Comment comment) {
        entityManager.remove(comment);
    }

}
