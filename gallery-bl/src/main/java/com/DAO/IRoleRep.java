package com.DAO;


import com.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRep extends JpaRepository<Role, Long> {

}
