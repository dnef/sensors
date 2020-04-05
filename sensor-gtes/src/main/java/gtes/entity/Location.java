package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="location")
public class Location implements Serializable {
    private Integer idLocation;
    private String nameLoc;
    private String serNumber;
    private String invNumber;
    private Boolean workLoc;
    private Collection<Archive> archivesByIdLocation;
    private Collection<LocationInstall> locationInstallsByIdLocation;
    private Collection<Sensor> sensorsByIdLocation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location", nullable = false)
    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    @Basic
    @NotBlank
    @Column(name = "name_loc", nullable = false, length = 50)
    public String getNameLoc() {
        return nameLoc;
    }

    public void setNameLoc(String nameLoc) {
        this.nameLoc = nameLoc;
    }

    @Basic
    @Column(name = "ser_number", nullable = false, length = 50)
    public String getSerNumber() {
        return serNumber;
    }
    public void setSerNumber(String serNumber){this.serNumber=serNumber;}

    @Basic
    @Column(name = "inv_number", nullable = false, length = 50)
    public String getInvNumber() {
        return invNumber;
    }
    public void setInvNumber(String invNumber){this.invNumber=invNumber;}


    @Basic
    @Column(name = "work_loc", nullable = false)
    public Boolean getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(Boolean workLoc) {
        this.workLoc = workLoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (idLocation != null ? !idLocation.equals(location.idLocation) : location.idLocation != null) return false;
        if (nameLoc != null ? !nameLoc.equals(location.nameLoc) : location.nameLoc != null) return false;
        if (nameLoc != null ? !serNumber.equals(location.serNumber) : location.serNumber != null) return false;
        if (nameLoc != null ? !invNumber.equals(location.invNumber) : location.invNumber != null) return false;
        if (workLoc != null ? !workLoc.equals(location.workLoc) : location.workLoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLocation != null ? idLocation.hashCode() : 0;
        result = 31 * result + (nameLoc != null ? nameLoc.hashCode() : 0);
        result = 31 * result + (serNumber != null ? serNumber.hashCode() : 0);
        result = 31 * result + (invNumber != null ? invNumber.hashCode() : 0);
        result = 31 * result + (workLoc != null ? workLoc.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<Archive> getArchivesByIdLocation() {
        return archivesByIdLocation;
    }

    public void setArchivesByIdLocation(Collection<Archive> archivesByIdLocation) {
        this.archivesByIdLocation = archivesByIdLocation;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<LocationInstall> getLocationInstallsByIdLocation() {
        return locationInstallsByIdLocation;
    }

    public void setLocationInstallsByIdLocation(Collection<LocationInstall> locationInstallsByIdLocation) {
        this.locationInstallsByIdLocation = locationInstallsByIdLocation;
    }
    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<Sensor> getSensorsByIdLocation() {
        return sensorsByIdLocation;
    }

    public void setSensorsByIdLocation(Collection<Sensor> sensorsByIdLocation) {
        this.sensorsByIdLocation = sensorsByIdLocation;
    }
}
