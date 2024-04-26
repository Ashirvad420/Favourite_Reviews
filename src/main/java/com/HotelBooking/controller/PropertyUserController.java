package com.HotelBooking.controller;


import com.HotelBooking.dto.JWTResponse;
import com.HotelBooking.dto.LoginDto;
import com.HotelBooking.dto.PropertyUserDto;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.service.PropertyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class PropertyUserController {


    private PropertyUserService userService;

    public PropertyUserController(PropertyUserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/api/v1/users/addUser
    @PostMapping("/addUser")  // this is for SignUp
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto dto)
    {
       PropertyUser user = userService.addUser(dto);
       if (user != null)
       {
           return new ResponseEntity<>("sign up successfully", HttpStatus.CREATED);
       }
       return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @PostMapping("/login")  // this is for SignIn
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto)
    {

        // JWT Token
        String jwtToken = userService.verifyLogin(loginDto);

        if (jwtToken!=null)
        {
            JWTResponse jwtResponse = new JWTResponse();
            jwtResponse.setToken(jwtToken);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }



    // how to get users details from jwt token
    @GetMapping("/profile")
    public PropertyUser getCurrentProfile(@AuthenticationPrincipal PropertyUser propertyUser)
    {
        return propertyUser;
    }

    // Get User Information By Id
    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id)
    {
        PropertyUser propertyUser = userService.geUser(id);
        if (propertyUser!=null)
        {
            return new ResponseEntity<>(propertyUser,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Find Id  of Property User",HttpStatus.NOT_FOUND);
    }

    // Get All User information
    @GetMapping("/all")
    public ResponseEntity<?> getAll()
    {
        List<PropertyUser> propertyUsers = userService.getAll();
        return new ResponseEntity<>(propertyUsers,HttpStatus.OK);
    }

    // Delete By id
    @DeleteMapping("/ById/{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id)
    {
        String str = userService.DeleteById(id);
        if(str!= null)
        {
            return new ResponseEntity<>("Data is deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("id is not found of User",HttpStatus.NOT_FOUND);
    }

    // Delete All
    @DeleteMapping("/DeleteAlls")
    public ResponseEntity<?> DeleteAll()
    {
       String str = userService.DeleteAll();
       return new ResponseEntity<>(str,HttpStatus.OK);
    }

    // update By id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable Long id, @RequestBody PropertyUser propertyUser)
    {
       PropertyUser propertyUsers = userService.UpdateUser(id,propertyUser);
       if (propertyUsers!=null)
       {
           return new ResponseEntity<>(propertyUsers,HttpStatus.OK);
       }
        return new ResponseEntity<>("Property is not present for this id",HttpStatus.NOT_FOUND);
    }
}


// hum postmaping ko replace kr ke " @RequestMapping(name = "/addUser", method = RequestMethod.POST) " de skte hai