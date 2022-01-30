package qt.mate.qt_mate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import qt.mate.qt_mate.model.QtDTO;

@Mapper
@Repository
public interface QtMapper {

    void addQt(String verse);

    QtDTO getQT();
    
}
