package com.habit.tracker.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.habit.tracker.dto.AuthenticationRequestDTO;
import com.habit.tracker.dto.AuthenticationResponseDTO;
import com.habit.tracker.util.JwtUtil;

@RestController
public class HomeResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String home() {
		return "<h1> Welcome </h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1> Welcome User </h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1> Welcome User </h1>";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
					, authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password.",e);
		}
		final UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
	}

}
