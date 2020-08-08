package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import hibernate.table.MUser;
@Service
public class HibernateDao {

	public void insertIntoMUser(MUser user) {
		Session session = null;
		try {
			session = com.TravellingWorld.common.resource.Transaction.getHibernateSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error occured while saving user details : "+e);
		} finally {
			session.close();
		}
	}

}
