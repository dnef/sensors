package gtes.dao;


import gtes.entity.User;


import java.util.List;

public interface UserDAO {
    User findById(int id);

    User findBySSO(String sso);

    void save(User user);
    void updateSave(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}
