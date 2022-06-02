/**
 * 
 */
package demo.ftmk.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import demo.ftmk.connector.DbConnector;

/**
 * @author M.S.I
 *
 */
public class TypeDataManager {

	private DbConnector dbConn;
	
	public TypeDataManager() {
		
		dbConn = new DbConnector();
	}
	
	public int addType(String name)
			throws ClassNotFoundException, SQLException {
		
		int success = 0;
		String sql = "insert into type(Name) values (?)";
		Connection conn = dbConn.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, name);
		success = preparedStatement.executeUpdate();
		conn.close();
		return success;
		}
	
	public ArrayList<Type> getType(ArrayList<Type> typeList) 
			throws SQLException, ClassNotFoundException{
		
		Connection conn = dbConn.getConnection();
		String sql = "select * from type";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		
		ResultSet result = preparedStatement.executeQuery();
		while(result.next()) {
			Type type = new Type();
			int typeId = result.getInt("TypeId");
			String name = result.getString("Name");
			typeList.add(type);
			
		}
		
		preparedStatement.close();
		conn.close();
		return typeList;
	}
}
