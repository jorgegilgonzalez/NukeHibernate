/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.registro.nuke;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonalDao;
import dao.PersonalDaoImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.*;


/**
 * FXML Controller class
 *
 * @author donjo
 */

/*Trabajo sobre el anterior proyecto, modificando los atributos de las clases y mapeandolas para usar Hibernate JPA*/
public class PrimaryController implements Initializable {

    @FXML
    private Button btGuardar;

    @FXML
    private Button btModificar;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btNuevo;

    @FXML
    private Button btDeshacer;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellido;

    @FXML
    private ToggleGroup GrupoEnActivo;

    @FXML
    private RadioButton rdSi;

    @FXML
    private RadioButton rdNo;

    @FXML
    private ComboBox<CargosEntity> comboRango;

    private ObservableList<CargosEntity> listaRangos;//crea una lista con la que alimentar el combobox

    @FXML
    private ComboBox<BasesEntity> comboBase;

    private ObservableList<BasesEntity> listaBases;//crea una lista con la que alimentar el combobox

    @FXML
    private TableView<PersonalEntity> tableviewInfoPersonal;

    private ObservableList<PersonalEntity> listaPersonal;//crea una lista con la que alimentar el combobox

    @FXML
    private TableColumn<PersonalEntity, String> columnaNombre;
    @FXML
    private TableColumn<PersonalEntity, String> columnaApellido;
    @FXML
    private TableColumn<PersonalEntity, Boolean> columnaActivo;
    @FXML
    private TableColumn<PersonalEntity, Integer> columnaRango;
    @FXML
    private TableColumn<PersonalEntity, Integer> columnaBase;

    PersonalEntity personaRetenida = new PersonalEntity(); //guardara el ultimo registro eliminado

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        listaRangos = FXCollections.observableArrayList();//inicializa de esta manera porque observablelist es una interfaz y no se puede instanciar
        OperacionesHibernate.informacionRangos(BDConexionSingleton.getInstancia(), listaRangos);//llamo al estatico que me devuelve la info desde la BD
        comboRango.setItems(listaRangos);//asigna o enlaza la lista al combobox

        listaBases = FXCollections.observableArrayList();
        OperacionesHibernate.informacionBases(BDConexionSingleton.getInstancia(), listaBases);
        comboBase.setItems(listaBases);

        listaPersonal = FXCollections.observableArrayList();
        if (!busqueda) {//si no esta buscando muestra todo

            tableviewInfoPersonal.setItems(listaPersonal);
            OperacionesHibernate.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);

        } else {//si esta buscando pasa true como parametro para que en el metodo cargue los datos de la busqueda

            tableviewInfoPersonal.setItems(listaPersonal);
            OperacionesHibernate.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);

        }


        /*enlazar columnas con cada atributo*/
        columnaNombre.setCellValueFactory(new PropertyValueFactory<PersonalEntity, String>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<PersonalEntity, String>("apellido"));
        columnaActivo.setCellValueFactory(new PropertyValueFactory<PersonalEntity, Boolean>("activo"));
        columnaRango.setCellValueFactory(new PropertyValueFactory<PersonalEntity, Integer>("idCargos"));
        columnaBase.setCellValueFactory(new PropertyValueFactory<PersonalEntity, Integer>("IdBases"));

        //uso de callbacks para recuperar datos de otras clases da problemas para luego realizar las operaciones al no coincidir el tipo de dato con el mapeo
/*
        columnaRango.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PersonalEntity, Integer>, ObservableValue<Integer>>() {
                                             @Override
                                             public ObservableValue<String> call(TableColumn.CellDataFeatures<PersonalEntity, String> param) {
                                                 return new SimpleStringProperty(param.getValue().getCargosById().getCargo());
                                             }
                                         });
        columnaBase.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PersonalEntity, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PersonalEntity, String> param) {
                return new SimpleStringProperty(param.getValue().getBasesById().getCiudad());
            }
        });
*/



        recuperarFilaAction();
    }

    public void recuperarFilaAction() {
        tableviewInfoPersonal.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<PersonalEntity>() {

            @Override
            public void changed(ObservableValue<? extends PersonalEntity> ov, PersonalEntity anterior, PersonalEntity seleccion) {

                if (seleccion != null) {//controlo que haya alguno seleccionado
                    tfId.setText(String.valueOf(seleccion.getId()));
                    tfNombre.setText(seleccion.getNombre());
                    tfApellido.setText(seleccion.getApellido());

                    if (seleccion.getActivo()) {
                        rdSi.setSelected(true);
                    } else {
                        rdNo.setSelected(true);
                    }

                    comboRango.setValue(seleccion.getCargosById());
                    comboBase.setValue(seleccion.getBasesById());

                    btGuardar.setDisable(true);
                    btModificar.setDisable(false);
                    btEliminar.setDisable(false);
                }
            }
        }
        );
    }

    @FXML

    public void limpiarCampos() {

        tfId.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        rdSi.setSelected(true);
        rdNo.setSelected(false);
        comboBase.setValue(null);
        comboRango.setValue(null);

        btGuardar.setDisable(false);
        btModificar.setDisable(true);
        btEliminar.setDisable(true);
        listaPersonal.clear();
        OperacionesHibernate.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);
    }

    @FXML

    public void guardarRegistro() {


        PersonalEntity personal = new PersonalEntity(

                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                comboRango.getSelectionModel().getSelectedItem().getIdCargos(),
                comboBase.getSelectionModel().getSelectedItem().getIdbases());

        if (OperacionesHibernate.insertarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.add(personal);//al anadir el registro actualiza el tableview

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva inserción en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha añadido");;
            aviso.show();
        }
    }

    public void actualizarRegistro() {


        PersonalEntity personal = new PersonalEntity(
               // Integer.parseInt(tfId.getText()),
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                comboRango.getSelectionModel().getSelectedItem().getIdCargos(),
                comboBase.getSelectionModel().getSelectedItem().getIdbases());

        if (OperacionesHibernate.modificarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.set(tableviewInfoPersonal.getSelectionModel().getSelectedIndex(), personal);//al actualizar el registro actualiza el tableview. Al ser un update se usa .set

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva modificación en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha modificado");
            aviso.show();
        }
    }

    public void eliminarRegistro() {


        PersonalDao dao = new PersonalDaoImpl();
        PersonalEntity personal = dao.getPersonalById(Integer.parseInt(tfId.getText()));
        personaRetenida = OperacionesHibernate.retenerUltimoRegistro(personal);//conserva el ultimo registro antes de borrarlo

        if (OperacionesHibernate.borrarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.remove(tableviewInfoPersonal.getSelectionModel().getSelectedIndex());//al borrar el registro actualiza el tableview. solo nmecesito el indice. Al ser un update se usa .remove

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva eliminación en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha borrado");
            aviso.show();
        }
    }

    public void recuperarUltimoRegistro() {

        if (personaRetenida != null) {//controlo que se haya borrado ya un registro para que no lance si no hay ninguna almacenada

            if (OperacionesHibernate.insertarRegistro(BDConexionSingleton.getInstancia(), personaRetenida)) {//si es correcto devuelve true

                listaPersonal.add(personaRetenida);//al anadir el registro actualiza el tableview



                personaRetenida = null;//vacia la variable para que no se pueda reinsertar mas de una vez

                Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
                aviso.setTitle("Nueva inserción en la Base de Datos");
                aviso.setHeaderText("Procedimiento correcto: ");
                aviso.setContentText("Se ha recuperado el ultimo registro borrado");
                aviso.show();
            }
        }

    }

    //cambio de escenas
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void cambiarEscena2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //implementar metodo buscar
    Boolean busqueda = false;

    public void buscarRegistro() {

        listaPersonal.clear();
        
        BasesEntity base = new BasesEntity();
        
       
        if (comboBase.getSelectionModel().getSelectedItem()!=null){
        
        base.setIdbases(comboBase.getSelectionModel().getSelectedItem().getIdbases());
        base.setCiudad(comboBase.getSelectionModel().getSelectedItem().getCiudad());
        base.setCapacidad(comboBase.getSelectionModel().getSelectedItem().getCapacidad());
        System.out.println("impimiento objeto base" + base.toString());
        } else {
            
        base.setIdbases(0);
        base.setCiudad("");
        base.setCapacidad("");
        System.out.println("imprimiendo objeto base VACIO" + base.toString());
        
        }
        
        CargosEntity rango = new CargosEntity();
        
       
        if (comboRango.getSelectionModel().getSelectedItem()!=null){
        
        rango.setIdCargos(comboRango.getSelectionModel().getSelectedItem().getIdCargos());
        rango.setCargo(comboRango.getSelectionModel().getSelectedItem().getCargo());
        rango.setNivelseguridad(comboRango.getSelectionModel().getSelectedItem().getNivelseguridad());
        System.out.println("impimiento objeto rango" + rango.toString());
        } else {
            
        rango.setIdCargos(0);
        rango.setCargo("");
        rango.setNivelseguridad(0);
        System.out.println("imprimiendo objeto rango VACIO" + base.toString());
            
        }
        
        PersonalEntity personal = new PersonalEntity(
                //0,//para la busqueda no voy a usar el id, pero necesito un valor para la creacion del objeto, le asigno 0.
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                rango.getIdCargos(),
                base.getIdbases());
                //comboRango.getSelectionModel().getSelectedItem().getIdCargos(),
                //comboBase.getSelectionModel().getSelectedItem().getIdbases());

                //new Base(comboBase.getSelectionModel().getSelectedItem().getIdCiudad(), comboBase.getSelectionModel().getSelectedItem().getCiudad(), comboBase.getSelectionModel().getSelectedItem().getCapacidad()));

       
        /*Base base = new Base();
        base.setIdCiudad(comboBase.getSelectionModel().getSelectedItem().getIdCiudad());
        base.setCiudad(comboBase.getSelectionModel().getSelectedItem().getCiudad());
        base.setCapacidad(comboBase.getSelectionModel().getSelectedItem().getCapacidad());*/

        System.out.println("personal creado" + personal.toString());
        //tableviewInfoPersonal.setItems(listaPersonal);
        OperacionesHibernate.InformacionBusqueda(BDConexionSingleton.getInstancia(), listaPersonal, personal);

    }

}
