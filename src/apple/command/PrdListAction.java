package apple.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.MngrDBBean;
import apple.bean.MngrDataBean;

public class PrdListAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<MngrDataBean> prdList = null;
		String prd_kind = request.getParameter("prd_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		MngrDBBean prdProcess = MngrDBBean.getInstance();
        count = prdProcess.getPrdCount(); 
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 prdList에 저장
        	prdList = prdProcess.getPrds(prd_kind);
        	//prdList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("prdList", prdList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("prd_kind", prd_kind);
        request.setAttribute("type", new Integer(0));
		return "/mngr/productProcess/prdList.jsp";
	}
}