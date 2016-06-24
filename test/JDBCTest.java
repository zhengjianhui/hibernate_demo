package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;


public class JDBCTest {

	@Test
	public void test() {
		// 建立Connection接口
		Connection conn = null;
		// 将主类配置jar包中的类加载到内存中
		try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("加载成功");
		                    // 加载主类成功后 使Connection接口 通过DriverManager 获取一个
		                     // 实例 该实例用于与数据库通信
		                    conn
		                         = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/hibernate",
		"root",
		"root"
		);
		System.out.println("连接成功");

		// 建立Statement 对象用于向服务器发送SQL语句
		Statement stat
		= conn.createStatement();
		String sql = "INSERT tt (name,age,salary) VALUES  ('asd',100,9999.99) ";

		stat.executeUpdate(sql);


		} catch (ClassNotFoundException e) {
		// 加载失败则抛出加载错误异常上报调用者
		throw new RuntimeException("加载失败",e);
		} catch (SQLException e) {
		// 连接失败，则抛出异常上报调用者
		throw new RuntimeException("连接失败",e);
		}

		}
	}

