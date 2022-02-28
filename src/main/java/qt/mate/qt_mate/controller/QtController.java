package qt.mate.qt_mate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QtController {

    @RequestMapping(value = "/getQt")
    public String GetDashboard() throws JSONException, IOException{
        String verse = new String();
        String understand = new String();
        String apply = new String();
        String pray = new String();
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
				verse = responseJson.getString("Bible_name") + " " + responseJson.getString("Bible_chapter");
                understand = responseJson.getString("Qt_a1");
                apply = responseJson.getString("Qt_a2");
                pray = responseJson.getString("Qt_a3");
			}
			}catch(Exception e){
				System.out.println("[ SCHEDULE EXCEPTION ] " + e);
			}
        JSONObject jsonData = new JSONObject();
        jsonData.put("verse", verse);
        jsonData.put("understand", understand);
        jsonData.put("apply", apply);
        jsonData.put("pray", pray);
        System.out.println(jsonData);
        return jsonData.toString();
    }
    
}
