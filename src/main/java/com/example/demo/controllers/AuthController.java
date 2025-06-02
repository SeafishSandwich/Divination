package com.example.demo.controllers;

import com.example.demo.model.UserService;
import com.example.demo.JDBC.entity.User;
import com.example.demo.JDBC.entity.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/users")

public class AuthController {
	@Autowired
	private UserService userService;


	@PostMapping("/signup")
	public String signup(@RequestParam("email") String email,
						 @RequestParam("password") String password,
						 @RequestParam("username") String user_name) {
		User user = new User(user_name, email, password);
		userService.registerUser(user);
		return "redirect:/login_div.html";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email,
						@RequestParam String password,
						HttpSession session) {
		System.out.println("Login controller called: " + email);
		UserSession userSession = new UserSession();
		boolean isValid = userService.checkLogin(email, password);
		User nowUser = userService.getNowUser(email,password);
		userSession.setUser(nowUser.getId(), nowUser.getUser_name());
		session.setAttribute("userSession", userSession);
		return isValid ? "redirect:/hexaFirstStage.html" : "redirect:/error.html";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Clear session data
		return "redirect:/login.html"; // Redirect back to login page
	}


}
