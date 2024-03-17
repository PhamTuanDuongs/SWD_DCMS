package dcms.be.swd.dto.medstaff;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageEmployeeResponseDTO {

    int totalPages;
    int currentPage;
    List<EmployeeDTO> employees;
}
