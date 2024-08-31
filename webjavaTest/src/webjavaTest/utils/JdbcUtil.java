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
	//加载驱动 当使用此类时自动加载执行
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获得连接
	public static void getConnction(){
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/face_login?characterEncoding=utf8", "root", "2023202816");
		} catch (SQLException e) {
			System.out.println("连接异常");
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行增  删 ,改语句sql
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
	 * 执行查询语句sql
	 * @throws SQLException 
	 */
	public static ResultSet executeQuerySql(String sql,Object[] obj) throws SQLException{
	      getConnction();//链接数据库
	      ps =   connection.prepareStatement(sql);
	     if(obj!=null){//向sql传入参数
	    	 for(int i=0;i<obj.length;i++){
		    	 ps.setObject(i+1, obj[i]);
		     }
	     }
	     rs =   ps.executeQuery();//返回查询结果
		return rs;
	}
	
	/**
	 * 关闭链接
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
