package apple.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.LogonDBBean;
import apple.bean.LogonDataBean;

public class ModifyFormAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		//회원 정보를 수정하기 위한 정보를 얻어냄
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean m = manager.getMember(id,passwd); 
		
		request.setAttribute("m",m);
		request.setAttribute("id", id);
		request.setAttribute("type", new Integer(1));
		return "/member/modifyForm.jsp";
	}
}