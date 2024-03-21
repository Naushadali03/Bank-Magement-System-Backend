package com.dev.userService;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Account;
import com.dev.model.User;
import com.dev.repository.AccountRepository;
import com.dev.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;

	
//	public int accCreact(User user) {
//		Account account = new Account();
//		Random random =  new Random();
//		int acc = random.nextInt(5);
//		account.setAcc(acc);
//		account.setType("Saving");
//		account.setBalance(0);
//		account.setUserId(user);
//		Optional<Account> acList = accountRepository.findByAcc(acc);
//		while(acList.isPresent()) {
//			acc=random.nextInt(1,1000000);
//			acList = accountRepository.findByAcc(acc);
//		}
//		account.setAcc(acc);
//		accountRepository.save(account);
//		return account.getAcc();
//	}
	
	public int createAcc(User user) {
		Account account = new Account();
		Random random =  new Random();
		int acc = random.nextInt(1,100000);
		account.setAcc(acc);
		account.setType("Saving");
		account.setBalance(0);
		account.setUserId(user);
		Optional<Account> acList = accountRepository.findByAcc(acc);
		while(acList.isPresent()) {
		acc=random.nextInt(1,5);
		acList = accountRepository.findByAcc(acc);
	}
	account.setAcc(acc);
		
		if(user!=null) {
			userRepository.save(user);
			accountRepository.save(account);
		}
		return account.getAcc();
	}
	public Optional<User> chekcingUser(User user){
		Optional<User> list=userRepository.findByEmailOrAadharOrPan(user.getEmail(),user.getAadhar(), user.getPan());
		return list;
	}
	
	public Long getId(String pan) {
		Optional<User> pandata= userRepository.findIdByPan(pan);
		User u = pandata.get();
		return u.getId();
	}
	
	public int addBalance(int acc,int balance,String pan) {
		Long uid=getId(pan);
		Optional<Account> accData=accountRepository.findBalanceById(uid);
		int curr_bal=accData.get().getBalance();
		int new_bal=balance+curr_bal;
//		Optional<Account> accdata1=accountRepository.findAccById(uid);
		accountRepository.updateBalanceByAcc(acc, new_bal);
		return new_bal;
	}
	
	public String withDraw(int acc,int amountToWithdraw,String pan) {
		Long uid=getId(pan);
		Optional<Account> accData=accountRepository.findBalanceById(uid);
		int curr_bal=accData.get().getBalance();
		if(amountToWithdraw>curr_bal) return "Insufficient balance";
		int new_bal = curr_bal-amountToWithdraw;
//		Optional<Account> accdata1=accountRepository.findAccById(uid);
		accountRepository.updateBalanceByAcc(acc, new_bal);
		return "Now current bal is "+new_bal;
	}
	
	public int checkBalance(int acc) {
		Optional<Account> acdataOptional = accountRepository.findBalanceByAcc(acc);
		return acdataOptional.get().getBalance();
	}
	
	public String transfer(int acc,int amount, int accB) {
		Optional<Account> accBal = accountRepository.findBalanceByAcc(acc);
		int curr_bal=accBal.get().getBalance();
		if(amount>curr_bal) return "Your account balance is insufficient";
		int new_balS=curr_bal-amount;
		accountRepository.updateBalanceByAcc(acc, new_balS);
		Optional<Account> accBalB=accountRepository.findBalanceByAcc(accB);
		int curr_balB=accBalB.get().getBalance();
		int new_balB=curr_balB+amount;
		accountRepository.updateBalanceByAcc(accB, new_balB);
		return amount+" is Succesfully transfered at account no. "+accB;
	}
	
//	public String deleteAcc(int acc) {
//		Optional<Account> U_id = accountRepository.findU_idBYAcc(acc);
//		User usr = U_id.get().getUserId();
//		StringBuilder sb = new StringBuilder();
//		sb.append(usr);
//		Long idLong = Long.parseLong(sb.toString());
//		accountRepository.deleteByAcc(acc);
//		userRepository.deleteById(idLong);
//		return "Your Account is succesfully deleted "+acc;
//	}
	
	
}
