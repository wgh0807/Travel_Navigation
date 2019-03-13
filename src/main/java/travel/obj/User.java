package travel.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {//地点详情
    private Integer id;//id
        private String name;
        private String password;
        private String mail;

}

