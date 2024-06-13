package com.ivan.thehealthsoftwarecompany.hisv3.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class UserRoleId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -4852114321413928931L;
    @NotNull
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleId entity = (UserRoleId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
*/
}