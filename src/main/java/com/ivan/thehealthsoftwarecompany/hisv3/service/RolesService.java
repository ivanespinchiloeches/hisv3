package com.ivan.thehealthsoftwarecompany.hisv3.service;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface RolesService {

    Set<Role> findAllByOrderByNameAsc();
    List<Role> findAll(Sort sortStrategy);
}
