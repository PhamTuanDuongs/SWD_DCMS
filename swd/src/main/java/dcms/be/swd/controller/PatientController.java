package dcms.be.swd.controller;

import dcms.be.swd.dto.User.UpdateUserProfileRequest;
import dcms.be.swd.dto.User.UserProfileResponse;
import dcms.be.swd.dto.patient.PagePatientResponseDto;
import dcms.be.swd.dto.service.PageServiceResponseDTO;
import dcms.be.swd.entity.User;
import dcms.be.swd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static dcms.be.swd.controller.ServiceController.SERVICE_NOT_FOUND_MESSAGE;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
public class PatientController {
    private final UserService userService;
    static final String PATIENT_NOT_FOUND_MESSAGE = "Patient not found";
    @GetMapping("")
    public ResponseEntity<PagePatientResponseDto> findAllPatient(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "") String nationalId
    ) {
        var page = userService.findAllPatients(pageNo - 1, nationalId);

        if (!page.hasContent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PATIENT_NOT_FOUND_MESSAGE
            );
        } else {
            return new ResponseEntity(
                PagePatientResponseDto
                    .builder()
                    .totalPages(page.getTotalPages())
                    .currentPage(page.getNumber() + 1)
                    .data(page.getContent())
                    .build(),
                HttpStatus.OK
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> findPatientById(@PathVariable Integer id) {
        return userService.getUserProfile(id);
    }
    @PostMapping ("/update")
    public ResponseEntity<Object> getUserProfile(@RequestBody UpdateUserProfileRequest request) {
        return userService.updateUserProfile(request);
    }
}
