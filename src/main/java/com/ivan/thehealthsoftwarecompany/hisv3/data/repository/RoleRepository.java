package com.ivan.thehealthsoftwarecompany.hisv3.data.repository;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Set<Role> findAllByOrderByNameAsc();
    @Override
    @NotNull
    List<Role> findAll(@NotNull Sort sortStrategy);
}
