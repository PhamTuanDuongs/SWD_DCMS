package dcms.be.swd.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {
    private int id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phoneNo;
    private Boolean gender;
    private String nationalId;
}
