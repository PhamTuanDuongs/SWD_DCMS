package dcms.be.swd.service;

import dcms.be.swd.dto.medstaff.MedStaffResponse;
import dcms.be.swd.dto.medstaff.EmployeeDTO;
import dcms.be.swd.dto.medstaff.EmployeeDetailDTO;
import dcms.be.swd.dto.medstaff.PageEmployeeResponseDTO;
import dcms.be.swd.entity.Account;
import dcms.be.swd.entity.MedStaffInfo;
import dcms.be.swd.entity.Role;
import dcms.be.swd.entity.User;
import dcms.be.swd.repository.AccountRepository;
import dcms.be.swd.repository.MedStaffInfoRepository;
import dcms.be.swd.repository.UserRepository;
import dcms.be.swd.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedstaffService {

    private MedStaffInfoRepository medStaffInfoRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public MedstaffService(MedStaffInfoRepository medStaffInfoRepository, AccountRepository accountRepository,
            UserRepository userRepository) {
        this.medStaffInfoRepository = medStaffInfoRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    static final int PAGE_SIZE = 10;

    public MedstaffService(MedStaffInfoRepository medStaffInfoRepository, AccountRepository accountRepository,
            UserRepository userRepository, RoleRepository roleRepository) {
        this.medStaffInfoRepository = medStaffInfoRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<MedStaffInfo> deleteEmployee(MedStaffInfo medstaff) throws Exception {
        MedStaffInfo medStaffInfo = medStaffInfoRepository
                .findById(medstaff.getId())
                .orElseThrow(() -> new Exception("Employee is not found"));
        Account a = accountRepository
                .findAccountByUserId(medStaffInfo.getUser().getId())
                .orElseThrow(() -> new Exception("Account is not found"));
        a.setEmail(null);
        accountRepository.save(a);
        return new ResponseEntity<>(medstaff, HttpStatus.OK);
    }

    public ResponseEntity<MedStaffResponse> findEmployeeDetail(int id) throws Exception {
        MedStaffResponse medStaffInfo = userRepository.getMedStaffById(id);
        return new ResponseEntity<>(medStaffInfo, HttpStatus.OK);

    }

    public ResponseEntity<MedStaffInfo> updateEmployee(MedStaffInfo medstaff) throws Exception {
        MedStaffInfo medStaffInfo = medStaffInfoRepository
                .findById(medstaff.getId())
                .orElseThrow(() -> new Exception("Employee is not found"));

        if (medstaff.getExperience() != null)
            medStaffInfo.setExperience(medstaff.getExperience());

        if (medstaff.getQuanlification() != null)
            medStaffInfo.setQuanlification(medstaff.getQuanlification());

        if (medstaff.getUser() != null) {
            medstaff.getUser().setId(medStaffInfo.getUser().getId());
            medStaffInfo.setUser(medstaff.getUser());
        }

        MedStaffInfo medStaffInfoUpdate = medStaffInfoRepository.save(medStaffInfo);
        return new ResponseEntity<>(medStaffInfoUpdate, HttpStatus.OK);
    }

    public PageEmployeeResponseDTO findAllEmployeePaginated(int pageNo) {
        var paging = PageRequest.of(pageNo - 1, PAGE_SIZE);
        Page<User> pageEmployees = userRepository.findAllEmployeePaginate(paging);
        List<User> employees = pageEmployees.getContent();
        List<EmployeeDTO> listEmployee = new ArrayList<>();
        for (User u : pageEmployees) {
            EmployeeDTO e = new EmployeeDTO();
            e.setId(u.getId());
            e.setEmail(u.getUserAccounts().getEmail());
            e.setName(u.getName());
            e.setRole(u.getUserAccounts().getRole().getRoleName());
            e.setPhone(u.getPhoneNo());
            listEmployee.add(e);
        }
        return PageEmployeeResponseDTO.builder().totalPages(pageEmployees.getTotalPages())
                .currentPage(pageEmployees.getNumber())
                .employees(listEmployee).build();

    }

    public MedStaffInfo createNewEmployee(EmployeeDetailDTO employeeDetail) {

        User u = new User();
        u.setName(employeeDetail.getName());
        u.setDob(employeeDetail.getDob());
        u.setAddress(employeeDetail.getAddress());
        u.setNationalId(employeeDetail.getNationalId());
        u.setPhoneNo(employeeDetail.getPhoneNo());
        u.setGender(employeeDetail.getGender());
        userRepository.save(u);
        Account acc = new Account();
        acc.setEmail(employeeDetail.getEmail());
        acc.setPassword("123456");
        acc.setUser(u);
        Role r = roleRepository.findRoleByRoleName(employeeDetail.getRole());
        acc.setRole(r);
        accountRepository.save(acc);
        MedStaffInfo medStaffInfo = new MedStaffInfo();
        medStaffInfo.setExperience(employeeDetail.getExperience());
        medStaffInfo.setQuanlification(employeeDetail.getQualification());
        medStaffInfo.setUser(u);
        medStaffInfoRepository.save(medStaffInfo);
        return medStaffInfo;

    }

}
