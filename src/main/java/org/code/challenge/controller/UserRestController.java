package org.code.challenge.controller;

import org.code.challenge.service.IUserService;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/follow/{creatorName}")
    @ResponseBody
    public ResponseEntity<String> followNewUser( @RequestParam String user ,@PathVariable String creatorName) throws DataAdditionException, DataExtractionException {
        userService.followCreator(user,creatorName);
        return ResponseEntity.ok("You followed creator: " + creatorName);
    }
}
