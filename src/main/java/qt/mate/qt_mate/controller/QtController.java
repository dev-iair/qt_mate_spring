package qt.mate.qt_mate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qt.mate.qt_mate.model.QtDTO;
import qt.mate.qt_mate.service.QtService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class QtController {

    private Logger logger = LogManager.getLogger(QtController.class);

    @Autowired
    private QtService qtService;

    @RequestMapping(value = "/todayVerse")
    public String showQt(){
        QtDTO qtDTO = qtService.getQt();
        if(qtDTO==null){
            try{
                Document html = Jsoup.connect("https://sum.su.or.kr:8888/bible/today").get();
                String verse = html.select(".bibleinfo_box").get(0).text();
                int cutIndex = verse.indexOf('찬');
                verse = verse.substring(5,cutIndex-1);
                qtService.addQt(verse);
                return verse;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return qtDTO.getVerse();
    }
    
}
