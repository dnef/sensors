package gtes.entity;

import gtes.validator.UniqueSSO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="app_user")
@UniqueSSO
public class User implements Serializable {
    public interface UpdateUser {
    }
    public interface UpdateAdmin {
    }
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
//    @UniqueSSO//как вариант можно использовать org.checkerframework.common.aliasing.qual.Unique Unique
    @Column(name="sso_id",unique = true, nullable=false)
    private String ssoId;

    @NotBlank
//    @NotBlank(groups = UpdateAdmin.class)
    @NotBlank(groups = UpdateUser.class)
    @Column(name="password", nullable=false)
    private String password;

    @NotBlank
//    @NotBlank(groups = UpdateAdmin.class)
    @NotBlank(groups = UpdateUser.class)
    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotBlank
//    @NotBlank(groups = UpdateAdmin.class)
    @NotBlank(groups = UpdateUser.class)
    @Column(name="last_name", nullable=false)
    private String lastName;


    @Column(name="patronymic", nullable=false)
    private String patronymic;

    @NotBlank
//    @Email(groups = UpdateAdmin.class)
    @Email(groups = UpdateUser.class)
    @Column(name="email", nullable=false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;



    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_user_profile",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_profile_id") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    /*
     * DO-NOT-INCLUDE passwords in toString function.
     * It is done here just for convenience purpose.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName +"patronymic"+patronymic
                + ", email=" + email + "]";
    }



}