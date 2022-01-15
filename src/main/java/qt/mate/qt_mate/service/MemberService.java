package qt.mate.qt_mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qt.mate.qt_mate.mapper.MemberMapper;
import qt.mate.qt_mate.model.MemberDTO;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public MemberDTO showMember(String name) {
        return memberMapper.showMember(name);
    }
    
}
