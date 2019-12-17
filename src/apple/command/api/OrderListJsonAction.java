package apple.command.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import apple.bean.BuyDBBean;
import apple.bean.BuyDataBean;
import apple.command.Action;

public class OrderListJsonAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<BuyDataBean> buyLists = null;
		int count = 0;
		
		BuyDBBean buyProcess = BuyDBBean.getInstance();
		count = buyProcess.getListCount();
		
		if(count > 0){//주문목록이 있으면
			//전체 주문목록을 얻어냄
			buyLists = buyProcess.getBuyList();
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(buyLists).toString();
		
		request.setAttribute("count", new Integer(count));
	    request.setAttribute("type", new Integer(0));
		return result;
	}

}
