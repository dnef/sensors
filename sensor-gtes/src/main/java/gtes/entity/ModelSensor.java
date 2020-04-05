package gtes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "model_sensor", schema = "zmb_pribor", catalog = "")
public class ModelSensor implements Serializable {
    private Integer idModel;
    private String modelName;
    private String modelVersion;
    private Integer typesensId;
    private Typesens typesensByTypesensId;
    private List<Sensor> sensorsByIdModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model", nullable = false)
    public Integer getIdModel() {
        return idModel;
    }

    public void setIdModel(Integer idModel) {
        this.idModel = idModel;
    }

    @Basic
    @NotBlank
    @Column(name = "model_name", nullable = false, length = 45)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @NotBlank
    @Column(name = "model_version", nullable = true, length = 45)
    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    @Basic
    @NotNull
    @Column(name = "typesens_id", nullable = false)
    public Integer getTypesensId() {
        return typesensId;
    }

    public void setTypesensId(Integer typesensId) {
        this.typesensId = typesensId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelSensor that = (ModelSensor) o;

        if (idModel != null ? !idModel.equals(that.idModel) : that.idModel != null) return false;
        if (modelName != null ? !modelName.equals(that.modelName) : that.modelName != null) return false;
        if (modelVersion != null ? !modelVersion.equals(that.modelVersion) : that.modelVersion != null) return false;
        if (typesensId != null ? !typesensId.equals(that.typesensId) : that.typesensId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idModel != null ? idModel.hashCode() : 0;
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        result = 31 * result + (modelVersion != null ? modelVersion.hashCode() : 0);
        result = 31 * result + (typesensId != null ? typesensId.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST },fetch = FetchType.EAGER)
    @JoinColumn(name = "typesens_id", referencedColumnName = "id_typesens", nullable = false, insertable = false, updatable = false)
    public Typesens getTypesensByTypesensId() {
        return typesensByTypesensId;
    }

    public void setTypesensByTypesensId(Typesens typesensByTypesensId) {
        this.typesensByTypesensId = typesensByTypesensId;
    }

    @OneToMany(mappedBy = "modelSensorByModelId")
    public List<Sensor> getSensorsByIdModel() {
        return sensorsByIdModel;
    }

    public void setSensorsByIdModel(List<Sensor> sensorsByIdModel) {
        this.sensorsByIdModel = sensorsByIdModel;
    }


}
