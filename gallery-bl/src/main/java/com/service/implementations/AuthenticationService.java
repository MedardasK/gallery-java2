//package com.service.implementations;
//
//import com.config.TokenProvider;
//import com.entity.User;
//import com.payload.AuthCookie;
//import com.payload.AuthToken;
//import com.service.IAuthenticationService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService implements IAuthenticationService {
//
//    private AuthenticationManager authenticationManager;
//    private TokenProvider jwtTokenUtil;
//
//    public AuthenticationService(AuthenticationManager authenticationManager,
//                                 TokenProvider jwtTokenUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    public AuthToken loginUser(User loginUser){
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginUser.getUsername(),
//                        loginUser.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        final String token = jwtTokenUtil.generateToken(authentication);
//        return new AuthToken(token);
//    }
//
//    public AuthToken refreshToken(AuthCookie authCookie) {
//        User user = new User();
//        user.setUsername(jwtTokenUtil.getUsernameFromToken(authCookie.getUsername()));
//        if (jwtTokenUtil.validateToken(authCookie.getCookie(), (UserDetails) user)) {
//            Authentication authentication = null;
//            final String token = jwtTokenUtil.generateToken(authentication);
//            return new AuthToken(token);
//        } else {
//            return null;
//        }
//    }
//
//}
