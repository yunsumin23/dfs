package dfS.ysm.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConInter {

	String Controller(HttpServletRequest rq, HttpServletResponse rs) throws Exception;

}
