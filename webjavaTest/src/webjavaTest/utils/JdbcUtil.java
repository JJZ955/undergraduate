package webjavaTest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	private static Connection connection = null;
	private static PreparedStatement ps = null;
	static ResultSet rs = null;
	//�������� ��ʹ�ô���ʱ�Զ�����ִ��
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�������
	public static void getConnction(){
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/face_login?characterEncoding=utf8", "root", "2023202816");
		} catch (SQLException e) {
			System.out.println("�����쳣");
			e.printStackTrace();
		}
	}
	
	/**
	 * ִ����  ɾ ,�����sql
	 * @throws SQLException 
	 */
	
	public static void executeUpdateSql(String sql,Object[] obj) throws SQLException{
		JdbcUtil.getConnction();
		
			ps=connection.prepareStatement(sql);
			for(int i =0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			
			ps.executeUpdate();
			
		
	}
	
	
	/**
	 * ִ�в�ѯ���sql
	 * @throws SQLException 
	 */
	public static ResultSet executeQuerySql(String sql,Object[] obj) throws SQLException{
	      getConnction();//�������ݿ�
	      ps =   connection.prepareStatement(sql);
	     if(obj!=null){//��sql�������
	    	 for(int i=0;i<obj.length;i++){
		    	 ps.setObject(i+1, obj[i]);
		     }
	     }
	     rs =   ps.executeQuery();//���ز�ѯ���
		return rs;
	}
	
	/**
	 * �ر�����
	 * @throws SQLException 
	 */
	public static void closeAll() throws SQLException{
		if(rs!=null){
			rs.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
	
	

	

}
