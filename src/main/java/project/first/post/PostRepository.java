package project.first.post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager entityManager;

    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM post p", Post.class)
                .getResultList();
    }

    public List<Post> findByUser(String user) {
        return entityManager.createQuery("SELECT p FROM post p WHERE p.user = :user", Post.class)
                .setParameter("user", user)
                .getResultList();
    }

}
