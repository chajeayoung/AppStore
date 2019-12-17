package apple.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;

public class MngrDBBean {
	// MngrDBBean 전역 객체 생성 <- 한개의 객체만 생성해서 공유
	private static MngrDBBean instance = new MngrDBBean();

	// MngrDBBean객체를 리턴하는 메소드
	public static MngrDBBean getInstance() {
		return instance;
	}

	private MngrDBBean() {
	}

	// 커넥션 풀에서 커넥션 객체를 얻어내는 메소드
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/shoppingmall");
		return ds.getConnection();
	}

	// 관리자 인증 메소드
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		String orgPass = passwd;
		String shaPass = DigestUtils.sha256Hex(orgPass);
		
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select managerPasswd from manager where managerId = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("managerPasswd");
				if (shaPass.equals(dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// 상품 등록 메소드..
	public void insertPrd(MngrDataBean prd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// prd 테이블 인서트문
			String sql = "insert into prd(prd_kind,prd_name,prd_price,";
			sql += "prd_image,";
			sql += "prd_content, reg_date, end_time, prd_count) values (?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prd.getPrd_kind());
			pstmt.setString(2, prd.getPrd_name());
			pstmt.setInt(3, prd.getPrd_price());
			pstmt.setString(4, prd.getPrd_image());
			pstmt.setString(5, prd.getPrd_content());
			pstmt.setTimestamp(6, prd.getReg_date());
			pstmt.setString(7, prd.getEnd_time());
			pstmt.setShort(8, prd.getPrd_count());
			pstmt.executeUpdate();

//			// 옵션 테이블 인서트문.
//			String sql2 = "insert into option(option1, option2) " + "values (?,?)";
//			pstmt = conn.prepareStatement(sql2);
//
//			pstmt.setString(1, prd.getOption1());
//			pstmt.setString(2, prd.getOption2());
//			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 이미등록된 상품을 검증
	public int registedPrdconfirm(String kind, String prdName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = getConnection();

			String sql = "select prd_name from prd ";
			sql += " where prd_kind = ? and prd_name = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, prdName);

			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1; // 해당 상품이 이미 등록되어 있음
			else
				x = -1;// 해당 상품이 이미 등록되어 있지 않음

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// 전체등록된 상품의 수를 얻어내는 메소드
	public int getPrdCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from prd");
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// 해당 분류의 상품의 수를 얻어내는 메소드
	public int getPrdCount(String prd_kind) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		int kind = Integer.parseInt(prd_kind);

		try {
			conn = getConnection();
			String query = "select count(*) from prd where prd_kind=" + kind;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// 상품의 제목을 얻어냄
	public String getPrdName(int prd_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String x = "";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select prd_name from prd where prd_id = " + prd_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getString(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// 분류별또는 전체등록된 상품의 정보를 얻어내는 메소드
	public List<MngrDataBean> getPrds(String prd_kind) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MngrDataBean> prdList = null;

		try {
			conn = getConnection();

			String sql1 = "select * from prd";
			String sql2 = "select * from prd ";
			sql2 += "where prd_kind = ? order by reg_date desc";

			if (prd_kind.equals("all") || prd_kind.equals("")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, prd_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				prdList = new ArrayList<MngrDataBean>();
				do {
					MngrDataBean prd = new MngrDataBean();

					prd.setPrd_id(rs.getInt("prd_id"));
					prd.setPrd_kind(rs.getString("prd_kind"));
					prd.setPrd_name(rs.getString("prd_name"));
					prd.setPrd_price(rs.getInt("prd_price"));
					prd.setPrd_count(rs.getShort("prd_count"));
					prd.setPrd_image(rs.getString("prd_image"));
					prd.setReg_date(rs.getTimestamp("reg_date"));
					prd.setEnd_time(rs.getString("end_time"));
					prd.setPrd_content(rs.getString("prd_content"));

					prdList.add(prd);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return prdList;
	}

	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간상품목록을 얻어내는 메소드
	public MngrDataBean[] getPrds(String prd_kind, int count) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean prdList[] = null;
		int i = 0;

		try {
			conn = getConnection();

			String sql = "select * from prd where prd_kind = ? ";
			sql += "order by reg_date desc limit ?,?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prd_kind);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, count);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				prdList = new MngrDataBean[count];
				do {
					MngrDataBean prd = new MngrDataBean();
					prd.setPrd_id(rs.getInt("prd_id"));
					prd.setPrd_kind(rs.getString("prd_kind"));
					prd.setPrd_name(rs.getString("prd_name"));
					prd.setPrd_price(rs.getInt("prd_price"));
					prd.setPrd_count(rs.getShort("prd_count"));
					prd.setPrd_image(rs.getString("prd_image"));
					prd.setReg_date(rs.getTimestamp("reg_date"));
					prd.setEnd_time(rs.getString("end_time"));
					prd.setPrd_content(rs.getString("prd_content"));

					prdList[i] = prd;

					i++;
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return prdList;
	}

	// prdId에 해당하는 상품의 정보를 얻어내는 메소드로
	// 등록된 상품을 수정하기 위해 수정폼으로 읽어들기이기 위한 메소드
	public MngrDataBean getPrd(int prdId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean prd = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from prd where prd_id = ?");
			pstmt.setInt(1, prdId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				prd = new MngrDataBean();

				prd.setPrd_kind(rs.getString("prd_kind"));
				prd.setPrd_name(rs.getString("prd_name"));
				prd.setPrd_price(rs.getInt("prd_price"));
				prd.setPrd_count(rs.getShort("prd_count"));
				prd.setPrd_image(rs.getString("prd_image"));
				prd.setPrd_content(rs.getString("prd_content"));
				prd.setEnd_time(rs.getString("end_time"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return prd;
	}

	// 등록된 상품의 정보를 수정시 사용하는 메소드
	public void updatePrd(MngrDataBean prd, int prdId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn = getConnection();

			sql = "update prd set prd_kind=?,prd_name=?,prd_price=?";
			sql += ",prd_count=?,author=?,publishing_com=?,publishing_date=?";
			sql += ",prd_image=?,prd_content=?,discount_rate=?";
			sql += " where prd_id=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, prd.getPrd_kind());
			pstmt.setString(2, prd.getPrd_name());
			pstmt.setInt(3, prd.getPrd_price());
			pstmt.setShort(4, prd.getPrd_count());
			pstmt.setString(5, prd.getPrd_image());
			pstmt.setString(6, prd.getPrd_content());
			pstmt.setInt(7, prdId);

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// prdId에 해당하는 상품의 정보를 삭제시 사용하는 메소드
	public void deletePrd(int prdId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("delete from prd where prd_id=?");
			pstmt.setInt(1, prdId);

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// prdid에 해당하는 옵션들을 가져오기
	public List<MngrDataBean> getPrdOptions(int prdId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MngrDataBean> optionList = null;


		

		try {
			conn = getConnection();
			String sql = "select option1, option2 from option where prd_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prdId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				optionList = new ArrayList<MngrDataBean>();
				do {
					MngrDataBean prd = new MngrDataBean();

					prd.setOption_id(rs.getInt("option_id"));
					prd.setPrd_id(rs.getInt("prd_id"));
					prd.setOption1(rs.getString("prd_kind"));
					prd.setOption2(rs.getString("prd_name"));
					prd.setOption_count(rs.getInt("option_count"));
			
					optionList.add(prd);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return optionList;
	}
}