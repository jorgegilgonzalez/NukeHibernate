package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.CargosEntity;
import modelo.Personal;
import modelo.PersonalEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateConf;

public class CargosDaoImpl implements CargosDao {
	
	
	private SessionFactory factory = HibernateConf.getFactory();


	@Override
	public void saveCargos(CargosEntity personal) {

	}

	@Override
	public CargosEntity getCargosById(int id) {
		return null;
	}



	@Override
	public ObservableList<CargosEntity> getAllCargos() {

		Transaction transaction = null;
		ObservableList<CargosEntity> cargos = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			cargos = FXCollections.observableArrayList(session.createQuery("from CargosEntity").list());
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)

				transaction.rollback();
		}
		return cargos;
	}


	@Override
	public void updateCargos(CargosEntity cargos) {

	}

	@Override
	public void deleteCargosById(int id) {

	}
}
