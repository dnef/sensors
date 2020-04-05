package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="unit")
public class Unit implements Serializable {
    private Integer idUnit;
    private String unitName;
    private Collection<Sensor> sensorsByIdUnit;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_unit", nullable = false)
    public Integer getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    @Basic
    @NotBlank
    @Column(name = "unit_name", nullable = false, length = 45)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        if (idUnit != null ? !idUnit.equals(unit.idUnit) : unit.idUnit != null) return false;
        if (unitName != null ? !unitName.equals(unit.unitName) : unit.unitName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUnit != null ? idUnit.hashCode() : 0;
        result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "unitByUnitId")
    public Collection<Sensor> getSensorsByIdUnit() {
        return sensorsByIdUnit;
    }

    public void setSensorsByIdUnit(Collection<Sensor> sensorsByIdUnit) {
        this.sensorsByIdUnit = sensorsByIdUnit;
    }
}
