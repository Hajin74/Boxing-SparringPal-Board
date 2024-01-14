package project.first.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setStance(userRequestDTO.getStance());
        user.setName(userRequestDTO.getName());
        userService.create(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findOne(name);
    }

}
