package com.tj.user.dao;
import java.sql.*;

import com.tj.user.vo.UserVO;

public class UserDAO 
{
	private Connection conn; //sql �����ͺ��̽��� ����
	private PreparedStatement pstmt; // sql�� mysql�� �����ϴ� ��� (������ ���ʿ�)
	private ResultSet rs; // sql�� ����������� ����
	
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	
	public UserDAO() {
		System.out.println("UserDAO ����");
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
		
	public boolean login(UserVO user) {
		if(getConn()==null) {
			System.err.println("DB ���ӿ���");
			closeAll();
			return false;
		}
		String sql = "SELECT * FROM user WHERE uid=? AND pwd=MD5(?)"; // mysql�������� ?�� ��ü
		try { // open order(conn -> preparedstatement -> resultset)
			pstmt = conn.prepareStatement(sql);// sql�� mysql�� ����
			pstmt.setString(1, user.getUid()); // ù��°(1) ����ǥ�� "user.getUid()"�� �ٲ��(depend on �ڷ���)
			pstmt.setString(2, user.getPwd());// �ι�°(2) ����ǥ�� "user.getPwd()"�� �ٲ��(depend on �ڷ���)
			rs = pstmt.executeQuery(); //mysql���� ������ �������(resultset)�� ���������� ����
			if(rs.next()) {; //rs�� �ִ� �����͸� �� �྿ ������ �˻��ؼ� ������ ������ ���� boolean�� return
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
		if(ok) System.out.println("�α��� ����");
		else System.err.println("�α��� ����");
		}
}



