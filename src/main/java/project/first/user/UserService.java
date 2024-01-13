package project.first.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    @Transactional
    public void create(User user) {
        userRepository.save(user);
    }

    // 유저 전체 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 유저 개별 조회 - 이름 기반
    public User findOne(String name) {
        return userRepository.findByName(name);
    }

}
