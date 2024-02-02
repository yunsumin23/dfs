package dfS.ysm.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CharacterSearch2 {
	public String cInfo2(String serverId, String characterId) {
		System.out.println("CharacterSearch2 cInfo2 method called");
        String apiKey = "MJYuhm7dPZrYOYAH5vlOpiSg94Vj9WhS";
        String apiUrl = "https://api.neople.co.kr/df/servers/"+ serverId +"/characters/" + characterId + "?apikey=MJYuhm7dPZrYOYAH5vlOpiSg94Vj9WhS";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET 메서드 설정
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답을 읽어옴
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.ISO_8859_1))) {
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // ISO-8859-1에서 UTF-8로 변환
                    String utf8Response = new String(response.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    return utf8Response;
                }
            } else {
                System.out.println("Error in API request");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
		}
	}
}
