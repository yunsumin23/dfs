<%@page import="java.util.List"%>
<%@page import="dfS.ysm.DTO.CharacterDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.DayOfWeek"%>
<%
	LocalDateTime currentDate = LocalDateTime.now();
	DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/search_result.css">
<script src="js/search_result.js"></script>
<title>search_result.jsp</title>
</head>
<body>
	<nav>
		<button onclick="location.href='main.jsp'">메인으로</button>
		<form id="searchForm" action="main.Controller?url=CSearch"
			method="POST">
			<select name="server">
				<option value="all">전체</option>
				<option value="adven">모험단</option>
				<option value="anton">안톤</option>
				<option value="bakal">바칼</option>
				<option value="cain">카인</option>
				<option value="casillas">카시야스</option>
				<option value="deregie">디레지에</option>
				<option value="hilder">힐더</option>
				<option value="prey">프레이</option>
				<option value="siroco">시로코</option>
			</select> <input type="text" id="text" name="name" placeholder="캐릭터명을 입력해주세요.">
			<input type="submit" value="검색">
		</form>
	</nav>
	<section id="first_section">
		<%
			List<CharacterDTO> list = (List<CharacterDTO>) request.getAttribute("List");
			int gayvolg = 0; //게이볼그 31489
			int myma = 0; //마이마 33043
			int espins = 0; //이스핀즈 33043
			int espins2 = 0; //이핀 돌던가 말던가
			int gaegeon = 0; //개전 34308
			int whiteSeaN = 0; //백해 노말 36132
			int hyerang = 0; //회랑 38095
			int joungjae = 0; //중재자 40047
			int ilkal = 0; //일칼 40047
			int emyeon = 0; //이면 44872
			int hakal = 0; //하칼 45247
			int bakal = 0;
			int whiteSeaE = 0; //백해 익스 47624
			int aduk = 0; //어둑섬 50248
			int whiteSeaM = 0; //백해 마스터 54098

			int ispins2 = 0;
			int gaegeon2 = 0;
			int hyerang2 = 0;
			int ilkal2 = 0;
			int emyeon2 = 0;
			int hakal2 = 0;
			int bakal2 = 0;
			int aduk2 = 0;
			
			int ispins3Step = 0; //이핀 3단 1캐릭씩 파티플
			int ispins3StepBg = 0; //이핀 3단 4캐릭씩 벞교
			int ispins3StepBjg = 0; //이핀 3단 2캐릭씩 쩔 교환
			
			int gaegeonS = 0; //개전 솔플
			int gaegeonP = 0; //개전 파티플
			int gaegeonBg = 0; //개전 4캐릭씩 벞교
			int gaegeonJ = 0; //개전 1캐릭씩 쩔
			int gaegeonBjg = 0; //개전 2캐릭씩 쩔 교환

			int hyerang3Step = 0; //회랑 3단 1캐릭씩 파티플
			int hyerang3StepBg = 0; //회랑 3단 4캐릭씩 벞교
			int hyerang3StepBjg = 0; //회랑 3단 2캐릭씩 쩔 벞교
			int hyerang4Step = 0; //회랑 초월 1캐릭씩 파티플
			int hyerang4StepBg = 0; //회랑 초월 4캐릭씩 벞교
			int hyerang4StepBhg = 0; //회랑 초월 2캐릭씩 쩔 벞교
			
			int bakalN = 0; // 일칼 1캐릭
			int bakalNb = 0; // 일칼 4캐릭씩 벞교
			int bakalNj = 0; // 일칼 1캐릭 쩔
			int bakalNB6jg = 0; // 일칼 6인쩔 3캐릭
			int bakalNB4jg = 0; // 일칼 4인쩔 2캐릭
			int bakalNB2jg = 0; // 일칼 2인쩔 2캐릭
			int bakalH = 0; // 하칼 1캐릭
			int bakalHb = 0; // 하칼 4캐릭씩 벞교
			int bakalHj = 0; // 하칼 1캐릭 쩔
			int bakalHB6jg = 0; //하칼 6인쩔 3캐릭
			int bakalHB4jg = 0; //하칼 4인쩔 2캐릭
			int bakalHB2jg = 0; //하칼 2인쩔 2캐릭
			
			int whiteSeaH = 0; //백해 마스터 1캐릭
			int whiteSeaHb2 = 0; //백해 마스터 벞교 2캐릭
			int whiteSeaHb4 = 0; //백해 마스터 벞교 4캐릭
			
			
			
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					int ispinsMark = 0;
					int hyerangMark = 0;
					int bakalMark = 0;
					int emyeonMark = 0;
					int adukMark = 0;

					String serverId = list.get(i).getServerId();
					String characterName = list.get(i).getCharacterName();
					int level = list.get(i).getLevel();
					String jobGrowName = list.get(i).getJobGrowName();
					int fame = list.get(i).getFame();
					String aventureName = list.get(i).getAdventureName();
					String characterId = list.get(i).getCharacterId();
					String time = list.get(i).getTime();
					int ispins3 = list.get(i).getIspins();
					int gaegeon3 = list.get(i).getGaegeon();
					int hyerang3 = list.get(i).getHyerang();
					int ilkal3 = list.get(i).getIlkal();
					int emyeon3 = list.get(i).getEmyeon();
					int hakal3 = list.get(i).getHakal();
					int aduk3 = list.get(i).getAduk();
					if (ispins3 == 1) {
						ispins2++;
						ispinsMark = 1;
					}
					if (gaegeon3 == 1) {
						gaegeon2++;
						bakalMark = 1;
					}
					if (hyerang3 == 1) {
						hyerang2++;
						hyerangMark = 1;
					}
					if (ilkal3 == 1) {
						ilkal2++;
						bakalMark = 1;
					}
					if (emyeon3 == 1) {
						emyeon2++;
						emyeonMark = 1;
					}
					if (hakal3 == 1) {
						hakal2++;
						bakalMark = 1;
					}
					if (aduk3 == 1) {
						aduk2++;
						adukMark = 1;
					}
					if (fame >= 54098) {
						gayvolg++;
						espins2++;
						hyerang++;
						joungjae++;
						emyeon++;
						hakal++;
						aduk++;
						whiteSeaM++;
					} else if (54098 > fame && fame >= 50248) {
						gayvolg++;
						espins2++;
						hyerang++;
						joungjae++;
						emyeon++;
						hakal++;
						whiteSeaE++;
						aduk++;
					} else if (50248 > fame && fame >= 47624) {
						gayvolg++;
						espins++;
						hyerang++;
						joungjae++;
						emyeon++;
						hakal++;
						whiteSeaE++;
					} else if (47624 > fame && fame >= 45247) {
						gayvolg++;
						espins++;
						whiteSeaN++;
						hyerang++;
						joungjae++;
						emyeon++;
						hakal++;
					} else if (45247 > fame && fame >= 44872) {
						gayvolg++;
						espins++;
						whiteSeaN++;
						hyerang++;
						joungjae++;
						ilkal++;
						emyeon++;
					} else if (44872 > fame && fame >= 40047) {
						gayvolg++;
						espins++;
						whiteSeaN++;
						hyerang++;
						joungjae++;
						ilkal++;
					} else if (38095 > fame && fame >= 36132) {
						gayvolg++;
						myma++;
						espins++;
						gaegeon++;
						whiteSeaN++;
					} else if (36132 > fame && fame >= 34308) {
						gayvolg++;
						myma++;
						espins++;
						gaegeon++;
					} else if (34308 > fame && fame >= 33043) {
						gayvolg++;
						myma++;
						espins++;
					} else if (33043 > fame && fame >= 31489) {
						gayvolg++;
					} else {

					}
					bakal = ilkal + hakal + gaegeon;
					bakal2 = gaegeon2 + ilkal2 + hakal2;
		%>
		<article id="box">
			<span id="server"> <%
 			if (serverId.equals("")) {
 				out.print("카인");
 			} else if (serverId.equals("anton")) {
 				out.print("안톤");
 			} else if (serverId.equals("bakal")) {
 				out.print("바칼");
 			} else if (serverId.equals("cain")) {
 				out.print("카인");
 			} else if (serverId.equals("casillas")) {
 				out.print("카시야스");
 			} else if (serverId.equals("deregie")) {
 				out.print("디레지에");
 			} else if (serverId.equals("hilder")) {
 				out.print("힐더");
 			} else if (serverId.equals("prey")) {
 				out.print("프레이");
 			} else if (serverId.equals("siroco")) {
 				out.print("시로코");
 			}
		 %>
			</span> 
			<span><%=time%></span><br>
			<span>
			<%
	 			if (ispinsMark == 0 && fame > 33042) {
	 				out.println("이핀");
	 			}
	 			if (hyerangMark == 0 && fame > 38094) {
	 				out.println("회랑");
	 			}
	 			if (emyeonMark == 0 && fame > 44871) {
	 				out.println("이면");
	 			}
	 			if (adukMark == 0 && fame > 50247) {
	 				out.println("어둑");
	 			}
	 			switch (dayOfWeek) {
	 			case THURSDAY:
	 				break;
	 			case FRIDAY:
	 				break;
	 			default:
	
	 				if (bakalMark == 0 && fame > 34307 && fame < 40047) {
	 					out.println("개전");
	 				} else if (bakalMark == 0 && fame > 40046 && fame < 45247) {
	 					out.println("일칼");
	 				} else if (bakalMark == 0 && fame > 45246) {
	 					out.println("하칼");
	 				}
	 			}
			%>
			</span> 
			<span><img id='c_img' src="https://img-api.neople.co.kr/df/servers/<%=serverId%>/characters/<%=characterId%>?zoom=1"></span>
			<span>Lv<%=level%></span> 
			<span><%=jobGrowName%></span><br> 
			<span id="characterName"><%=characterName%></span><br> 
			<span><img src="img/fame.png" alt="명성" width="11px" height="10px" /> <%=fame%></span>

		</article>
		<%
			}
			} else {
				out.println("데이터가 없습니다.");

			}
		%>
	</section>
	<table>
		<tr>
			<td colspan="3">
				<h1>던짱의 시간은 소중하니깐...</h1>
			</td>
		</tr>
		<tr>
			<td>
				<span id='d_title'>입장 가능 던전</span>
			</td>
			<td>
				<span id='d_title'>클리어  횟수 / 입장 가능 캐릭터 수</span>
			</td>
			<td>
				<span></span>
			</td>
		</tr>
	
		<tr>
			<td id="first_td">
				<section id="second_section">
					<%
						if (gayvolg > 4) {
							gayvolg = 4;
						}
						if (gayvolg > 0) {
							out.println("<span id='d_name'>코드네임 게이볼그</span> 31489<span id='d_name'> : " + gayvolg + "</span><br>");
						}
						if (myma > 0) {
							out.println("<span id='d_name'>마이스터의 실험실(마스터)</span> 33043<span id='d_name'> : " + myma + "</span><br>");
						}
						if (espins > 0) {
							out.println("<span id='d_name'>이스핀즈</span> 33043<span id='d_name'> : " + espins + "</span><br>");
						}
						if (espins2 > 0) {
							out.println("<span id='d_name'>이스핀즈2</span><span id='d_name'> : " + espins2 + "</span><br>");
						}
						if (gaegeon > 0) {
							out.println("<span id='d_name'>기계혁명:개전</span> 34308<span id='d_name'> : " + gaegeon + "</span><br>");
						}
						if (whiteSeaN > 0) {
							out.println("<span id='d_name'>백해 상급 던전(흰 구름 계곡, 솔리다리스) 노말</span> 36132<span id='d_name'> : " + whiteSeaN + "</span><br>");
						}
						if (hyerang > 0) {
							out.println("<span id='d_name'>차원회랑</span> 38095<span id='d_name'> : " + hyerang + "</span><br>");
						}
						if (joungjae > 0) {
							out.println("<span id='d_name'>균형의 중재자</span> 40047<span id='d_name'> : " + joungjae + "</span><br>");
						}
						if (ilkal > 0) {
							out.println("<span id='d_name'>바칼 레이드(일반)</span> 40047<span id='d_name'> : " + ilkal + "</span><br>");
						}
						if (emyeon > 0) {
							out.println("<span id='d_name'>이면 경계</span> 44872<span id='d_name'> : " + emyeon + "</span><br>");
						}
						if (hakal > 0) {
							out.println("<span id='d_name'>바칼 레이드(하드)</span> 45247<span id='d_name'> : " + hakal + "</span><br>");
						}
						if (whiteSeaE > 0) {
							out.println("<span id='d_name'>백해 상급 던전(흰 구름 계곡, 솔리다리스) 익스퍼트</span> 47624<span id='d_name'> : " + whiteSeaE + "</span><br>");
						}
						if (aduk > 0) {
							out.println("<span id='d_name'>어둑섬</span> 50248<span id='d_name'> : " + aduk + "</span><br>");
						}
						if (whiteSeaM > 0) {
							out.println("<span id='d_name'>백해 상급 던전(흰 구름 계곡, 솔리다리스) 마스터</span> 54098<span id='d_name'> : " + whiteSeaM + "</span><br>");
						}
					%>
				</section>
			</td>
			<td id="second_td">
				<section id="third_section">
					<%
						if (ispins2 > 0) {
							out.println("<span id='d_name'>이스핀즈: " + ispins2 + "/" + (espins + espins2) + "</span><br>");
						}
						if (hyerang2 > 0) {
							out.println("<span id='d_name'>차원회랑: " + hyerang2 + "/" + hyerang + "</span><br>");
						}
						if (emyeon2 > 0) {
							out.println("<span id='d_name'>이면 경계: " + emyeon2 + "/" + emyeon + "</span><br>");
						}
						if (aduk2 > 0) {
							out.println("<span id='d_name'>어둑섬: " + aduk2 + "/" + aduk + "</span><br>");
						}
						switch (dayOfWeek) {
			 			case THURSDAY:
			 				break;
			 			case FRIDAY:
			 				break;
			 			default:
							out.println("<span id='d_name'>바칼레이드</span>(기계혁명:개전 | 바칼 레이드(일반) | 바칼 레이드(하드))<br>");
							if (bakal2 > 0) {
								out.println("<span id='d_name'>" + bakal2 + "/" + bakal + " (" + gaegeon2 + ", " + ilkal2 + ", " + hakal2 + ")</span>");
							}	
			 			}
						// 		if (gaegeon2 > 0) {
						// 			out.println("기계혁명:개전: " + gaegeon + "/" + gaegeon2 + "<br>");
						// 		}
						// 		if (ilkal2 > 0) {
						// 			out.println("바칼 레이드(일반): " + ilkal + "/" + ilkal2 +"<br>");
						// 		}
						// 		if (hakal2 > 0) {
						// 			out.println("바칼 레이드(하드): " + hakal + "/" + hakal2 + "<br>");
						// 		}
					%>
				</section>
			</td>
			<td id='third_td'>
				<%
					int totalTime = (espins + espins2 - ispins2) * 6 + (hyerang - hyerang2) * 4 + (gaegeon - gaegeon2) * 8 +
					(ilkal -  ilkal2) * 20 + (emyeon - emyeon2) * 7 + (hakal - hakal2) * 20 + (aduk - aduk2) * 10;
			
					int hours = totalTime / 60;  // 분을 시간으로 변환
					int minutes = totalTime % 60;  // 나머지는 분

					out.println("예상 시간 : " + hours + "시간 " + minutes + "분");
				%>
			</td>
		</tr>
	</table>
	<section id="adven_update">
					<%
						if (list != null) {
							for (int i = 0; i < list.size(); i++) {
								String server = list.get(i).getServerId();
								String characterName = list.get(i).getCharacterName();
								String characterId = list.get(i).getCharacterId();
								String aventureName = list.get(i).getAdventureName();
					%>
					<form action="search_result.Controller?url=AdvenAllUpdate"
						method="POST">
						<input type="hidden" name="servers" value=<%=server%>> <input
							type="hidden" name="characterNames" value=<%=characterName%>>
						<input type="hidden" name="characterIds" value=<%=characterId%>>
						<input type="hidden" name="server" value="adven"> <input
							type="hidden" name="name" value=<%=aventureName%>>
						<%
							}
							}
						%>
						<input type="submit" value="모험단 업데이트">
					</form>
				</section>
	<footer>
		<a href="http://developers.neople.co.kr" target="_blank"> <img
			src="img/neople.png" alt="Neople 오픈 API" />
		</a>
	</footer>
</body>
</html>