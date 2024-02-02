package dfS.ysm.control;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dfS.ysm.DTO.CharacterDTO;
import dfS.ysm.api.CharacterSearch;
import dfS.ysm.api.CharacterSearch2;
import dfS.ysm.mybatis.CSearchAndInsertConn;
import dfS.ysm.mybatis.CSearchResultConn2;

public class CSearchAndInsert implements ConInter { //광고게시판 Extract == 추출이라는 뜻

	static CSearchAndInsert dbExtract = new CSearchAndInsert();
	public static CSearchAndInsert instance() {
		return dbExtract;
	}
	
	@Override
	public String Controller(HttpServletRequest rq, HttpServletResponse rs) throws Exception {
//		System.out.println("CSearchExtr Controller method called");
		String server = rq.getParameter("server");
		String noName = rq.getParameter("name");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		String time = localDateTime.toString();
		
		
		if(server.equals("adven")) {		
			System.out.println("모험단 조회");
			CSearchResultConn2 cSearchResultConn2 = CSearchResultConn2.instance();
			List<CharacterDTO> list = cSearchResultConn2.dbSelect(noName);
			rq.setAttribute("List", list);
			return null;
			
		} else {
			String name = URLEncoder.encode(noName, "UTF-8");
			CharacterSearch characterSearch = new CharacterSearch();
	        String apiResponse = characterSearch.cInfo(server, name);

	        // JSON 파싱
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(apiResponse);

	        // 필요한 정보 추출
	        String serverId = jsonNode.get("rows").get(0).get("serverId").asText();
	        String characterId = jsonNode.get("rows").get(0).get("characterId").asText();
	        String characterName = jsonNode.get("rows").get(0).get("characterName").asText();
	        int level = jsonNode.get("rows").get(0).get("level").asInt();
	        String jobId = jsonNode.get("rows").get(0).get("jobId").asText();
	        String jobGrowId = jsonNode.get("rows").get(0).get("jobGrowId").asText();
	        String jobName = jsonNode.get("rows").get(0).get("jobName").asText();
	        String jobGrowName = jsonNode.get("rows").get(0).get("jobGrowName").asText();
	        int fame = jsonNode.get("rows").get(0).get("fame").asInt();
	        
	        //모험단명, 길드 정보 추출
	        CharacterSearch2 characterSearch2 = new CharacterSearch2();     
	        String apiResponse2 = characterSearch2.cInfo2(serverId, characterId);
	       
	        JsonNode jsonNode2 = objectMapper.readTree(apiResponse2);
	        
	        String adventureName = jsonNode2.get("adventureName").asText();
	        String guildId = jsonNode2.get("guildId").asText();
	        String guildName = jsonNode2.get("guildName").asText();
	        
	        
	        CharacterDTO characterDTO = new CharacterDTO();
	        characterDTO.setServerId(serverId);
	        characterDTO.setCharacterId(characterId);
	        characterDTO.setCharacterName(characterName);
	        characterDTO.setLevel(level);
	        characterDTO.setJobId(jobId);
	        characterDTO.setJobGrowId(jobGrowId);
	        characterDTO.setJobName(jobName);
	        characterDTO.setJobGrowName(jobGrowName);
	        characterDTO.setFame(fame);
	        characterDTO.setAdventureName(adventureName);
	        characterDTO.setGuildId(guildId);
	        characterDTO.setGuildName(guildName);
	        characterDTO.setTime(time);
	        
	        CSearchAndInsertConn insert = CSearchAndInsertConn.instance();
	        insert.cSearchAndInsertConn(characterDTO);

			return null;
		}
	}
}