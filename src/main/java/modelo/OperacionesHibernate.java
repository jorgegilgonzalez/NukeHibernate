package modelo;

import dao.*;
import javafx.collections.ObservableList;



/**
 *
 * @author donjo
 */
public class OperacionesHibernate {//creacion de metodos para hibernate + JPA

    public static void informacionRangos(BDConexionSingleton conexion, ObservableList<CargosEntity> listaRangos) {

        try {
            CargosDao dao = new CargosDaoImpl();
            listaRangos.addAll(dao.getAllCargos());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void informacionBases(BDConexionSingleton conexion, ObservableList<BasesEntity> listaBases) {

        try {
            BasesDao dao = new BasesDaoImpl();
            listaBases.addAll(dao.getAllBases());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void InformacionTabla(BDConexionSingleton conexion, ObservableList<PersonalEntity> listaPersonal) {

        try {
            PersonalDao dao = new PersonalDaoImpl();
            listaPersonal.addAll(dao.getAllPersonal());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean insertarRegistro(BDConexionSingleton conexion, PersonalEntity personal) {

        boolean insertado = false;

        try {
            PersonalDao dao = new PersonalDaoImpl();
            dao.savePersonal(personal);
            insertado = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return insertado;
        }
        return insertado;
    }

    public static boolean modificarRegistro(BDConexionSingleton conexion, PersonalEntity personal) {

        boolean modificado = false;

        try {
            PersonalDao dao = new PersonalDaoImpl();
            dao.updatePersonal(personal);
            modificado = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return modificado;
        }
        return modificado;
    }

    public static boolean borrarRegistro(BDConexionSingleton conexion, PersonalEntity personal) {

        boolean borrado = false;

        try {
            PersonalDao dao = new PersonalDaoImpl();
            dao.deletePersonalById(personal.getId());
            borrado = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return borrado;
        }
        return borrado;
    }

    public static PersonalEntity retenerUltimoRegistro(PersonalEntity personal) {

        return personal;
    }

    public static void InformacionBusqueda(BDConexionSingleton conexion, ObservableList<PersonalEntity> datosPersonal, PersonalEntity personal) {


        try {
            PersonalDao dao = new PersonalDaoImpl();
            datosPersonal.addAll(dao.getPersonalFiltrado(personal));
            System.out.println(datosPersonal);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}



