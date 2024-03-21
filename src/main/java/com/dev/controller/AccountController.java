package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.User;
import com.dev.userService.UserService;

@RestController
@CrossOrigin("*")
public class AccountController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/reg-acc")
	public String regAcc(@RequestBody User user) {
		
		if(userService.chekcingUser(user).isPresent()) return "Error";
		
		return "Your Account is Successfully created with account no. :"+ userService.createAcc(user);
	}
	
	@PutMapping("/add-balanace")
	public int addBalance(@RequestParam("ac") int acc,@RequestParam("bala") int balance,@RequestParam("pan") String Pan) {
		return userService.addBalance(acc, balance, Pan);
	}
	
	@PutMapping("/withdraw")
	public String withDraw(@RequestParam("ac") int acc,@RequestParam("bala") int amountToWithdraw,@RequestParam("pan") String pan) {
		return userService.withDraw(acc, amountToWithdraw, pan);
	}
	
	@GetMapping("/checkBal")
	public int checkBalance(@RequestParam("acc") int acc) {
		return userService.checkBalance(acc);
	}
	
	@PostMapping("/trans")
	public String transferMoney(@RequestParam("ac") int acc,@RequestParam("am") int amount,@RequestParam("acB") int accB) {
		return userService.transfer(acc, amount, accB);
	}
	
//	@DeleteMapping("/delete-acc")
//	public String deleteAcc(@RequestParam("ac") int acc) {
//		return userService.deleteAcc(acc);
//	}
}
