package com.DAO;

import com.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRep extends JpaRepository<AppUser, String> {

}
