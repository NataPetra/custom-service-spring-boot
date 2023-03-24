package it.academy.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user_role")
public class AppUserRole implements Serializable {

    public AppUserRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "appUserRole")
    private List<AppUser> appUsers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUserRole that = (AppUserRole) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return roleName != null ? roleName.equals(that.roleName) : that.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppUserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
