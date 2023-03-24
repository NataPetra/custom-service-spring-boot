package it.academy.rest.controller;

import it.academy.dto.AppUserDTO;
import it.academy.dto.NewAppUserDTO;
import it.academy.rest.except.ExceptionGlobal;
import it.academy.service.AppUserService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app_users")
public class AppUserController {

    private static final Logger logger = LogManager.getLogger();

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("")
    public List<AppUserDTO> all(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return appUserService.allUsersPagination(page, size);
    }

    @SneakyThrows
    @PostMapping("")
    public ResponseEntity<NewAppUserDTO> newUser(@Valid @RequestBody NewAppUserDTO newUser, Errors errors) {
        logger.info("Validation Errors: " + errors.hasErrors());
        logger.info("Errors count: " + errors.getErrorCount());
        if (errors.hasErrors()){
            throw new ExceptionGlobal();
        }
        return new ResponseEntity<>(appUserService.addNewUser(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AppUserDTO getOne(@PathVariable Long id) {
        return appUserService.getUserByID(id);
    }
}
