package com.paydaydemo.customers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.paydaydemo.customers.service.CustomerDto;
import com.paydaydemo.customers.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping
    public  ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
		 CustomerDto customerDto = customerService.createCustomer(customer);
		 return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<CustomerDto> getCustomerByUserId(@RequestParam Long userId) {
        Optional<CustomerDto> customer = customerService.getCustomerByUserId(userId);
        if (customer.isPresent()) {
        	return new ResponseEntity<CustomerDto>(customer.get(), HttpStatus.OK);
		}
        return new ResponseEntity<CustomerDto>(customer.get(), HttpStatus.NOT_FOUND);
    }
}
