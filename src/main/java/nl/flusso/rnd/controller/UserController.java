package nl.flusso.rnd.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import nl.flusso.rnd.model.User;
import nl.flusso.rnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "",
            produces = { "application/json", "text/plain; charset=utf-8" },
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<java.util.List<User>> getUsers()  {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = Lists.newArrayList(usersIterable);
        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(value = "",
            produces = { "application/json", "text/plain; charset=utf-8" },
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<User> newUser(@ApiParam("User to add") @RequestBody User user) {
        String identifier = UUID.randomUUID().toString();
        User response = userRepository.save(user);
        return ResponseEntity.ok().body(response);
    }


    @RequestMapping(value = "/{id}",
            produces = { "application/json", "text/plain; charset=utf-8" },
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<User> getUser(@ApiParam(value = "User ID", required = true) @PathVariable("id") Long id) {
        User response = userRepository.findOne(id);
        return ResponseEntity.ok().body(response);
    }
}