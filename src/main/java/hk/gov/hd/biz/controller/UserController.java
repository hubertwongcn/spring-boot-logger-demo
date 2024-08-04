package hk.gov.hd.biz.controller;

import hk.gov.blt.core.logging.AppLogger;
import hk.gov.hd.biz.entity.User;
import hk.gov.hd.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hubertwong
 * @since 2024/8/4 12:23
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppLogger appLogger;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestHeader("App-Config-Name") String configName) {
        appLogger.info("Received request to create user: " + user + " with config: " + configName);
        User result = userService.createUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id, @RequestHeader("App-Config-Name") String configName) {
        appLogger.info("Received request to get user with id: "+ id + " with config: " + configName);
        User user = userService.getUserById(id);
        return ResponseEntity.ofNullable(user);
    }
}
