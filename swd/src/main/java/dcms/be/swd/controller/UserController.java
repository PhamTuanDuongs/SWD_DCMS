package dcms.be.swd.controller;

import dcms.be.swd.dto.User.UpdateUserProfileRequest;
import dcms.be.swd.dto.User.UserProfileResponse;
import dcms.be.swd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Integer id) {
        return userService.getUserProfile(id);
    }
    @PostMapping ("/update")
    public ResponseEntity<Object> getUserProfile(@RequestBody UpdateUserProfileRequest request) {
        return userService.updateUserProfile(request);
    }
}
