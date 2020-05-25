package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import hibernate.table.MUser;

@Service
public class HibernateDao {

	SessionFactory factory = null;
	Session session = null;

	public Transaction beginTransaction() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();

		return session.beginTransaction();
	}

	public void insertIntoMUser(MUser user) {
		Transaction transaction = beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
		factory.close();
	}

}
