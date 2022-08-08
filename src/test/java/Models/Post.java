package Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
public class Post {
    private int id;
    private int user_id;
    private String title;
    private String body;

    public Post()
    {}
    public Post(int user_id, String title, String body) {
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }
}
