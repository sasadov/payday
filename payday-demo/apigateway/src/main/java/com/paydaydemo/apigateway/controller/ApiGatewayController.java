package com.paydaydemo.apigateway.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paydaydemo.apigateway.exception.SignUpFailedException;
import com.paydaydemo.apigateway.model.User;
import com.paydaydemo.apigateway.security.JwtAuthenticationProvider;
import com.paydaydemo.apigateway.security.JwtUtility;
import com.paydaydemo.apigateway.service.AccountDto;
import com.paydaydemo.apigateway.service.AccountService;
import com.paydaydemo.apigateway.service.AccountSummaryDto;
import com.paydaydemo.apigateway.service.CustomerDto;
import com.paydaydemo.apigateway.service.CustomerService;
import com.paydaydemo.apigateway.service.TransactionDto;
import com.paydaydemo.apigateway.service.TransactionService;
import com.paydaydemo.apigateway.service.UserService;

@RestController
@RequestMapping("/apigateway")
public class ApiGatewayController {
	
    private UserService userService;
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    TransactionService transactionService;

    @Autowired
    JwtUtility jwtUtility;

	@Autowired
	public ApiGatewayController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody SignUpRequest request) throws SignUpFailedException {

        try {
        	PasswordEncoder encoder =
        		     PasswordEncoderFactories.createDelegatingPasswordEncoder();           
         	String encoded = encoder.encode(request.user.password);         
         	User newUser = new User();
         	newUser.setFirstName(request.user.firstName);
         	newUser.setLastName(request.user.lastName);
         	newUser.setEmail(request.user.email);
         	newUser.setDate(new Date());
         	newUser.setRoles("ROLE_USER");
         	newUser.setPassword(encoded);
        	User user = userService.createUser(newUser);       
        	CustomerDto customerDto = new CustomerDto();
        	customerDto.userId = user.getId();
        	customerDto.firstName = request.user.firstName;
        	customerDto.lastName = request.user.lastName;
        	customerDto.gender = request.user.gender;
        	customerDto.phoneNumber = request.user.phoneNumber;
        	customerDto.email = request.user.phoneNumber;
        	customerDto.birthDate = request.user.birthDate;
        	Optional<CustomerDto> createdCustomer = customerService.createCustomer(customerDto);        	        	
            if (createdCustomer.isPresent()) {
            	user.setUserName(createdCustomer.get().Id.toString());
            	userService.updateUser(newUser); 
        	    Map<String, String> model = new HashMap<>();
                model.put("CustomerId", createdCustomer.get().Id.toString());
            	return new ResponseEntity<Map<String, String>>(model, HttpStatus.CREATED);
			}
            Map<String, String> model = new HashMap<>();
            model.put("CustomerId", "");
            return new ResponseEntity<Map<String, String>>(model, HttpStatus.CONFLICT);
        } catch (Exception e) {
            throw new SignUpFailedException();
        }
    }
	
	@PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin() {

        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	String token = jwtUtility.createToken(authentication.getName(), userService.findUserByUserName(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("customerId " + authentication.getName() + "not found")).getRoles());
            Map<String, String> model = new HashMap<>();
            model.put("customerId", authentication.getName());
            model.put("token", token);
            return new ResponseEntity<Map<String, String>>(model, HttpStatus.ACCEPTED);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid customerId/password supplied");
        }
    }
	
	@PostMapping("/accounts")
    public ResponseEntity<AccountSummaryDto> openAccount(@RequestBody AccountDto account) {

        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	account.customerId = Long.parseLong(authentication.getName());
        	Optional<AccountSummaryDto> accountSummaryOptional = accountService.openAccount(account);
        	if (accountSummaryOptional.isPresent()) {
				return new ResponseEntity<AccountSummaryDto>(accountSummaryOptional.get(), HttpStatus.CREATED);
			}
            return new ResponseEntity<AccountSummaryDto>(HttpStatus.CONFLICT);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid customerId/password supplied");
        }
    }
	
	@GetMapping("/accounts")
    public ResponseEntity<List<AccountSummaryDto>> getAccountSummary() {

        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	List<AccountSummaryDto> summary =  accountService.getAccountSummary(Long.parseLong(authentication.getName()));
        	if (summary!=null&&!summary.isEmpty()) {
            	return new ResponseEntity<List<AccountSummaryDto>>(summary, HttpStatus.OK);
			}
        	return new ResponseEntity<List<AccountSummaryDto>>(HttpStatus.NO_CONTENT);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid customerId/password supplied");
        }
    }
	
	@GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions() {

        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	List<TransactionDto> summary =  transactionService.getTransactions(Long.parseLong(authentication.getName()));
        	if (summary!=null&&!summary.isEmpty()) {
            	return new ResponseEntity<List<TransactionDto>>(summary, HttpStatus.OK);
			}
        	return new ResponseEntity<List<TransactionDto>>(HttpStatus.NO_CONTENT);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid customerId/password supplied");
        }
    }
	
	@GetMapping("/test")
    public String test() {

       return "test success";
    }
}
