package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RolesServiceImplTest {

    @Test
    void findAllByOrderByNameAsc() {
        RoleRepository roleRepository = mock(RoleRepository.class);
        Role role1 = new Role(1L, "bRole");
        Role role2 = new Role(2L, "aRole");
  
        HashSet<Role> expectedRoles = new HashSet<>(Arrays.asList(role1, role2));
        when(roleRepository.findAllByOrderByNameAsc()).thenReturn(expectedRoles);

        RolesServiceImpl service = new RolesServiceImpl(roleRepository);
        Set<Role> actualRoles = service.findAllByOrderByNameAsc();

        assertEquals(expectedRoles, actualRoles);
        verify(roleRepository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    void findAll() {
        RoleRepository roleRepository = mock(RoleRepository.class);
        Role role1 = new Role(1L,"bRole");
        Role role2 = new Role(2L, "aRole");
        Sort sort = Sort.by("name");
  
        List<Role> expectedRoles = new ArrayList<>(Arrays.asList(role1, role2));
        when(roleRepository.findAll(sort)).thenReturn(expectedRoles);

        RolesServiceImpl service = new RolesServiceImpl(roleRepository);
        List<Role> actualRoles = service.findAll(sort);

        assertEquals(expectedRoles, actualRoles);
        verify(roleRepository, times(1)).findAll(sort);
    }
}