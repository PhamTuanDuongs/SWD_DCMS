package dcms.be.swd.dto.User;

import java.time.LocalDate;

public interface UserProfileResponse {
    String getName();
    LocalDate getDob();
    String getAddress();
    String getPhoneNo();
    Boolean getGender();
    String getNationalId();
    String getAvatar();
    String getEmail();
    String getRoleName();
    LocalDate getCreatedAt();
}
