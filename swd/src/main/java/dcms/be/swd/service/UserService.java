package dcms.be.swd.service;

import dcms.be.swd.entity.Account;
import dcms.be.swd.entity.MedStaffInfo;
import dcms.be.swd.entity.User;
import dcms.be.swd.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private AccountRepository accountRepository;

    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<MedStaffInfo> deleteEmployee(MedStaffInfo medstaff ) throws Exception {
        Account account = accountRepository
                .findAccountByUserId(medstaff.getUser().getId())
                .orElseThrow(() -> new Exception("Employee not found"));
        return new ResponseEntity<>(medstaff,HttpStatus.OK);
    }
}
