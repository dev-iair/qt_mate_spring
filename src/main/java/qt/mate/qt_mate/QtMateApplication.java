package qt.mate.qt_mate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class QtMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(QtMateApplication.class, args);
	}

	@Scheduled(cron = "0 5 0 * * *")
    public void GetQT() {
		try{
			URL url = new URL ("https://sum.su.or.kr:8888/Ajax/Bible/BodyBibleCont");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String jsonInputString ="{\"qt_ty\":\"QT6\",\"Base_de\":\"2022-02-26\",\"Bibletype\":\"1\"}";
			try(OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}
			}catch(Exception e){
				System.out.println("[EXCEPTION] "+e);
			}
    }
    

}
