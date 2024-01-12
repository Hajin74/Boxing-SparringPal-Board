package project.first.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 부분 조회 - 유저 기반
    public List<Post> findByUser(String user) {
        return postRepository.findByUser(user);
    }

    // 게시글 부분 조회 - 타이틀 기반
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

}
