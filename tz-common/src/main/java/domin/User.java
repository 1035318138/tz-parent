package domin;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;


import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    private String name;
    private String password;
    private Date endtime;
    private String imgurl;
    private Long tel;
    private String email;


}
