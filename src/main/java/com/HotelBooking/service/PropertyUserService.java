package com.HotelBooking.service;

import com.HotelBooking.dto.LoginDto;
import com.HotelBooking.dto.PropertyUserDto;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.repository.PropertyUserRepository;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyUserService {

    private PropertyUserRepository propertyUserRepository;
    private JWTService jwtService;
    public PropertyUserService(PropertyUserRepository propertyUserRepository, JWTService jwtService) {
        this.propertyUserRepository = propertyUserRepository;
        this.jwtService=jwtService;
    }




    // this is for SignUp
    public PropertyUser addUser(PropertyUserDto dto) {
        PropertyUser user = new PropertyUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setPassword(new BCrypt().hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        propertyUserRepository.save(user);
        return user;

    }




    // this is for SignIn
    public String verifyLogin(LoginDto loginDto) {

        Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent())
        {
            PropertyUser user = opUser.get();
            // wrapping this code for jwt token
           if( BCrypt.checkpw(loginDto.getPassword(), user.getPassword()))
           {
              return jwtService.generateToken(user);
           }
        }
        return null;
    }

    // Get User Information By Id
    public PropertyUser geUser(Long id) {
       Optional<PropertyUser> opUser = propertyUserRepository.findById(id);
       if (opUser.isPresent())
       {
           PropertyUser propertyUser = opUser.get();
           return propertyUser;
       }
       return null;
    }

    // Get All User information
    public List<PropertyUser> getAll() {
       List<PropertyUser> propertyUsers = propertyUserRepository.findAll();
       return propertyUsers;
    }

    // Delete By id
    public String DeleteById(Long id) {
       Optional<PropertyUser> Opuser = propertyUserRepository.findById(id);
       if (Opuser.isPresent())
       {
           propertyUserRepository.deleteById(id);
           return "Delete";
       }
       return null;
    }

    // Delete All
    public String DeleteAll() {
        propertyUserRepository.deleteAll();
        return "deletet";
    }

    // Update User by id
    public PropertyUser UpdateUser(Long id, PropertyUser propertyUser) {
        Optional<PropertyUser> OpUsers = propertyUserRepository.findById(id);
        if (OpUsers.isPresent())
        {
            PropertyUser propertyUsers = OpUsers.get();
            propertyUsers.setFirstName(propertyUsers.getFirstName());
           PropertyUser saved = propertyUserRepository.save(propertyUsers);
           return saved;
        }
        return null;
    }
}






//Option user (opUser) :-If we are now returning nothing at least it will not give any null pointer exception
// opUser:- password is encrypted there
// checkpw (check password)- return back boolean, it means return true or false

// generateToken ;- yahi sirf call krega ki username and password valid hai ya nhi

// String can not return false either can be return null and default val