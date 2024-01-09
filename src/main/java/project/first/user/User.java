package project.first.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.first.comment.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserStance stance;

    @OneToMany(mappedBy = "user") // 양방향 연관관계 매핑
    private List<Comment> comments = new ArrayList<>();
}
