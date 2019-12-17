package apple.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apple.bean.CartDBBean;
import apple.bean.CartDataBean;

public class InsertCartAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//장바구니에 추가할 정보를 파라미터에서 받아냄
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		int prd_id = Integer.parseInt(request.getParameter("prd_id"));
		String prd_name = request.getParameter("prd_name");
		String prd_image = request.getParameter("prd_image");
		int buy_price = (int)Float.parseFloat(request.getParameter("buy_price"));
		String buyer = request.getParameter("buyer");
		
		//장바구니에 추가하기 위한 정보구성
		CartDataBean cart = new CartDataBean();
		cart.setPrd_id(prd_id);
		cart.setPrd_image(prd_image);
		cart.setPrd_name(prd_name);
		cart.setBuy_count(buy_count);
		cart.setBuy_price(buy_price);
		cart.setBuyer(buyer);
		
		//장바구니에 추가
		CartDBBean prdProcess = CartDBBean.getInstance();
		prdProcess.insertCart(cart);
		
		return "/cart/insertCart.jsp";
	}
}