package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.TravellingWorld.common.resource.TransactionManager;

import hibernate.table.MUser;
@Service
public class HibernateDao {

	static TransactionManager transactionManager = null;
	static {
		transactionManager = TransactionManager.getInstance();
	}
	public static void insertIntoMUser(MUser user) {
		Session session = null;
		try {
			session = transactionManager.getHibernateSession();
			Transaction transactionObj = session.beginTransaction();
			session.save(user);
			transactionObj.commit();
			System.out.println("Registration done");
		} catch (Exception e) {
			System.out.println("Error occured while saving user details : "+e);
		} finally {
			session.close();
		}
	}
}
