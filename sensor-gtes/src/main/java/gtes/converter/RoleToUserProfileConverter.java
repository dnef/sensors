package gtes.converter;

import gtes.entity.UserProfile;
import gtes.service.UserProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
    static final Logger logger = LogManager.getLogger(RoleToUserProfileConverter.class);
    @Autowired
    UserProfileService userProfileService;
    public UserProfile convert(Object element){
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = userProfileService.findById(id);
        logger.info("Profile : {}",profile);
        return profile;
    }

}
