package project.first.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setStance(userRequestDTO.getStance());
        userService.create(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
