//package com.controller;
//
//
//import com.entity.User;
//import com.payload.AuthCookie;
//import com.service.IAuthenticationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class AuthenticationController {
//
//    private IAuthenticationService authenticationService;
//
//    public AuthenticationController(IAuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginUser) throws AuthenticationException {
//        if (loginUser.getUsername() != null && loginUser.getUsername() != null) {
//            return ResponseEntity.ok(authenticationService.loginUser(loginUser));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody AuthCookie authCookie) throws AuthenticationException {
//        if (authCookie.getCookie() != null && authCookie.getUsername() != null){
//            return ResponseEntity.ok(authenticationService.refreshToken(authCookie));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}