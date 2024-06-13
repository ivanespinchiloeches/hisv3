package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.RoleRepository;
import com.ivan.thehealthsoftwarecompany.hisv3.service.RolesService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAllByOrderByNameAsc() {
        return roleRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Role> findAll(Sort sortStrategy) {
        return roleRepository.findAll(sortStrategy);
    }
}
