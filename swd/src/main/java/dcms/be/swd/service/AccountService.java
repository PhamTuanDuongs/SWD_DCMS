package dcms.be.swd.service;

import dcms.be.swd.dto.account.AccountDto;
import dcms.be.swd.entity.Account;
import dcms.be.swd.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<Account> login(AccountDto account) throws Exception {
        ResponseEntity<Account> result;
        Account getAccount = accountRepository
                .findAccountByEmailAndPassword(account.getEmail())
                .orElseThrow(() -> new Exception("Employee is not found"));
        if (getAccount.getPassword().equals(account.getPassword())) {
            result = new ResponseEntity<>(getAccount, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
