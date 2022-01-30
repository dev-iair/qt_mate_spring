package qt.mate.qt_mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qt.mate.qt_mate.mapper.QtMapper;
import qt.mate.qt_mate.model.QtDTO;

@Service
@Transactional
public class QtService {

    @Autowired
    private QtMapper qtMapper;

    public void addQt(String verse) {
        qtMapper.addQt(verse);
    }

    public QtDTO getQt() {
        return qtMapper.getQT();
    }
    
}
