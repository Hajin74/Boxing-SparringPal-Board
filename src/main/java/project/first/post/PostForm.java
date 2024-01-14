package project.first.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

    private String title;
    private String content;
    private PostStatus status;

}
