package com.example.simplecrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    ResponseEntity<Iterable<Customer>> getAll(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @PostMapping("/")
    ResponseEntity<Customer> addOne(@RequestBody Customer customer){
        return ResponseEntity.ok(customerRepository.save(customer));
    }

}
