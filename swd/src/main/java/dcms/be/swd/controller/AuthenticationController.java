package dcms.be.swd.controller;

import dcms.be.swd.dto.account.AccountDto;
import dcms.be.swd.entity.Account;
import dcms.be.swd.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AccountService accountService;

    public AuthenticationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody AccountDto account) throws Exception {
        return accountService.login(account);
    }
}
