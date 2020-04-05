package gtes.entity;

import gtes.validator.UniqueColumn;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name="sensor")
@UniqueColumn
public class Sensor implements Serializable {
    private Integer idSensor;
    @NotNull
    private Integer modelId;
    @NotBlank
//    @UniqueColumn
    private String sensorNumb;
    private String inventoryNumb;
    @NotNull
    private Integer unitId;
    @NotNull
    private Integer rangeMin;
    @NotNull
    private Integer rangeMax;
    @NotNull
    private Integer countryId;
    @NotNull
    private Integer firmId;
    @NotNull
    @PastOrPresent(message = "Дата некорректна")
    private LocalDate dateManufacture;
    @NotNull
    @PastOrPresent(message = "Дата некорректна")
    private LocalDate dateVerification;
    @NotNull
    private Integer intervalVerification;
    @NotNull
    private Boolean verification;
    @NotNull
    private Boolean passport;
    private String note;

    private Integer locationId;
    private Collection<Archive> archivesByIdSensor;
    private Collection<LocationInstall> locationInstallsByIdSensor;
    private ModelSensor modelSensorByModelId;
    private Unit unitByUnitId;
    private Country countryByCountryId;
    private Firm firmByFirmId;
    private Location locationByLocationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor", nullable = false)
    public Integer getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {
        this.idSensor = idSensor;
    }

    @Basic
    @Column(name = "model_id", nullable = false)
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @Basic
    @NotBlank
    @Column(name = "sensor_numb", unique = true,nullable = false, length = 45)
    public String getSensorNumb() {
        return sensorNumb;
    }

    public void setSensorNumb(String sensorNumb) {
        this.sensorNumb = sensorNumb;
    }

    @Basic
    @Column(name = "inventory_numb", nullable = true, length = 45)
    public String getInventoryNumb() {
        return inventoryNumb;
    }

    public void setInventoryNumb(String inventoryNumb) {
        this.inventoryNumb = inventoryNumb;
    }

    @Basic
    @Column(name = "unit_id", nullable = false)
    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "range_min", nullable = false)
    public Integer getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Integer rangeMin) {
        this.rangeMin = rangeMin;
    }

    @Basic
    @Column(name = "range_max", nullable = false)
    public Integer getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Integer rangeMax) {
        this.rangeMax = rangeMax;
    }

    @Basic
    @NotNull
    @Column(name = "country_id", nullable = true)
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "firm_id", nullable = true)
    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }


    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_manufacture", nullable = false)
    public LocalDate getDateManufacture() {
        return dateManufacture;
    }

    public void setDateManufacture(LocalDate dataManufacture) {
        this.dateManufacture = dataManufacture;
    }

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_verification", nullable = false)
    public LocalDate getDateVerification() {
        return dateVerification;
    }

    public void setDateVerification(LocalDate dateVerification) {
        this.dateVerification = dateVerification;
    }

    @Basic
    @Column(name = "interval_verification", nullable = false)
    public Integer getIntervalVerification() {
        return intervalVerification;
    }

    public void setIntervalVerification(Integer intervalVerification) {
        this.intervalVerification = intervalVerification;
    }

    @Basic
    @Column(name = "verification", nullable = false)
    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    @Basic
    @Column(name = "passport", nullable = true)
    public Boolean getPassport() {
        return passport;
    }

    public void setPassport(Boolean passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "location_id", nullable = true)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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

        Sensor sensor = (Sensor) o;

        if (idSensor != null ? !idSensor.equals(sensor.idSensor) : sensor.idSensor != null) return false;
        if (modelId != null ? !modelId.equals(sensor.modelId) : sensor.modelId != null) return false;
        if (sensorNumb != null ? !sensorNumb.equals(sensor.sensorNumb) : sensor.sensorNumb != null) return false;
        if (inventoryNumb != null ? !inventoryNumb.equals(sensor.inventoryNumb) : sensor.inventoryNumb != null)
            return false;
        if (unitId != null ? !unitId.equals(sensor.unitId) : sensor.unitId != null) return false;
        if (rangeMin != null ? !rangeMin.equals(sensor.rangeMin) : sensor.rangeMin != null) return false;
        if (rangeMax != null ? !rangeMax.equals(sensor.rangeMax) : sensor.rangeMax != null) return false;
        if (countryId != null ? !countryId.equals(sensor.countryId) : sensor.countryId != null) return false;
        if (firmId != null ? !firmId.equals(sensor.firmId) : sensor.firmId != null) return false;
        if (dateManufacture != null ? !dateManufacture.equals(sensor.dateManufacture) : sensor.dateManufacture != null)
            return false;
        if (dateVerification != null ? !dateVerification.equals(sensor.dateVerification) : sensor.dateVerification != null)
            return false;
        if (intervalVerification != null ? !intervalVerification.equals(sensor.intervalVerification) : sensor.intervalVerification != null)
            return false;
        if (verification != null ? !verification.equals(sensor.verification) : sensor.verification != null)
            return false;
        if (passport != null ? !passport.equals(sensor.passport) : sensor.passport != null) return false;
        if (locationId != null ? !locationId.equals(sensor.locationId) : sensor.locationId != null) return false;
        if (note != null ? !note.equals(sensor.note) : sensor.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSensor != null ? idSensor.hashCode() : 0;
        result = 31 * result + (modelId != null ? modelId.hashCode() : 0);
        result = 31 * result + (sensorNumb != null ? sensorNumb.hashCode() : 0);
        result = 31 * result + (inventoryNumb != null ? inventoryNumb.hashCode() : 0);
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (rangeMin != null ? rangeMin.hashCode() : 0);
        result = 31 * result + (rangeMax != null ? rangeMax.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (firmId != null ? firmId.hashCode() : 0);
        result = 31 * result + (dateManufacture != null ? dateManufacture.hashCode() : 0);
        result = 31 * result + (dateVerification != null ? dateVerification.hashCode() : 0);
        result = 31 * result + (intervalVerification != null ? intervalVerification.hashCode() : 0);
        result = 31 * result + (verification != null ? verification.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sensorBySensorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Archive> getArchivesByIdSensor() {
        return archivesByIdSensor;
    }

    public void setArchivesByIdSensor(Collection<Archive> archivesByIdSensor) {
        this.archivesByIdSensor = archivesByIdSensor;
    }

    @OneToMany(mappedBy = "sensorBySensorId")
    public Collection<LocationInstall> getLocationInstallsByIdSensor() {
        return locationInstallsByIdSensor;
    }

    public void setLocationInstallsByIdSensor(Collection<LocationInstall> locationInstallsByIdSensor) {
        this.locationInstallsByIdSensor = locationInstallsByIdSensor;
    }

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id_model", nullable = false, insertable = false, updatable = false)
    public ModelSensor getModelSensorByModelId() {
        return modelSensorByModelId;
    }

    public void setModelSensorByModelId(ModelSensor modelSensorByModelId) {
        this.modelSensorByModelId = modelSensorByModelId;
    }

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id_unit", nullable = false, insertable = false, updatable = false)

    public Unit getUnitByUnitId() {
        return unitByUnitId;
    }

    public void setUnitByUnitId(Unit unitByUnitId) {
        this.unitByUnitId = unitByUnitId;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id_country", insertable = false, updatable = false)
    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id_firm", insertable = false, updatable = false)
    public Firm getFirmByFirmId() {
        return firmByFirmId;
    }

    public void setFirmByFirmId(Firm firmByFirmId) {
        this.firmByFirmId = firmByFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id_location", insertable=false, updatable = false)
    public Location getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(Location locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }
}
