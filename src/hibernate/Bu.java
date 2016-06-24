package hibernate;


import org.hibernate.Session;

/**
 * 一级缓存有些时候会对性能产生影响(需要解析)，总体会提高性能
 * @author zjh
 *
 */
public class Bu {
	
	public static void main(String[] args) {
		
		Session session = DBUtil.getSession();
		Students s = (Students) session.get(Students.class,1);
		System.out.println(s.getSname());
		
		session = DBUtil.getSession();
		s = (Students) session.get(Students.class,1);
		System.out.println(s.getSname());
		
	
	}
}
