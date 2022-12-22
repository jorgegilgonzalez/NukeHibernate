package modelo;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bases")
public class BasesEntity {//version de Bases para mapeo JDBC
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idbases")
    private int idbases;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "capacidad")
    private String capacidad;

    public BasesEntity() {
        super();
    }

    public BasesEntity(int idbases, String ciudad, String capacidad,List<PersonalEntity> listaPersonal) {
        super();
        this.idbases = idbases;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.listaPersonal = listaPersonal;
    }

    public int getIdbases() {
        return idbases;
    }

    public void setIdbases(int idbases) {
        this.idbases = idbases;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    @OneToMany(mappedBy = "cargosById", cascade = CascadeType.ALL)//esto hace referencia al
    private List<PersonalEntity> listaPersonal;

    public List<PersonalEntity> getListaPersonal() {
        return listaPersonal;
    }

    public void setListaPersonal(List<PersonalEntity> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasesEntity that = (BasesEntity) o;

        if (idbases != that.idbases) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (capacidad != null ? !capacidad.equals(that.capacidad) : that.capacidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idbases;
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (capacidad != null ? capacidad.hashCode() : 0);
        return result;
    }
    */
    @Override
    public String toString() {
        return getCiudad();
    }
}
