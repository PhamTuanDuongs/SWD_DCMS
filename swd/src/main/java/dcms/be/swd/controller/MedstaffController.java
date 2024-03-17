package dcms.be.swd.controller;

<<<<<<< Updated upstream
import dcms.be.swd.dto.medstaff.MedStaffResponse;
=======
import dcms.be.swd.dto.medstaff.EmployeeDetailDTO;
>>>>>>> Stashed changes
import dcms.be.swd.dto.medstaff.MedstaffDto;
import dcms.be.swd.dto.medstaff.PageEmployeeResponseDTO;
import dcms.be.swd.dto.service.PageServiceResponseDTO;
import dcms.be.swd.entity.MedStaffInfo;
import dcms.be.swd.entity.User;
import dcms.be.swd.repository.UserRepository;
import dcms.be.swd.service.MedstaffService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/admin/employee")
public class MedstaffController {

    static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found";
    private MedstaffService medstaffService;
    private UserRepository userRepository;

    public MedstaffController(MedstaffService medstaffService, UserRepository userRepository) {
        this.medstaffService = medstaffService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedStaffResponse> findEmployeeDetail(@PathVariable int id) throws Exception {
        return medstaffService.findEmployeeDetail(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MedStaffInfo> deleteEmployee(@PathVariable long id) throws Exception {
        MedStaffInfo info = new MedStaffInfo();
        info.setId(id);
        return medstaffService.deleteEmployee(info);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedStaffInfo> updateEmployee(@RequestBody MedstaffDto info) throws Exception {
        MedStaffInfo medStaffInfo = new MedStaffInfo();
        User user = new User();
        if (info.getUser() != null) {
            user.setName(info.getUser().getName());
            user.setGender(info.getUser().getGender());
            user.setAddress(info.getUser().getAddress());
            user.setNationalId(info.getUser().getNationalId());
            user.setDob(info.getUser().getDob());
            user.setPhoneNo(info.getUser().getPhoneNo());
        }
        medStaffInfo.setId(info.getId());
        medStaffInfo.setQuanlification(info.getQuanlification());
        medStaffInfo.setExperience(info.getExperience());
        medStaffInfo.setUser(user);
        return medstaffService.updateEmployee(medStaffInfo);
    }

    @GetMapping
    @RequestMapping("/all")
    public PageEmployeeResponseDTO findAllEmployeeByRoleAndName(
            @RequestParam(defaultValue = "1") int pageNo
    ) {
        var page = medstaffService.findAllEmployeePaginated(pageNo);

        if (page.getEmployees()==null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND_MESSAGE
            );
        } else {
            return page;
        }
    }
    @PostMapping("/add")
    public MedStaffInfo createNewEmployee(@RequestBody EmployeeDetailDTO employeeDetail){
        System.out.println(employeeDetail.getEmail());
        MedStaffInfo medStaff= medstaffService.createNewEmployee(employeeDetail);
        return medStaff;
    }

}
