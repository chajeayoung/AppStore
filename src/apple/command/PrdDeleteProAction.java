package apple.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.MngrDBBean;

public class PrdDeleteProAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int prd_id = Integer.parseInt(request.getParameter("prd_id"));
		String prd_kind = request.getParameter("prd_kind");
		
		//DB연동 - prd_id에 해당하는 상품을 삭제
		MngrDBBean prdProcess = MngrDBBean.getInstance();
		prdProcess.deletePrd(prd_id); 
		
		request.setAttribute("prd_kind", prd_kind);
		return "/mngr/productProcess/prdDeletePro.jsp";
	}
}