package dcms.be.swd.dto.medstaff;

import com.fasterxml.jackson.annotation.JsonProperty;
import dcms.be.swd.dto.User.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
    private int id;

    private String name;

    private String role;

    private String email;

    private String phone;
}
