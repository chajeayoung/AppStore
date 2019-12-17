package apple.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import apple.bean.MngrDBBean;
import apple.bean.MngrDataBean;

public class PrdUpdateProAction implements Action {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");//한글 인코딩
		
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/prdImage"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		int maxSize = 1*1024*1024;  //최대 업로될 파일크기 1Mb
		
		MultipartRequest imageUp = null;

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);  
       
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  
		     while(files.hasMoreElements()){
		       String name = (String)files.nextElement();
		       filename = imageUp.getFilesystemName(name);
		     }
		}catch(Exception e){
		     e.printStackTrace();
		}
		
		MngrDataBean prd = new MngrDataBean();
		int prd_id= Integer.parseInt( imageUp.getParameter("prd_id"));
		String prd_kind = imageUp.getParameter("prd_kind");
		String prd_name = imageUp.getParameter("prd_name");
		String prd_price = imageUp.getParameter("prd_price");
		String prd_count = imageUp.getParameter("prd_count");
		String prd_content = imageUp.getParameter("prd_content");
		

		
		prd.setPrd_kind(prd_kind);
		prd.setPrd_name(prd_name);
		prd.setPrd_price(Integer.parseInt(prd_price));
		prd.setPrd_count(Short.parseShort(prd_count));
		prd.setPrd_image(filename);
		prd.setPrd_content(prd_content);
		prd.setReg_date(new Timestamp(System.currentTimeMillis()));

		//DB연동해서 상품 수정 처리
		MngrDBBean prdProcess = MngrDBBean.getInstance();
		prdProcess.updatePrd(prd, prd_id);
		
		request.setAttribute("prd_kind", prd_kind);
		return "/mngr/productProcess/prdUpdatePro.jsp";
	}
}