package Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
public class Todos {
    private int id;
    private int user_id;
    private String title;
    private String due_on;
    private String status;

    public Todos()
    {}

    public Todos(int user_id, String title, String due_on, String status) {
        this.user_id = user_id;
        this.title = title;
        this.due_on = due_on;
        this.status = status;
    }
}
