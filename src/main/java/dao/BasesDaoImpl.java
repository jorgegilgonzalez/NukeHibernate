package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.BasesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateConf;

public class BasesDaoImpl implements BasesDao {
	
	
	private SessionFactory factory = HibernateConf.getFactory();


	@Override
	public void saveBases(BasesEntity personal) {

	}

	@Override
	public BasesEntity getBasesById(int id) {
		return null;
	}



	@Override
	public ObservableList<BasesEntity> getAllBases() {

		Transaction transaction = null;
		ObservableList<BasesEntity> Bases = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Bases = FXCollections.observableArrayList(session.createQuery("from BasesEntity").list());
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)

				transaction.rollback();
		}
		return Bases;
	}


	@Override
	public void updateBases(BasesEntity Bases) {

	}

	@Override
	public void deleteBasesById(int id) {

	}
}
