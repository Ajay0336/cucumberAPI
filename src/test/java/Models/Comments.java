package Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
public class Comments {

    private int id;
    private int post_id;
    private String name;
    private String email;
    private String body;

    public Comments()
    {}

    public Comments(int post_id, String name, String email, String body) {
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
