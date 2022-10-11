package com.example.md6be.controller;


import com.example.md6be.jwt.JwtResponse;
import com.example.md6be.jwt.JwtService;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Role;
import com.example.md6be.service.ICustomerService;
import com.example.md6be.service.IRoleService;
import com.example.md6be.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(iCustomerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/id-new-customer")
    private ResponseEntity<Long> findNewOrderId() {
        return new ResponseEntity<>(iCustomerService.findNewCustomerId(), HttpStatus.OK);
    }


    @GetMapping("/find/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(iCustomerService.findByEmailAddress(email), HttpStatus.OK);
    }

    //UPdate Img
    @PutMapping("/update-customer/{id}")
    private ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = iCustomerService.findById(id);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-customer-by-id/{idCustomer}")
    private ResponseEntity<Customer> findCustomerById(@PathVariable("idCustomer") Long idCustomer) {
        Optional<Customer> customerOptional = iCustomerService.findById(idCustomer);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/login/{email}-{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer currentUser = iCustomerService.findByEmailAddress(email).get();
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> responseEntity(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try {
            iCustomerService.save(customer);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        emailService.sendEmail(customer);
        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRole() {
        List<Role> roles = iRoleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customerOptional = iCustomerService.findById(id);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>("The customer has been deleted ", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
