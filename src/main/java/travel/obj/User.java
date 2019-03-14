package travel.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {//地点详情
    private Integer id;     //idprivate
    private String  name;
    private String  password;
    private String  mail;

    public User(String name,String password,String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

    public User(String mail, String password) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

}

