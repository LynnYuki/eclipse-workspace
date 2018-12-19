package test;
/**
 * import ojdbc5.jar
 */
import java.sql.*;
public class OracleDemo {
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/lynnyuki";
	public static final String USER = "root";
	public static final String PASSWD = "123456";
	
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM TEST";
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("��ʼ����Oracle���ݿ�...");
			connection = DriverManager.getConnection(URL, USER, PASSWD);
			if (connection != null) {
				System.out.println("���ݿ����ӳɹ���");
				System.out.println("����PrepareStatement����");
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					System.out.println("id: " + resultSet.getString("id") + "name: " + resultSet.getString("name")
							+ "age: " + resultSet.getString("age"));
				}
			}//�ر�����
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
		System.out.println("�Ͽ����ӡ�");
	}
}