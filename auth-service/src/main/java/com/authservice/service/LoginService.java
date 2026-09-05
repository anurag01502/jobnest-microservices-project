package com.authservice.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.authservice.dao.LoginDao;
import com.authservice.dao.UserDao;
import com.authservice.dto.RefreshTokenResponse;
import com.authservice.exception.CustomRuntimeException;
import com.authservice.model.LoginRequest;
import com.authservice.model.UserModel;
import com.authservice.security.JwtUtil;


@Service
public class LoginService  {

	
	private final LoginDao loginDao;
	private final JwtUtil jwtUtil;
	private final UserDao userDao;
	public LoginService(LoginDao loginDao,JwtUtil jwtUtil,UserDao userDao)
	{
		this.loginDao=loginDao;
		this.jwtUtil=jwtUtil;
		this.userDao=userDao;
		
	}
	
	public Map<Object, String> login(LoginRequest loginRequest) {

	    Map<Object, String> loginResponse = new ConcurrentHashMap<>();

	    UserModel user = loginDao.login(loginRequest);

	    String token = jwtUtil.generateToken(
	            user.getEmail(),
	            user.getRole()
	    );

	    loginResponse.put("message", "Successfully LoggedIn!");
	    loginResponse.put("token", token);

	    return loginResponse;
	}
	
	public RefreshTokenResponse refreshToken(String refreshToken) {

	    // 1. Validate refresh token
	    if (!jwtUtil.validateRefreshToken(refreshToken)) {
	        throw new CustomRuntimeException(
	                "Invalid refresh token",
	                HttpStatus.UNAUTHORIZED
	        );
	    }

	    // 2. Extract username
	    String username = jwtUtil.extractUsername(refreshToken);

	    // 3. Verify refresh token exists and isn't revoked
	    // Your existing refresh-token/blacklist logic here

	    // 4. Get current user from DB
	    UserModel user = userDao.findByIdentifier(username);

	    if (user == null) {
	        throw new CustomRuntimeException(
	                "User not found",
	                HttpStatus.NOT_FOUND
	        );
	    }

	    // 5. Generate access token using CURRENT DB role
	    String accessToken = jwtUtil.generateToken(
	            user.getEmail(),
	            user.getRole()
	    );

	    // 6. Rotate refresh token
	    String newRefreshToken = jwtUtil.refreshToken(user.getEmail());

	    return new RefreshTokenResponse(
	            accessToken,
	            newRefreshToken
	    );
	}

}
