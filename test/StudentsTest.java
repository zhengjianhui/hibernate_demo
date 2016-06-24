package test;




import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import hibernate.Students;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentsTest {
    
	
	// Session 工厂对象
	private SessionFactory sessionFactory;
	// session 数据库会话对象 
	private Session session;
	// 事物对象
	private Transaction transaction;
	
	@Before
	public void init() {
		// 创建配置对象
		Configuration configuration = new Configuration().configure();
		
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		
		// 创建Session工厂对象
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		// 获取会话对象
		session = sessionFactory.openSession();
		
		// 开启事物
		transaction = session.beginTransaction();
		
	}
	
	@After
	public void after() {
//		transaction.commit(); // 提交事物
		session.close();	// 关闭会话
		sessionFactory.close(); // 关闭session 对象
	}
	@Test
	public void test() {
		Students students = new Students();
		students.setSid(3);
		students.setSname("周芷若");
		students.setGender("女");
		students.setBirthday(new Timestamp(new Date().getTime()));
		students.setAddress("峨眉山");
		
		/**
		 * 开启自动事物 Hibernate 默认为非自动事物
		 * 调用 doWork() 方法 重写 Work接口
		 * 重写后不用调用 session.beginTransaction();
		 */
		session.doWork(new Work() {
			// 参数为 Connection  可以通过 connection 设置事物自动提交
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.setAutoCommit(true);
			}
		});
		
		// 保存对象
		session.save(students);
		
		// 调用save 后 sql语句并没有被真正发出 需要调用flush()方法将数据输出 
		session.flush();
	}
	
	
}
