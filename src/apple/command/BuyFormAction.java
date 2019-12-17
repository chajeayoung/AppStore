package apple.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.BuyDBBean;
import apple.bean.CartDBBean;
import apple.bean.CartDataBean;
import apple.bean.LogonDBBean;
import apple.bean.LogonDataBean;

public class BuyFormAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String buyer = request.getParameter("buyer");
		
		List<CartDataBean> cartLists = null;
		List<String> accountLists = null;
		LogonDataBean member= null;
		int count = 0;

		//해당 buyer의 장바구니 목록의 수를 얻어냄
		CartDBBean prdProcess = CartDBBean.getInstance();
        count = prdProcess.getListCount(buyer);
		
		if(count > 0){//장바구니 목록이 있으면 수행
			//구매에 필요한 해당 buyer의 장바구니 목록을 얻어냄
			cartLists = prdProcess.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
		
		//구매에 필요한 buyer의 정보를 얻어냄
		LogonDBBean memberProcess = LogonDBBean.getInstance();
		member = memberProcess.getMember(buyer);
		
	
		request.setAttribute("member", member);
		request.setAttribute("accountLists", accountLists);
		request.setAttribute("type", new Integer(1));
		return "/buy/buyForm.jsp";
	}
}
