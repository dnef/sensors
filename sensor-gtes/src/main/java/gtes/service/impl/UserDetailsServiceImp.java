package gtes.service.impl;

import gtes.entity.User;
import gtes.entity.UserProfile;
import gtes.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
    static final Logger logger = LogManager.getLogger(UserDetailsServiceImp.class.getName());
    @Autowired
    private UserService userService;



    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);
        logger.info("User : {}", user);
        if(user==null){
            logger.info("User not found !!!!");
            throw new UsernameNotFoundException(ssoId+"Username not found");
        }else{
            return new org.springframework.security.core.userdetails.User(user.getSsoId(),user.getPassword(),
                    user.isEnabled(),true,true,true,getGrantedAuthorities(user));
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserProfile userProfile : user.getUserProfiles()){
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }
}
