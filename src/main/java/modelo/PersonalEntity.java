package modelo;

import javafx.beans.property.BooleanProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personal")
//@SecondaryTable(name = "bases", pkJoinColumns=@PrimaryKeyJoinColumn(name="idbases"))//une tablas externas para acceder a sus campos
//@SecondaryTable(name = "cargos", pkJoinColumns=@PrimaryKeyJoinColumn(name="idCargos"))

public class PersonalEntity implements Serializable {//mapeo con JPA, transformo atributos de tipo properties en tipo primitivo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name="idCargos")
    private int idCargos;

    @Column(name="IdBases")
    private int IdBases;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCargos", insertable = false, updatable = false)
    private CargosEntity cargosById;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idBases", insertable = false , updatable = false)
    private BasesEntity basesById;

    /* no funciona necesito un column para mapear no solo el valor
    @Formula("(select ciudad from bases where idbases = idBases)")//uso la caracteristica de formula para poder usar un dato de otra tabla referenciada por clave ajena
    private String nombreBase;//aloja el nombre de la ciudad de la base que est'a en otra tabla
    */

/*
    @Column(table="bases" ,name="ciudad")//usa tabla secundaria definida
    private String ciudad;

    @Column(table="cargos" ,name="cargo")//usa tabla secundaria definida
    private String cargo;
*/
    public PersonalEntity(){
    }

    public PersonalEntity(String nombre, String apellido, Boolean esActivo, int rango, int base){

        //setId(id); lo saco del constructor porque tiene autoincrement
        setNombre(nombre);
        setApellido(apellido);
        setActivo(esActivo);
        setIdCargos(rango);
        setIdBases(base);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public int getIdCargos() {
        return idCargos;
    }

    public void setIdCargos(int idCargos) {
        this.idCargos = idCargos;
    }

    public int getIdBases() {
        return IdBases;
    }

    public void setIdBases(int idBases) {
        IdBases = idBases;
    }

    public CargosEntity getCargosById() {
        return cargosById;
    }

    public void setCargosById(CargosEntity cargosById) {
        this.cargosById = cargosById;
    }

    public BasesEntity getBasesById() {
        return basesById;
    }

    public void setBasesById(BasesEntity basesById) {
        this.basesById = basesById;
    }

    @Override
    public String toString() {
        return "PersonalEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", activo=" + activo +
                ", idCargos=" + idCargos +
                ", IdBases=" + IdBases +
                ", cargosById=" + cargosById +
                ", basesById=" + basesById +
                '}';
    }
}
