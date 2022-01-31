package qt.mate.qt_mate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import qt.mate.qt_mate.model.MyPrayerDTO;
import qt.mate.qt_mate.model.MyQtDTO;
import qt.mate.qt_mate.model.QtDTO;

@Mapper
@Repository
public interface DashboardMapper {

    void addQt(String verse);
    QtDTO getQt();
    MyPrayerDTO getMyPrayer(String id);
    MyQtDTO getMyQt(String id);
    
}
