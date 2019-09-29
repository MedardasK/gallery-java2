package com.service.implementations;


import com.DAO.IUserRep;
import com.entity.Role;
import com.entity.User;
import com.service.IRoleService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IUserRep userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public String delete(Long id) {
        try {
            userRepository.deleteById(id);
            return "Success";
        } catch (Exception exception){
            return "Failed";
        }

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {

        if (userRepository.findByUsername(user.getUsername().toLowerCase()) == null){
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByRoleName("USER"));
            newUser.setRoles(roles);
            return userRepository.save(newUser);
        } else {
            return null;
        }
    }
}