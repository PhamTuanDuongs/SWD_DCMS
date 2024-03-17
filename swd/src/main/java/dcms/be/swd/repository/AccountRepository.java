package dcms.be.swd.repository;

import dcms.be.swd.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.id = ?1")
    Optional<Account> findAccountByUserId(long id);

    @Query("select a from Account a where a.email = ?1 ")
    Optional<Account> findAccountByEmailAndPassword(String email);
}
