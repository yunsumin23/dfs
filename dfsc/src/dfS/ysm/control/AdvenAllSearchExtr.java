package dfS.ysm.control;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dfS.ysm.DTO.CharacterDTO;
import dfS.ysm.api.CharacterSearch;
import dfS.ysm.api.CharacterSearch2;
import dfS.ysm.api.TimeLine;
import dfS.ysm.api.TimeLineDropitem;
import dfS.ysm.mybatis.CSearchAndInsertConn;

public class AdvenAllSearchExtr implements ConInter {
	static AdvenAllSearchExtr dbExtract = new AdvenAllSearchExtr();
	public static AdvenAllSearchExtr instance() {
		return dbExtract;
	}
	@Override
	public String Controller(HttpServletRequest rq, HttpServletResponse rs) throws Exception {
		String[] servers = rq.getParameterValues("servers");
		String[] ids = rq.getParameterValues("characterIds");
		String[] noNames = rq.getParameterValues("characterNames");
		String[] names = new String[servers.length];
		LocalDateTime localDateTime = LocalDateTime.now();
		String time = localDateTime.toString();
		
		for(int i = 0; i < servers.length; i++) {
			names[i] = URLEncoder.encode(noNames[i], "UTF-8");
			CharacterSearch characterSearch = new CharacterSearch();
	        String apiResponse = characterSearch.cInfo(servers[i], names[i]);

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
	        
//	        "timeline" : {1
//	            "date" : {2
//	              "start" : "2023-11-25 17:16",
//	              "end" : "2023-12-25 17:16"
//	            },2
//	            "next" : "d969eb86d091640e89f61ad70cdbe401b6a0e022bcc6ae43040c87819916cbc055c1e059a1fc13f916b801702746d6ed8db413564ae2b37abc9f1c2d6832e61ef81980a322afc189690aa43f1f2baa3ee910bcf22621bb21e86223649720478d",
//	            "rows" : [ {3
//	              "code" : 209,
//	              "name" : "레기온 클리어",
//	              "date" : "2023-12-24 17:37",
//	              "data" : {4
//	                "regionName" : "이스핀즈"
//	              }4
//	            }3,{5
//	              "code" : 201,
//	              "name" : "레이드",
//	              "date" : "2023-12-21 00:41",
//	              "data" : {6
//	                "raidName" : "기계 혁명",
//	                "raidPartyName" : "하드5.1 딜러버퍼님!@",
//	                "modeName" : "바칼 레이드",
//	                "hard" : true
//	              }6
//	            }5
//		}1
	        //타임라인
	        TimeLine timeLine = new TimeLine();
	        String apiResponse3 = timeLine.tLine(servers[i], ids[i]);

	        JsonNode jsonNode3 = objectMapper.readTree(apiResponse3);
	        
	        JsonNode timelineRows = jsonNode3.get("timeline").get("rows");

	        String regionName;
	        String raidName;
	        String modeName;
	        boolean hard = false;
	        
	        int ispins = 0;
	        int hyerang = 0;
	        int aduk = 0;
	        int gaegeon = 0;
	        int ilkal = 0;
	        int hakal =0;
	        int emyeon = 0;
	        
			System.out.println(characterName);
			for (int k = 0; k < timelineRows.size(); k++) {
				if (timelineRows.isArray() && timelineRows.size() > 0) {
					JsonNode firstEvent = timelineRows.get(k);

					if (firstEvent.has("data")) {
						JsonNode eventData = firstEvent.get("data");

						if (eventData.has("regionName")) {
							regionName = eventData.get("regionName").asText();

							if (regionName.equals("이스핀즈")) {
								ispins++;
							}
							if (regionName.equals("차원회랑")) {
								hyerang++;
							}
							if (regionName.equals("어둑섬")) {
								aduk++;
							}
						}

						if (eventData.has("raidName")) {
							raidName = eventData.get("raidName").asText();
							modeName = eventData.get("modeName").asText();
							
							try {
							    hard = eventData.get("hard").asBoolean();
							} catch (Exception e) {
							    e.printStackTrace(); // 예외 내용을 출력하거나 다른 예외 처리 로직을 추가할 수 있습니다.
							} finally {
							    // 예외 발생 여부와 상관없이 항상 실행되는 부분
							    // 필요한 정리 작업 등을 추가할 수 있습니다.
							}

							if (raidName.equals("기계 혁명")&&modeName.equals("바칼 레이드") && hard==true) {
								hakal++;
							} else if(raidName.equals("기계 혁명") && modeName.equals("바칼 레이드")) {
								ilkal++;
							} else if(raidName.equals("기계 혁명") && modeName.equals("개전")) {
								gaegeon++;
							} else {
								System.out.println("바칼레이드가 아닙니다.");
							}
						}
					}
				}
			}
			//타임라인 에픽드랍 https://api.neople.co.kr/df/servers/cain/characters/0d59477b0d5bb4454427ed0aa8af9985/timeline?limit=100&code=505&startDate=<startDate>&endDate=<endDate>&next=<next>
			//&apikey=MJYuhm7dPZrYOYAH5vlOpiSg94Vj9WhS
	        TimeLineDropitem timeLineDropitem = new TimeLineDropitem();
	        String apiResponse4 = timeLineDropitem.tLineDropitem(servers[i], ids[i]);

	        JsonNode jsonNode4 = objectMapper.readTree(apiResponse4);
	        
	        JsonNode timelineRows2 = jsonNode4.get("timeline").get("rows");
	        
	        for (int k = 0; k < timelineRows2.size(); k++) {
				if (timelineRows2.isArray() && timelineRows2.size() > 0) {
					JsonNode firstEvent = timelineRows2.get(k);

					if (firstEvent.has("data")) {
						JsonNode eventData = firstEvent.get("data");

						if (eventData.has("dungeonName")) {
							regionName = eventData.get("dungeonName").asText();

							if (regionName.equals("이면 경계")) {
								emyeon = 1;
							}
						}
					}
				}
	        }
			System.out.println("이핀"+ispins);
			System.out.println("회랑"+hyerang);
			System.out.println("어둑"+aduk);
	        System.out.println("개전"+gaegeon);
	        System.out.println("일칼"+ilkal);
	        System.out.println("하칼"+hakal);
	        System.out.println("이면"+emyeon);

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
	        characterDTO.setIspins(ispins);
	        characterDTO.setHyerang(hyerang);
	        characterDTO.setAduk(aduk);
	        characterDTO.setGaegeon(gaegeon);
	        characterDTO.setIlkal(ilkal);
	        characterDTO.setHakal(hakal);
	        characterDTO.setEmyeon(emyeon);
	        
	        CSearchAndInsertConn insert = CSearchAndInsertConn.instance();
	        insert.cSearchAndInsertConn(characterDTO);
		}
		
		return null;
	}
	
	
				}
