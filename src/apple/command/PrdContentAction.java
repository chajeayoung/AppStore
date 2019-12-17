package apple.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.MngrDBBean;
import apple.bean.MngrDataBean;


public class PrdContentAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		

		String prd_kind = request.getParameter("prd_kind");
		int prd_id = Integer.parseInt(request.getParameter("prd_id"));
		
		//prd_id에 해당하는 상품을 얻어냄
		MngrDBBean prdProcess = MngrDBBean.getInstance();
		MngrDataBean prd = prdProcess.getPrd(prd_id);
		
		//prd_id에 해당하는 상품의 QnA 수를 얻어냄
	

				
	
		
		request.setAttribute("end_time", prd.getEnd_time());
		request.setAttribute("prd", prd);
		request.setAttribute("prd_id", new Integer(prd_id));
		request.setAttribute("prd_kind", prd_kind);
		request.setAttribute("type", new Integer(1));
		return "/shop/prdContent.jsp";
	}
}