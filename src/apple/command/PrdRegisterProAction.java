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

public class PrdRegisterProAction implements Action {

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
			  
			 //파일 정보가 있다면
		     while(files.hasMoreElements()){
		       //input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
		       String name = (String)files.nextElement();
		   
		       //서버에 저장된 파일 이름
		       filename = imageUp.getFilesystemName(name);
		     }
		  }catch(Exception e){
		     e.printStackTrace();
		  }
		
		//폼으로부터 넘어온 정보중 파일이 아닌 정보를 얻어냄
		MngrDataBean prd = new MngrDataBean();
		//prd테이블에 넣을거임
		String prd_kind = imageUp.getParameter("prd_kind");
		String prd_name = imageUp.getParameter("prd_name");		
		String prd_content = imageUp.getParameter("prd_content");
		String end_time = imageUp.getParameter("end_time");
		String prd_price = imageUp.getParameter("prd_price");
		
		
		//option테이블에 넣을거임
		String option1 = imageUp.getParameter("option1");
		String option2 = imageUp.getParameter("option2");		
		String prd_count = imageUp.getParameter("prd_count");
		System.out.println(end_time);
		

		//빈에 넣기
		prd.setPrd_kind(prd_kind);
		prd.setPrd_name(prd_name);
		prd.setPrd_image(filename);
		prd.setPrd_content(prd_content);
		prd.setPrd_price(Integer.parseInt(prd_price));
		prd.setReg_date(new Timestamp(System.currentTimeMillis()));
		prd.setEnd_time(end_time);
		prd.setOption1(option1);
		prd.setOption2(option2);
		prd.setPrd_count(Short.parseShort(prd_count));	
		

		//DB연동 - 넘어온 정보를 테이블의 레코드로 추가
		MngrDBBean prdProcess = MngrDBBean.getInstance();
		prdProcess.insertPrd(prd);
		
		request.setAttribute("prd_kind", prd_kind);
		return "/mngr/productProcess/prdRegisterPro.jsp";
	}
}
