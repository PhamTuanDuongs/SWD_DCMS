package dcms.be.swd.service;

import dcms.be.swd.dto.User.UpdateUserProfileRequest;
import dcms.be.swd.dto.User.UserProfileResponse;
import dcms.be.swd.entity.Account;
import dcms.be.swd.entity.MedStaffInfo;
import dcms.be.swd.entity.User;
import dcms.be.swd.repository.AccountRepository;
import dcms.be.swd.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    static final int PAGE_SIZE = 10;

    public ResponseEntity<MedStaffInfo> deleteEmployee(MedStaffInfo medstaff ) throws Exception {
        Account account = accountRepository
                .findAccountByUserId(medstaff.getUser().getId())
                .orElseThrow(() -> new Exception("Employee not found"));
        return new ResponseEntity<>(medstaff,HttpStatus.OK);
    }

    public ResponseEntity<UserProfileResponse> getUserProfile(int id) {
         var user = userRepository.getUserProfileById(id);
         return ResponseEntity.ok(user);
    }

    public ResponseEntity<Object> updateUserProfile(UpdateUserProfileRequest request) {
        User user = userRepository.getUserById(request.getId());
        if(user != null){
            BeanUtils.copyProperties(request, user);
            userRepository.save(user);
            return ResponseEntity.ok("update successfully");
        }
        return ResponseEntity.ok("update failed");
    }

    public Page<User> findAllPatients(
         int pageNo,
         String nationalId
    ) {
        var paging = PageRequest.of(pageNo, PAGE_SIZE);
        return userRepository.findAllPatients(nationalId, paging);
    }
}
