package apple.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.MngrDBBean;
import apple.bean.MngrDataBean;

public class PrdUpdateFormAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int prd_id = Integer.parseInt(request.getParameter("prd_id"));
		String prd_kind = request.getParameter("prd_kind");
		
		//DB연동 prd_id에 해당하는 상품을 얻내서 prd에 저장
		MngrDBBean prdProcess = MngrDBBean.getInstance();
		MngrDataBean prd =  prdProcess.getPrd(prd_id);
		
		request.setAttribute("prd_id", prd_id);
		request.setAttribute("prd_kind", prd_kind);
        request.setAttribute("prd", prd);
		request.setAttribute("type", new Integer(0));
		return "/mngr/productProcess/prdUpdateForm.jsp";
	}
}