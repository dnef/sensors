package gtes.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "disposal")
public class Disposal implements Serializable {
    private int idDisposal;
    private String typeSensor;
    private String modelSensor;
    private String versionSensor;
    private String numberSensor;
    private String inventoryNumberSensor;
    @PastOrPresent
    private LocalDateTime dateArchive;
    @PastOrPresent
    private LocalDateTime dateDisposal;

    private String note;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disposal")
    public int getIdDisposal() {
        return idDisposal;
    }

    public void setIdDisposal(int idDisposal) {
        this.idDisposal = idDisposal;
    }

    @Basic
    @Column(name = "type_sensor")
    public String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    @Basic
    @Column(name = "model_sensor")
    public String getModelSensor() {
        return modelSensor;
    }

    public void setModelSensor(String modelSensor) {
        this.modelSensor = modelSensor;
    }

    @Basic
    @Column(name = "version_sensor")
    public String getVersionSensor() {
        return versionSensor;
    }

    public void setVersionSensor(String versionSensor) {
        this.versionSensor = versionSensor;
    }

    @Basic
    @Column(name = "number_sensor")
    public String getNumberSensor() {
        return numberSensor;
    }

    public void setNumberSensor(String numberSensor) {
        this.numberSensor = numberSensor;
    }

    @Basic
    @Column(name = "inventory_number_sensor")
    public String getInventoryNumberSensor() {
        return inventoryNumberSensor;
    }

    public void setInventoryNumberSensor(String inventoryNumberSensor) {
        this.inventoryNumberSensor = inventoryNumberSensor;
    }
    @Basic
    @Column(name = "date_archive")
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "date_disposal")
    public LocalDateTime getDateDisposal() {
        return dateDisposal;
    }

    public void setDateDisposal(LocalDateTime dateDisposal) {
        this.dateDisposal = dateDisposal;
    }

    @Basic
    @Column(name = "note")
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
        Disposal disposal = (Disposal) o;
        return idDisposal == disposal.idDisposal &&
                Objects.equals(typeSensor, disposal.typeSensor) &&
                Objects.equals(modelSensor, disposal.modelSensor) &&
                Objects.equals(versionSensor, disposal.versionSensor) &&
                Objects.equals(numberSensor, disposal.numberSensor) &&
                Objects.equals(inventoryNumberSensor, disposal.inventoryNumberSensor) &&
                Objects.equals(dateDisposal, disposal.dateArchive) &&
                Objects.equals(dateDisposal, disposal.dateDisposal) &&
                Objects.equals(note, disposal.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDisposal, typeSensor, modelSensor, versionSensor, numberSensor, inventoryNumberSensor,dateArchive, dateDisposal, note);
    }
}
