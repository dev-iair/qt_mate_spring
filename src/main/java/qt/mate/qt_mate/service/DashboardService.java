package qt.mate.qt_mate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import qt.mate.qt_mate.mapper.DashboardMapper;
import qt.mate.qt_mate.model.MyPrayerDTO;
import qt.mate.qt_mate.model.MyQtDTO;
import qt.mate.qt_mate.model.QtDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    public void addQt(String verse) {
        dashboardMapper.addQt(verse);
    }

    public QtDTO getQt() {
        return dashboardMapper.getQt();
    }

    public MyPrayerDTO getMyPrayer(String id) {
        return dashboardMapper.getMyPrayer(id);
    }

    public MyQtDTO getMyQt(String id) {
        return dashboardMapper.getMyQt(id);
    }
    
}
