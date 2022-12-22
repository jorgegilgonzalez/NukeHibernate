/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author donjo
 */

@Entity
@IdClass(Personal.class)
@Table(name = "Personal")

public class Personal implements Serializable {//ya no uso esta clase, creo una nueva con sufijo Entities para adaptarla al funcionamiento de Hibernate

/*usare properties para que refresquen los cambios al interfaz FX
    y para poder acceder a ellos desde elementos como tableview*/

    private IntegerProperty id = new SimpleIntegerProperty();

private final StringProperty nombre = new SimpleStringProperty();
private  final StringProperty apellido = new SimpleStringProperty();
private  final BooleanProperty esActivo = new SimpleBooleanProperty();
    private IntegerProperty rango = new SimpleIntegerProperty();
    private IntegerProperty base = new SimpleIntegerProperty();

//estructura de datos con properties

//constructor se pasan tipos primitivos y se asignan a las propiedades

    public Personal(){
    
}

    public Personal(int id,String nombre, String apellido, Boolean esActivo, int rango, int base){

        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setEsActivo(esActivo);
        setRango(rango);
        setBase(base);

    }




//getter y setter de dato primitivo y de Property

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id.get();//devuelve el String no la property
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty(){//devuelve la property en si
        return id;

    }

    @Column(name="nombre")
    public String getNombre() {
        return nombre.get();//devuelve el String no la property
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty(){//devuelve la property en si
        return nombre;

    }
    @Column(name="apellido")
    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public StringProperty apellidoProperty(){
        return apellido;

    }
    @Column(name="activo")
    public boolean getEsActivo() {
        return esActivo.get();
    }

    public void setEsActivo(Boolean activo) {
        this.esActivo.set(activo);
    }

    public BooleanProperty esActivoProperty(){
        return esActivo;

    }

    @Column(name="rango")
    public int getRango() {
        return rango.get();
    }

    public void setRango(int rango) {
        this.rango.set(rango);
    }

    public int getBase() {
        return base.get();
    }

    @Column(name="base")
    public void setBase(int base) {
        this.base.set(base);
    }


    @Override
    public String toString() {
        return "nombre=" + nombre.getValue() + ", apellido=" + apellido.getValue() + ", esActivo=" + esActivo.getValue() + "Rango: " + rango.get() + base.get();
    }


/*
public Personal(int id,String nombre, String apellido, Boolean esActivo, Rango rango, Base base){
    
    this.id = new SimpleIntegerProperty(id);
    this.nombre = new SimpleStringProperty(nombre);
    this.apellido = new SimpleStringProperty(apellido);
    this.esActivo = new SimpleBooleanProperty(esActivo);
    this.rango = rango;
    this.base= base;
        
} 




//getter y setter de dato primitivo y de Property

public int getId() {
        return id.get();//devuelve el String no la property
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);//StringProperty es abstracta asi que se asigna de esta manera
    }

    public IntegerProperty idProperty(){//devuelve la property en si
        return id;
        
    }

    public String getNombre() {
        return nombre.get();//devuelve el String no la property
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);//StringProperty es abstracta asi que se asigna de esta manera
    }

    public StringProperty nombreProperty(){//devuelve la property en si
        return nombre;
        
    }

     public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido = new SimpleStringProperty(apellido);
    }

    public StringProperty apellidoProperty(){
        return apellido;
        
    }
     public boolean getEsActivo() {
        return esActivo.get();
    }

    public void setEsActivo(Boolean activo) {
        this.esActivo = new SimpleBooleanProperty(activo);
    }

    public BooleanProperty esActivoProperty(){
        return esActivo;
        
    }
    
    
    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre.getValue() + ", apellido=" + apellido.getValue() + ", esActivo=" + esActivo.getValue() + "Rango: " + rango.getCargo() + "Niv.Seguridad: " + rango.getNivelSeguridad() + "Ciudad: " + base.getCiudad()+ "Capacidad: " + base.getCapacidad();
    }

    

*/
}
