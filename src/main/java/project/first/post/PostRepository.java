package project.first.post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager entityManager;

    public void save(Post post) {
        if (post.getId() == null) {
            entityManager.persist(post);
        } else {
            entityManager.merge(post);
        }
    }

    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM Post p", Post.class)
                .getResultList();
    }

    public List<Post> findByUser(String user) {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.user = :user", Post.class)
                .setParameter("user", user)
                .getResultList();
    }

}
