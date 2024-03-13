package dcms.be.swd.dto.service;

import dcms.be.swd.entity.Service;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageServiceResponseDTO {
    int totalPages;
    int currentPage;
    List<Service> services;
}