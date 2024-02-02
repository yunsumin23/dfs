package dfS.ysm.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        rq.setCharacterEncoding("UTF-8"); //한글 데이터 인코딩 설정
        String url = rq.getParameter("url");
        String server = rq.getParameter("server");
        ConInter inter = null; //인터페이스용 주머니

        if (url.equals("CSearch") && rq.getMethod().equalsIgnoreCase("POST")) {
            inter = CSearchAndInsert.instance();
            try {
                String csearch = inter.Controller(rq, rs);

                // CSearchAndInsert 작업 후에 CSearchResultExtr 호출
                if(!server.equals("adven")) {
                	ConInter resultExtr = CSearchResultExtr.instance();
                	resultExtr.Controller(rq, rs);              	
                }

                RequestDispatcher re = rq.getRequestDispatcher("search_result.jsp");
                re.forward(rq, rs);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("dfS.ysm.control.controller 'CSearch' ERROR");
            }
        } else if (url.equals("AdvenAllUpdate") && rq.getMethod().equalsIgnoreCase("POST")) {
        	inter = AdvenAllSearchExtr.instance();
        	try {
				String AdvenAllUpdate = inter.Controller(rq, rs);
				
				ConInter resultExtr = CSearchAndInsert.instance();
            	resultExtr.Controller(rq, rs);
            	
				RequestDispatcher re = rq.getRequestDispatcher("search_result.jsp");
				re.forward(rq, rs);
			} catch (Exception e) {
				System.out.println(e);
                System.out.println("dfS.ysm.control.controller 'AdvenAllUpdate' ERROR");
			}
        }
    }
}
