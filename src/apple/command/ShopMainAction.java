package apple.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.MngrDBBean;
import apple.bean.MngrDataBean;

public class ShopMainAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		MngrDataBean prdList[] = null;
		List<MngrDataBean[]> prdLists = new ArrayList<MngrDataBean[]>();
		
		MngrDBBean prdProcess = MngrDBBean.getInstance();//DB연동
		
		//카테고리별 최신의 상품 3개씩 얻어내서 List에 저장
		for(int i=1; i<=3;i++){
			prdList = prdProcess.getPrds(i+"00",3);
			prdLists.add(prdList);
		}
		
        //해당 페이지로 보낼 내용 설정
		
        request.setAttribute("prdLists", prdLists);
        //사용자 화면을 의미하는 값을 설정
		request.setAttribute("type", new Integer(1));
		return "/shop/shopMain.jsp";
	}
}