package dfS.ysm.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeLineDropitem {
	public String tLineDropitem(String server, String characterId) {
		System.out.println("TimeLineDropitem tLineDropitem method called");
		//현재 날짜 가져오기, 현재 요일 가져오기
		LocalDateTime currentDate = LocalDateTime.now();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		LocalDateTime searchTime = null;
		
		switch(dayOfWeek) {
		case THURSDAY:
			if (currentDate.getHour() >= 6) {
                // 목요일이면서 06:00 이후일 경우 현재 날짜의 06:00부터 조회
                searchTime = currentDate.withHour(6).withMinute(0);
            } else {
                // 목요일이면서 06:00 이전일 경우 전주 목요일 06:00부터 조회
                searchTime = currentDate.minusDays(7).withHour(6).withMinute(0);
            }
            break;
		case FRIDAY:
			searchTime = currentDate.minusDays(1).withHour(6).withMinute(0); //목요일 06:00으로 설정
			break;
		case SATURDAY:
			searchTime = currentDate.minusDays(2).withHour(6).withMinute(0);
			break;
		case SUNDAY:
			searchTime = currentDate.minusDays(3).withHour(6).withMinute(0);
			break;
		case MONDAY:
			searchTime = currentDate.minusDays(4).withHour(6).withMinute(0);
			break;
		case TUESDAY:
			searchTime = currentDate.minusDays(5).withHour(6).withMinute(0);
			break;
		case WEDNESDAY:
			searchTime = currentDate.minusDays(6).withHour(6).withMinute(0);
			break;
		default:
			System.out.println("TimeLine ERROR");
			break;
		}
		

		//포맷 설정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDate = currentDate.format(formatter);
		String formattedSearchDate = searchTime.format(formatter);	

		System.out.println(formattedSearchDate+" ~ " + formattedDate);
        String apiKey = "MJYuhm7dPZrYOYAH5vlOpiSg94Vj9WhS";

     

        try {
        	String apiUrl = "https://api.neople.co.kr/df/servers/"+server+"/characters/"+characterId+"/timeline?limit=100&code=505&startDate="+URLEncoder.encode(formattedSearchDate, "UTF-8")
        	+"&endDate="+URLEncoder.encode(formattedDate, "UTF-8")+"&apikey="+apiKey;

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
//                    System.out.println(utf8Response);
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
