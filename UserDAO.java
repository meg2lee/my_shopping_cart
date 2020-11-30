package com.tj.user.dao;
import java.sql.*;

import com.tj.user.vo.UserVO;

public class UserDAO 
{
	private Connection conn; //sql 데이터베이스와 연결
	private PreparedStatement pstmt; // sql을 mysql에 전달하는 기능 (컴파일 불필요)
	private ResultSet rs; // sql을 결과집합으로 저장
	
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	
	public UserDAO() {
		System.out.println("UserDAO 생성");
	}
	
	private Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // web 실행시 要
			conn = DriverManager.getConnection(url, "root", "tjoeun");
			return conn; // 해당블럭이 실행되면 함수종료
		} catch (Exception sqle) { // 만약 오류가 발생하면
			System.out.println("UserDAO:DB접속오류");
			sqle.printStackTrace();
			return null; // 해당블럭이 실행되고 함수종료
		}finally { // 에러발생 여부와 상관없이 반드시 실행되는 블럭
			
		}// return(함수종료)명령이 없으면 블럭 밖의 코드도 실행됨
	} 
		
	public boolean login(UserVO user) {
		if(getConn()==null) {
			System.err.println("DB 접속오류");
			closeAll();
			return false;
		}
		String sql = "SELECT * FROM user WHERE uid=? AND pwd=MD5(?)"; // mysql실행전에 ?값 대체
		try { // open order(conn -> preparedstatement -> resultset)
			pstmt = conn.prepareStatement(sql);// sql을 mysql에 전달
			pstmt.setString(1, user.getUid()); // 첫번째(1) 물음표를 "user.getUid()"로 바꿔라(depend on 자료형)
			pstmt.setString(2, user.getPwd());// 두번째(2) 물음표를 "user.getPwd()"로 바꿔라(depend on 자료형)
			rs = pstmt.executeQuery(); //mysql에서 가져온 결과집합(resultset)을 참조변수에 저장
			if(rs.next()) {; //rs에 있는 포인터를 한 행씩 내려서 검사해서 데이터 유무에 따라 boolean값 return
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeAll();
		}
	}
			
	private void closeAll() {
		try {
			if(rs!=null) {
				rs.close();
				rs = null;
			} 
			if(pstmt!=null) {
				pstmt.close();
				pstmt = null;
			}
			if(conn!=null) {
				conn.close();
				conn = null;
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

	public static void main(String[] args) 
	{
		UserDAO dao = new UserDAO();
		UserVO user = new UserVO("smith","1110");
		boolean ok = dao.login(user);
		if(ok) System.out.println("로그인 성공");
		else System.err.println("로그인 실패");
		}
}



