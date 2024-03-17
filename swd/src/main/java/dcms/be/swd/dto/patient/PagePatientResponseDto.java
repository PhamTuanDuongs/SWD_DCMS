package dcms.be.swd.dto.patient;

import dcms.be.swd.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagePatientResponseDto {
    int totalPages;
    int currentPage;
    List<User> data;
}
