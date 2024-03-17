package dcms.be.swd.repository;

import dcms.be.swd.dto.User.UserProfileResponse;
import dcms.be.swd.dto.medstaff.MedStaffResponse;
import dcms.be.swd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """
            select u.name as name, 
                   u.dob as dob, 
                   u.address as address, 
                   u.phoneNo as phoneNo, 
                   u.gender as gender, 
                   u.nationalId as nationalId, 
                   u.avatar as avatar,
                   a.email as email, 
                   a.createdAt as createdAt, 
                   r.roleName as roleName 
            from User u 
            join u.userAccounts a 
            join a.role r 
            where u.id = :id
                """)
    UserProfileResponse getUserProfileById(int id);

    @Query(value = """
            select u.name as name, 
                   u.dob as dob, 
                   u.address as address, 
                   u.phoneNo as phoneNo, 
                   u.gender as gender, 
                   u.nationalId as nationalId, 
                   u.avatar as avatar,
                   m.quanlification as quanlification,
                    m.experience as experience
            from User u 
            join u.userMedStaffInfoes m
            where u.id = :id
                """)
    MedStaffResponse getMedStaffById(int id);

    User getUserById(int id);
}
