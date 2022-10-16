package com.example.md6be.controller;


import com.example.md6be.model.Comment;
import com.example.md6be.model.Customer;
import com.example.md6be.model.DTO.DTOComment;
import com.example.md6be.model.Product;
import com.example.md6be.service.impl.CommentService;
import com.example.md6be.service.impl.CustomerService;
import com.example.md6be.service.impl.ProductService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/commment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAllComment() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{idProduct}")
    private ResponseEntity<?> findAllByProductId(@PathVariable("idProduct") Long idProduct) {
        List<DTOComment> dtoComments = new ArrayList<DTOComment>();
        List<Comment> comments = commentService.findAllByProductId(idProduct);
        if (comments.size() != 0) {
            for (Comment comment : comments) {
                String review = comment.getReview();
                Optional<Customer> customer;
                try {
                    customer = customerService.findCustomerById(comment.getCustomer().getId());
                } catch (Exception e) {
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                }
                if (customer.isPresent()) {
                    String nameCustomer = customer.get().getName();
                    LocalDateTime myDate = comment.getTime();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                    String formattedDate = dateTimeFormatter.format(myDate);
                    dtoComments.add(new DTOComment(review, nameCustomer, formattedDate));
                }
            }
            Collections.reverse(dtoComments);
            return new ResponseEntity<>(dtoComments, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        Optional<Product> productOptional = productService.findById(comment.getProduct().getId());
        Optional<Customer> customerOptional = customerService.findCustomerById(comment.getCustomer().getId());
        LocalDateTime time = LocalDateTime.now();
        comment.setTime(time);
        if (productOptional.isPresent() && customerOptional.isPresent()) {
            return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
