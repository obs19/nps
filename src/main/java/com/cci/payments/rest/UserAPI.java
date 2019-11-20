package com.cci.payments.rest;

import com.cci.payments.dto.EditResponse;
import com.cci.payments.dto.PageDTOGeneric;
import com.cci.payments.dto.UserDTO;
import com.cci.payments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/rest/user")
public class UserAPI {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/list")
    public ResponseEntity<PageDTOGeneric> getPaymentHistory(@RequestParam(name = "page") int page,
                                                            @RequestParam(name = "start") int start,
                                                            @RequestParam(name = "limit") int limit){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUsers(page, start, limit));
    }


    @GetMapping
    public ResponseEntity<UserDTO> getById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getById(id));
    }


    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Long> deleteUser(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.deleteById(id));
    }


    @PostMapping(value = "/update")
    @Transactional
    public ResponseEntity<?> updateUser(UserDTO dto) {
        if (dto.getId() == null){
            userService.create(dto);
        } else{
            userService.update(dto);
        }
        String msg = "User updated successfully!";
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EditResponse(true, msg));
    }


    @PostMapping(value = "/changeStatus")
    @Transactional
    public ResponseEntity<?> changeUserStatus(@RequestParam(name = "id") Long id) {
        userService.changeUserStatus(id);
        String msg = "Status changed successfully!";
        return ResponseEntity.status(HttpStatus.OK)
                .body(new EditResponse(true, msg));
    }


}
