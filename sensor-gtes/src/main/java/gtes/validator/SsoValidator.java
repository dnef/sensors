package gtes.validator;

import gtes.entity.User;
import gtes.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SsoValidator implements ConstraintValidator<UniqueSSO, User> {
    static final Logger logger = LogManager.getLogger(SsoValidator.class.getName());
    @Autowired
    private UserService userService;

    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    MessageSource messageSource;

    @Override
    public void initialize(UniqueSSO uniqueSSO) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
//        String ssoId = user.getSsoId();
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            logger.info("SSO существует",user.getSsoId());
            //TODO:херня какая-то
//            HibernateConstraintValidatorContext hcvc= constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
//            hcvc.disableDefaultConstraintViolation();
//            hcvc.addExpressionVariable("ssoId",ssoId)
//                    .buildConstraintViolationWithTemplate("{UniqueSSO.user.ssoId}")
//                    .addConstraintViolation();
//           FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "{UniqueSSO.user.ssoId}")
                    .addPropertyNode("ssoId")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
