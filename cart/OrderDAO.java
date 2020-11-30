package com.tj.session.cart;
import java.sql.*;
import java.util.ArrayList;

import com.tj.user.vo.UserVO;

public class OrderDAO 
{
	private Connection conn; //sql �����ͺ��̽��� ����
	private PreparedStatement ps; // mysql�� sql��� �����ϴ� ��ü
	private ResultSet rs; // sql�� ����������� ����
	
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	
	public OrderDAO() {
		System.out.println("OrderDAO ����");
	}
	
	private Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // web ����� �
			conn = DriverManager.getConnection(url, "root", "tjoeun");
			return conn; // �ش���� ����Ǹ� �Լ�����
		} catch (Exception sqle) { // ���� ������ �߻��ϸ�
			System.out.println("UserDAO:DB���ӿ���");
			sqle.printStackTrace();
			return null; // �ش���� ����ǰ� �Լ�����
		}finally { // �����߻� ���ο� ������� �ݵ�� ����Ǵ� ��
			
		}// return(�Լ�����)����� ������ �� ���� �ڵ嵵 �����
	} 
		
	public boolean save(ArrayList<ItemVO> list, int total, String uid) { //jsp���� parameter�� �޾ƿ�
		if(getConn()==null) {
			System.err.println("DB ���ӿ���");
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
			
			/*�ӽ� ����� auto_increment �ʵ尪 ��������*/
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			int last_id = 0;
			if(rs.next()) {last_id = rs.getInt(1);}
			rs.close();
			
			/*�ܷ�Ű�� ��� ����*/
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
			
			System.out.println("DB ���强��");
			return true;
		}catch(Exception ex) {// ���� ������ �ϳ��� ������ �ִ� ���
			 System.out.println("�������");
			 ex.printStackTrace();
			try {
				conn.rollback(); //�ش� �� �� �ܰ�� backward
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



