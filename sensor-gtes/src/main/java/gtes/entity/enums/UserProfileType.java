package gtes.entity.enums;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
    USER("USER"),
    KIP("KIP"),
    ADMIN("ADMIN"),
    DBA("DBA");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}
