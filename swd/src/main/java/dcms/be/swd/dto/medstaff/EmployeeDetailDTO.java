package dcms.be.swd.dto.medstaff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {
    private String email;
    private String name;
    private LocalDate dob;

    private String address;

    private String phoneNo;

    private Boolean gender;

    private String nationalId;
    private String role;
    private String qualification;
    private String experience;



}

