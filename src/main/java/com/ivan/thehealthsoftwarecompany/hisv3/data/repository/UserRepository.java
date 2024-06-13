package com.ivan.thehealthsoftwarecompany.hisv3.data.repository;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query(" select u.id, u.email,  u.enabled, u.username from User u" )
    List<Tuple> findAllWithoutRoles(Sort sortCriteria);

}
