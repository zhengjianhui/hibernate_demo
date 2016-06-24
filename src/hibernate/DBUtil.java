package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DBUtil {

	private static Configuration config;
	private static ServiceRegistry serviceRegistry;
	private static SessionFactory factory;
	private static Session session;
	private static Transaction transaction;
	static {
		// 获取配置对象
		config = new Configuration().configure();
		// 获取服务注册对象
		serviceRegistry 
			= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		// 建立SeeiongFactory 工厂对象  将服务对象传入
		factory = config.buildSessionFactory(serviceRegistry);
	
	}
	
	public static Session getSession() {
		session = factory.openSession();
		transaction = session.beginTransaction();
		return session;
		
	}
	
	public static void commit() {
		transaction.commit();
	}
	
}
