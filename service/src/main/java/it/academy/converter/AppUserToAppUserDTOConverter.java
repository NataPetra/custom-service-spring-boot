package it.academy.converter;

import it.academy.dto.AppUserDTO;
import it.academy.model.AppUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppUserToAppUserDTOConverter implements Converter<AppUser, AppUserDTO> {

    @Override
    public AppUserDTO convert(AppUser source) {
        return new AppUserDTO(
                source.getFirstName() + " " + source.getLastName() + " " + source.getSurname(),
                source.getEmail(),
                source.getAppUserRole().getRoleName());
    }
}
