package gtes.service.impl;

import gtes.dao.UserDAO;
import gtes.entity.User;
import gtes.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findBySSO(String sso) {
        return userDAO.findBySSO(sso);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user) {
        User userEntity = userDAO.findById(user.getId());
        if (userEntity!=null) {
            userEntity.setSsoId(user.getSsoId());
            if (!user.getPassword().equals(userEntity.getPassword())) {
                userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPatronymic(user.getPatronymic());
        userEntity.setEmail(user.getEmail());
        userEntity.setEnabled(user.isEnabled());
        userEntity.setUserProfiles(user.getUserProfiles());
        userDAO.updateSave(userEntity);

    }

    @Override
    public void deleteUserBySSO(String sso) {
        userDAO.deleteBySSO(sso);

    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return (user==null||((id!=null)&&(user.getId()==id)));
    }
}
