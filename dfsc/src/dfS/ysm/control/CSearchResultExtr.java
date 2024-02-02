package dfS.ysm.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dfS.ysm.DTO.CharacterDTO;
import dfS.ysm.mybatis.CSearchResultConn;

public class CSearchResultExtr implements ConInter {
	static CSearchResultExtr dbExtract = new CSearchResultExtr();
	public static CSearchResultExtr instance() {
		return dbExtract;
	}
	
	@Override
	public String Controller(HttpServletRequest rq, HttpServletResponse rs) throws Exception {
		String name = rq.getParameter("name");
		CSearchResultConn select = CSearchResultConn.instance();
		List<CharacterDTO> list = select.dbSelect(name);
		rq.setAttribute("List", list);
		
		return null;
	}
}