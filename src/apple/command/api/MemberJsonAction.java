package apple.command.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import apple.bean.LogonDataBean;
import apple.command.Action;
import apple.bean.LogonDBBean;

public class MemberJsonAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		LogonDBBean db = LogonDBBean.getInstance();
		ArrayList<LogonDataBean> list = db.getMembers();
		
		Gson gson = new Gson();
		String result = gson.toJson(list).toString();
		return result;
	}

}
