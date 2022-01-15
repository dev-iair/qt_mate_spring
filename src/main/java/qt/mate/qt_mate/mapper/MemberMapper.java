package qt.mate.qt_mate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import qt.mate.qt_mate.model.MemberDTO;

@Mapper
@Repository
public interface MemberMapper {

    MemberDTO showMember(String name);
    
}
