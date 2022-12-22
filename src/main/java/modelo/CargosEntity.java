package modelo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargos")
public class CargosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCargos")
    private int idCargos;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "nivelseguridad")
    private int nivelseguridad;


    public CargosEntity() {
        super();
    }

    public CargosEntity(int id_cargos, String cargo, List<PersonalEntity> listaPersonal) {
        super();
        idCargos = id_cargos;
        this.cargo = cargo;
        this.listaPersonal = listaPersonal;
    }

    public int getIdCargos() {
        return idCargos;
    }

    public void setIdCargos(int idCargos) {
        this.idCargos = idCargos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNivelseguridad() {
        return nivelseguridad;
    }

    public void setNivelseguridad(int nivelseguridad) {
        this.nivelseguridad = nivelseguridad;
    }

    @OneToMany(mappedBy = "basesById", cascade = CascadeType.ALL)//esto hace referencia al
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

        CargosEntity that = (CargosEntity) o;

        if (idCargos != that.idCargos) return false;
        if (nivelseguridad != that.nivelseguridad) return false;
        if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCargos;
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + nivelseguridad;
        return result;
    }
    */

    @Override
    public String toString() {
        return getCargo();
    }
}
