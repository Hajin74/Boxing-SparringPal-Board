package project.first.user;

import lombok.Data;

@Data
public class UserRequestDTO {

    private Long id;
    private String name;
    private UserStance stance;

}
