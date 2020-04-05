package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "country")
public class Country implements Serializable {
    private Integer idCountry;
    private String countryName;
    private Collection<Sensor> sensorsByIdCountry;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country", nullable = false)
    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @NotBlank
    @Column(name = "country_name", nullable = false, length = 45)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (idCountry != null ? !idCountry.equals(country.idCountry) : country.idCountry != null) return false;
        if (countryName != null ? !countryName.equals(country.countryName) : country.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry != null ? idCountry.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "countryByCountryId")
    public Collection<Sensor> getSensorsByIdCountry() {
        return sensorsByIdCountry;
    }

    public void setSensorsByIdCountry(Collection<Sensor> sensorsByIdCountry) {
        this.sensorsByIdCountry = sensorsByIdCountry;
    }
}
