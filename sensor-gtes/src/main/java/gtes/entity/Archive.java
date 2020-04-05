package gtes.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="archive")
public class Archive implements Serializable {
    private Integer idArchive;
    private Integer locationId;
    private Integer sensorId;
    @PastOrPresent
    private LocalDateTime installDate;
    private String note;
    private Location locationByLocationId;
    private Sensor sensorBySensorId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archive", nullable = false)
    public Integer getIdArchive() {
        return idArchive;
    }

    public void setIdArchive(Integer idArchive) {
        this.idArchive = idArchive;
    }

    @Basic
    @Column(name = "location_id", nullable = false)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "sensor_id", nullable = false)
    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "install_date", nullable = false)
    public LocalDateTime  getInstallDate() {
        return installDate;
    }

    public void setInstallDate(LocalDateTime  installDate) {
        this.installDate = installDate;
    }

    @Basic
    @Column(name = "note", nullable = true, length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archive archive = (Archive) o;

        if (idArchive != null ? !idArchive.equals(archive.idArchive) : archive.idArchive != null) return false;
        if (locationId != null ? !locationId.equals(archive.locationId) : archive.locationId != null) return false;
        if (sensorId != null ? !sensorId.equals(archive.sensorId) : archive.sensorId != null) return false;
        if (installDate != null ? !installDate.equals(archive.installDate) : archive.installDate != null) return false;
        if (note != null ? !note.equals(archive.note) : archive.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArchive != null ? idArchive.hashCode() : 0;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (sensorId != null ? sensorId.hashCode() : 0);
        result = 31 * result + (installDate != null ? installDate.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id_location", nullable = false,insertable = false, updatable = false)
    public Location getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(Location locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id_sensor", nullable = false, insertable = false, updatable = false)
    public Sensor getSensorBySensorId() {
        return sensorBySensorId;
    }

    public void setSensorBySensorId(Sensor sensorBySensorId) {
        this.sensorBySensorId = sensorBySensorId;
    }
}
