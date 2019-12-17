package apple.bean;

import java.sql.Timestamp;

public class MngrDataBean {
	
	//prd 테이블에 get set 될 변수들
	private int prd_id; //상품의 등록번호
	private String prd_kind; //상품의 분류
	private String prd_name; //상품이름
	private String prd_image; //상품이미지명
	private String prd_content; //상품의내용
	private short prd_count; //상품의 재고수량
	private Timestamp reg_date; //상품의 등록날짜
	private String end_time; //상품마감날짜

	//옵션 테이블에 getset될 변수들.
	private int option_id;    //옵션  id 필요한가 ??;
	private String option1;	  //옵션1
	private String option2;		//옵션2
	private int option_count;	//해당 옵션의 수량


	public int getOption_count() {
		return option_count;
	}
	public void setOption_count(int option_count) {
		this.option_count = option_count;
	}
	private int prd_price; //상품가격
	
	public int getOption_id() {
		return option_id;
	}
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getPrd_id() {
		return prd_id;
	}
	public void setPrd_id(int prd_id) {
		this.prd_id = prd_id;
	}
	public String getPrd_kind() {
		return prd_kind;
	}
	public void setPrd_kind(String prd_kind) {
		this.prd_kind = prd_kind;
	}
	public String getPrd_name() {
		return prd_name;
	}
	public void setPrd_name(String prd_name) {
		this.prd_name = prd_name;
	}
	public int getPrd_price() {
		return prd_price;
	}
	public void setPrd_price(int prd_price) {
		this.prd_price = prd_price;
	}
	public short getPrd_count() {
		return prd_count;
	}
	public void setPrd_count(short prd_count) {
		this.prd_count = prd_count;
	}
	public String getPrd_image() {
		return prd_image;
	}
	public void setPrd_image(String prd_image) {
		this.prd_image = prd_image;
	}
	public String getPrd_content() {
		return prd_content;
	}
	public void setPrd_content(String prd_content) {
		this.prd_content = prd_content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}