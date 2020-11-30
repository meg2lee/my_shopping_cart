package com.tj.session.cart;
import java.sql.*;
import java.util.ArrayList;

import com.tj.user.vo.UserVO;

public class OrderDAO 
{
	private Connection conn; //sql 데이터베이스와 연결
	private PreparedStatement ps; // mysql에 sql명령 전달하는 객체
	private ResultSet rs; // sql을 결과집합으로 저장
	
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	
	public OrderDAO() {
		System.out.println("OrderDAO 생성");
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
		
	public boolean save(ArrayList<ItemVO> list, int total, String uid) { //jsp에서 parameter로 받아옴
		if(getConn()==null) {
			System.err.println("DB 접속오류");
			closeAll();
			return false;
		}
	
		try {
			conn.setAutoCommit(false);
			String sql= "INSERT INTO order_tb VALUES(null,?,now(),?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2,total);
			ps.executeUpdate();
			
			/*임시 저장된 auto_increment 필드값 가져오기*/
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			int last_id = 0;
			if(rs.next()) {last_id = rs.getInt(1);}
			rs.close();
			
			/*외래키를 잠시 해제*/
			Statement stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=0");
			stmt.close();
			
			sql = "INSERT INTO order_item VALUES(?,?,?,?,?)";			
			ps = conn.prepareStatement(sql);
			
			for(int i=0;i<list.size();i++) {
				ItemVO item = list.get(i);
				ps.setInt(1,last_id);
				ps.setString(2, item.getCategory());
				ps.setString(3, item.getItemName());
				ps.setInt(4, item.getPrice());
				ps.setInt(5, item.getCnt());
				ps.executeUpdate();
			}
			stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=1");
			conn.commit();
			
			System.out.println("DB 저장성공");
			return true;
		}catch(Exception ex) {// 위의 로직에 하나라도 에러가 있는 경우
			 System.out.println("저장실패");
			 ex.printStackTrace();
			try {
				conn.rollback(); //해당 블럭 전 단계로 backward
			} catch (SQLException e) { 
				e.printStackTrace();
			}
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
			if(ps!=null) {
				ps.close();
				ps = null;
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

}



