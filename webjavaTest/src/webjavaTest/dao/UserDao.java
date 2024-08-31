package webjavaTest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import webjavaTest.bean.User;

//import org.apache.catalina.User;

import webjavaTest.utils.JdbcUtil;

public class UserDao {
public void insertUser(String username,String usersex){
		
		
		try {
			String sql = "insert into user (username,sex) values (?,?)";
			Object[] obj = {username,usersex};
			JdbcUtil.executeUpdateSql(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public User getUserByName(String user_info) {
		ResultSet rs = null;
		User user = new User();
		try {
			String sql = "select * from user where username=?";
			Object[] obj = {user_info};
			rs=JdbcUtil.executeQuerySql(sql, obj);
			
			while(rs.next()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setSex(rs.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
		
	}

}
