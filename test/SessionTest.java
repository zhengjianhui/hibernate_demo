package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class SessionTest {

	@Test
	public void openSession() {
		// 获取配置对象
		Configuration config = new Configuration().configure();
		// 获取服务注册对象
		ServiceRegistry serviceRegistry 
			= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		// 建立SeeiongFactory 工厂对象  将服务对象传入
		SessionFactory factory = config.buildSessionFactory(serviceRegistry);
		// 获得Session 对象
		Session session = factory.openSession();
		Session session2 = factory.openSession();
		// 判断两个引用 是否指向同一个对象 答案为false  应为每次都创建一个新对象
		System.out.println(session == session2);
		
		if(session != null) {
			System.out.println("session 创建成功");
			session.close();
		}
		
	}
	
	@Test
	public void getCurrentSession() {
		// 获取配置对象
		Configuration config = new Configuration().configure();
		// 获取服务注册对象
		ServiceRegistry serviceRegistry 
			= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		// 建立SeeiongFactory 工厂对象  将服务对象传入
		SessionFactory factory = config.buildSessionFactory(serviceRegistry);
		// 获得Session 对象
		
		// 使用getCurrentSession 方法需要在 xml 中配置 否则抛出  No CurrentSessionContext configured!
		// 
		Session session = factory.getCurrentSession();
		Session session2 = factory.getCurrentSession();
		// 判断两个引用 是否指向同一个对象 答案为true  应为是个单例
		System.out.println(session == session2);
		if(session != null) {
			System.out.println("getCurrentSession 创建成功");
		}
	}
}
