package qt.mate.qt_mate.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPrayerDTO {
    private int index;
    private String id;
    private String content;
    private Date date;
    private String response;
}
