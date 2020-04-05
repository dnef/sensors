package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="typesens")
public class Typesens implements Serializable {
    private Integer idTypesens;
    private String nameType;
    private Collection<ModelSensor> modelSensorsByIdTypesens;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typesens", nullable = false)
    public Integer getIdTypesens() {
        return idTypesens;
    }

    public void setIdTypesens(Integer idTypesens) {
        this.idTypesens = idTypesens;
    }

    @Basic
    @NotBlank
    @Column(name = "name_type", nullable = false, length = 45)
    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Typesens typesens = (Typesens) o;

        if (idTypesens != null ? !idTypesens.equals(typesens.idTypesens) : typesens.idTypesens != null) return false;
        if (nameType != null ? !nameType.equals(typesens.nameType) : typesens.nameType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypesens != null ? idTypesens.hashCode() : 0;
        result = 31 * result + (nameType != null ? nameType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "typesensByTypesensId")
    public Collection<ModelSensor> getModelSensorsByIdTypesens() {
        return modelSensorsByIdTypesens;
    }

    public void setModelSensorsByIdTypesens(Collection<ModelSensor> modelSensorsByIdTypesens) {
        this.modelSensorsByIdTypesens = modelSensorsByIdTypesens;
    }
}
