package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "location_install", schema = "zmb_pribor", catalog = "")
public class LocationInstall implements Serializable {
    private Integer idLocationInstall;
    private Integer locationId;
    private Integer sensorId;
    private Location locationByLocationId;
    private Sensor sensorBySensorId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location_install", nullable = false)
    public Integer getIdLocationInstall() {
        return idLocationInstall;
    }

    public void setIdLocationInstall(Integer idLocationInstall) {
        this.idLocationInstall = idLocationInstall;
    }

    @Basic
    @NotNull
    @Column(name = "location_id", nullable = false)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @NotNull
    @Column(name = "sensor_id", nullable = false)
    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationInstall that = (LocationInstall) o;

        if (idLocationInstall != null ? !idLocationInstall.equals(that.idLocationInstall) : that.idLocationInstall != null)
            return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (sensorId != null ? !sensorId.equals(that.sensorId) : that.sensorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLocationInstall != null ? idLocationInstall.hashCode() : 0;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (sensorId != null ? sensorId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id_location", nullable = false, insertable = false, updatable = false)
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
