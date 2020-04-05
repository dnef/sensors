package gtes.validator;

import gtes.entity.Sensor;
import gtes.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberSensorValidator implements ConstraintValidator<UniqueColumn,Sensor> {

    @Autowired
    private SensorService sensorService;
    @Qualifier(value = "sensorService")
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @Autowired
    MessageSource messageSource;

    @Override
    public void initialize(UniqueColumn uniqueColumn) {

    }

    @Override
    public boolean isValid(Sensor sensor, ConstraintValidatorContext constraintValidatorContext) {
//        List<Sensor> sensorListByNumber = this.sensorService.findSensorByNumber(numberSensor);
        //TODO при редактировании срабатывает
        if (!sensorService.isNumberSensorUnique(sensor.getIdSensor(),sensor.getSensorNumb())){
//            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "{UniqueColumn.sensor.sensorNumb}")
                    .addPropertyNode("sensorNumb")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
