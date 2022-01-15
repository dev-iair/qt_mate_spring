package qt.mate.qt_mate.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String name;
    private String pasture;
    private int phone;
    private Date birthday;
    private String school;
    private String password;
}
