package dcms.be.swd.dto.medstaff;

import java.time.LocalDate;

public interface MedStaffResponse {
    String getName();
    LocalDate getDob();
    String getAddress();
    String getPhoneNo();
    Boolean getGender();
    String getNationalId();
    String getAvatar();
    String getEmail();
    String getQuanlification();
    String getExperience();
}
