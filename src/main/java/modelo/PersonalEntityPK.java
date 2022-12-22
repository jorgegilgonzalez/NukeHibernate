package modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class PersonalEntityPK implements Serializable {//generada automáticamente, pero no la uso
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idCargos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCargos;
    @Column(name = "idBases")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBases;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCargos() {
        return idCargos;
    }

    public void setIdCargos(int idCargos) {
        this.idCargos = idCargos;
    }

    public int getIdBases() {
        return idBases;
    }

    public void setIdBases(int idBases) {
        this.idBases = idBases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalEntityPK that = (PersonalEntityPK) o;

        if (id != that.id) return false;
        if (idCargos != that.idCargos) return false;
        if (idBases != that.idBases) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idCargos;
        result = 31 * result + idBases;
        return result;
    }
}
