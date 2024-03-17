package dcms.be.swd.repository;


import dcms.be.swd.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.roleName = ?1")
    Role findRoleByRoleName(String name);
}
