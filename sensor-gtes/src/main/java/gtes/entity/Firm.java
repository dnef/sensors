package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="firm")
public class Firm implements Serializable {
    private Integer idFirm;
    private String nameFirm;
    private Collection<Sensor> sensorsByIdFirm;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_firm", nullable = false)
    public Integer getIdFirm() {
        return idFirm;
    }

    public void setIdFirm(Integer idFirm) {
        this.idFirm = idFirm;
    }

    @Basic
    @NotBlank
    @Column(name = "name_firm", nullable = false, length = 45)
    public String getNameFirm() {
        return nameFirm;
    }

    public void setNameFirm(String nameFirm) {
        this.nameFirm = nameFirm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Firm firm = (Firm) o;

        if (idFirm != null ? !idFirm.equals(firm.idFirm) : firm.idFirm != null) return false;
        if (nameFirm != null ? !nameFirm.equals(firm.nameFirm) : firm.nameFirm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFirm != null ? idFirm.hashCode() : 0;
        result = 31 * result + (nameFirm != null ? nameFirm.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "firmByFirmId")
    public Collection<Sensor> getSensorsByIdFirm() {
        return sensorsByIdFirm;
    }

    public void setSensorsByIdFirm(Collection<Sensor> sensorsByIdFirm) {
        this.sensorsByIdFirm = sensorsByIdFirm;
    }
}
