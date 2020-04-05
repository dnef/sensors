package gtes.dao;

import gtes.entity.UserProfile;

import java.util.List;

public interface UserProfileDAO {
    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
