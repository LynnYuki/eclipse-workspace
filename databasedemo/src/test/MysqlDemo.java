package test;
/**
 *  import mysql-connector-java-version.bin.jar
 */
import java.sql.*;

public class MysqlDemo {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysq://localhost:3306/test?autoReconnect=true&useSSL=false";//关闭SSL连接方式
	public static final String USER = "root";
	public static final String PASSWD = "123456";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM TEST";
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("开始连接Mysql数据库...");
			connection = DriverManager.getConnection(URL, USER, PASSWD);
			if (connection != null) {
				System.out.println("数据库连接成功！");
				System.out.println("创建PrepareStatement对象");
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					System.out.println("id: " + resultSet.getString("id") + "name: " + resultSet.getString("name")
							+ "age: " + resultSet.getString("age"));
				}
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				} else if (preparedStatement != null) {
					preparedStatement.close();
				} else if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		System.out.println("断开连接。");
	}
}
