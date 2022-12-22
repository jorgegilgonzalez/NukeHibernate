package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.PersonalEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateConf;

public class PersonalDaoImpl implements PersonalDao { //implementa el interfaz y defino todos sus metodos

	private SessionFactory factory = HibernateConf.getFactory();

	@Override
	public void savePersonal(PersonalEntity personal) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {

			transaction = session.beginTransaction();
			session.save(personal);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}

	@Override
	public PersonalEntity getPersonalById(int id) {
		Transaction transaction = null;
		PersonalEntity personal = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			personal = session.get(PersonalEntity.class, id);
			transaction.commit();
			
				
		} catch (Exception e) {
			
			if(transaction != null)
				transaction.rollback();
		}
		return personal;
	}

	@Override
	public void updatePersonal(PersonalEntity personal) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(personal);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
	
	@Override
	public void deletePersonalById(int id) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			PersonalEntity personal = session.get(PersonalEntity.class, id);
			session.delete(personal);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ObservableList<PersonalEntity> getAllPersonal() {
		Transaction transaction = null;
		ObservableList<PersonalEntity> personal = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			personal = FXCollections.observableArrayList(session.createQuery("from PersonalEntity").list());
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)

				transaction.rollback();
		}
		return personal;
	}


	@Override//creo nuevo metodo que devuelve el personal que cumpla con los filtros y lo anado al interfaz PersonalDao
	public ObservableList<PersonalEntity> getPersonalFiltrado(PersonalEntity personal) {
		Transaction transaction = null;
		ObservableList<PersonalEntity> listaPersonal = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			String hql = "from PersonalEntity p";//uso sentencias HQL y creo un filtro a medida que se va construyendo según los campos usados en la busqueda
			hql += " where ";
			if(personal.getNombre()!=""){
				hql += " p.nombre = :nombre ";
			}

			if (personal.getNombre()!="" && personal.getApellido() !="")  { //si tiene algun otro filtro a continuacion anade un and para continuar la sentencia
				hql += (" and ");
			}
			if(personal.getApellido()!=""){
				hql += " p.apellido = :apellido ";
			}

			if (personal.getNombre()!="" || personal.getApellido() !="") { //si tiene algun otro filtro a continuacion anade un and para continuar la sentencia
				hql += (" and ");
			}
			if(personal.getActivo()!=null){//este campo siempre tiene seleccion, siempre devuelve un booleano y sirve de control intermedio para la estructura
				hql += " p.activo = :activo ";
			}

			if (personal.getIdCargos() != 0 || personal.getIdBases() != 0) { //si tiene algun otro filtro a continuacion anade un and para continuar la sentencia
				hql += (" and ");
			}

			if (personal.getIdCargos() != 0) {

				hql += "p.idCargos = :idCargos";
			}
			if (personal.getIdCargos() !=0 && personal.getIdBases() != 0) { //si tiene algun otro filtro a continuacion anade un and para continuar la sentencia
				hql += (" and ");
			}
			if (personal.getIdBases() != 0) {

				hql += "p.IdBases = :IdBases";
			}

			listaPersonal = FXCollections.observableArrayList(session.createQuery(hql) //mete en una observable el resultado del query
					.setProperties(personal)//anade las propiedades
					.list());//saca la lista


			System.out.println(personal);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)

				transaction.rollback();
		}
		return listaPersonal;
	}

}
