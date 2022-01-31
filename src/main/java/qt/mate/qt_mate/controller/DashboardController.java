package qt.mate.qt_mate.controller;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import qt.mate.qt_mate.model.MyPrayerDTO;
import qt.mate.qt_mate.model.MyQtDTO;
import qt.mate.qt_mate.model.QtDTO;
import qt.mate.qt_mate.service.DashboardService;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    
    private final DashboardService dasboardService;

    @RequestMapping(value = "/getDashboard")
    public String GetDashboard() throws JSONException, IOException{
        JSONObject jsonData = new JSONObject();
        QtDTO qtDTO = new QtDTO();
        MyQtDTO myQtDTO = new MyQtDTO();
        MyPrayerDTO myPrayerDTO = new MyPrayerDTO();
        String id = "test";
        try{
            qtDTO = dasboardService.getQt();
            jsonData.put("verse",qtDTO.getVerse());
        }catch(NullPointerException e){
            Document html = Jsoup.connect("https://sum.su.or.kr:8888/bible/today").get();
            String verse = html.select(".bibleinfo_box").get(0).text();
            int cutIndex = verse.indexOf('찬');
            verse = verse.substring(5,cutIndex-1);
            dasboardService.addQt(verse);
            jsonData.put("verse", verse);
        }
        try{
            myQtDTO = dasboardService.getMyQt(id);
            jsonData.put("apply",myQtDTO.getContent());
        }catch(NullPointerException e){
            jsonData.put("apply", "");
        }
        try{
            myPrayerDTO = dasboardService.getMyPrayer(id);
            jsonData.put("prayer",myPrayerDTO.getContent());
        }catch(NullPointerException e){
            jsonData.put("prayer", "");
        }
        return jsonData.toString();
    }
}
