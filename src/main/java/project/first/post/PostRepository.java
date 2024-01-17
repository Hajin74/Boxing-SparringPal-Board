package project.first.post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    public List<Post> findByBoardId(Long boardId) {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.board.id = :boardId", Post.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Post findById(Long postId) {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.id = :postId", Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public void update(Post post) {
        int result = entityManager.createQuery("UPDATE Post p SET p.title = :title, p.content = :content WHERE p.id = :id")
                .setParameter("title", post.getTitle())
                .setParameter("content", post.getContent())
                .setParameter("id", post.getId())
                .executeUpdate();
        log.info("<< update 결과 : " + result + " >>");
    }

    public void delete(Post post) {
//        entityManager.createQuery("Delete From Post p WHERE p.id = :id")
//                .setParameter("id", id)
//                .executeUpdate();
        entityManager.remove(post);
    }

}
