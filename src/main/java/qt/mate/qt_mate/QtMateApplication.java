package qt.mate.qt_mate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import qt.mate.qt_mate.service.DashboardService;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class QtMateApplication {

	private final DashboardService dasboardService;

	public static void main(String[] args) {
		SpringApplication.run(QtMateApplication.class, args);
	}
	
	@Scheduled(cron = "0 5 0 * * *")
    public void GetQT() {
		try{
			URL url = new URL ("https://sum.su.or.kr:8888/Ajax/Bible/BodyBibleCont");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todayString = sdf.format(dt);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String jsonData ="{\"qt_ty\":\"QT6\",\"Base_de\":\""+todayString+"\",\"Bibletype\":\"1\"}";
			try(OutputStream os = con.getOutputStream()) {
				byte[] byteData = jsonData.getBytes("utf-8");
				os.write(byteData, 0, byteData.length);
			}
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
        		JSONObject responseJson = new JSONObject(response.toString());
				String verse = responseJson.getString("Bible_name") + " " + responseJson.getString("Bible_chapter");
				dasboardService.addQt(verse);
			}
			}catch(Exception e){
				System.out.println("[ SCHEDULE EXCEPTION ] " + e);
			}
    }
    

}
