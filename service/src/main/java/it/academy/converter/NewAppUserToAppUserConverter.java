package it.academy.converter;

import it.academy.dto.NewAppUserDTO;
import it.academy.model.AppUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewAppUserToAppUserConverter implements Converter<NewAppUserDTO, AppUser> {

    @Override
    public AppUser convert(NewAppUserDTO source) {
        return new AppUser(
                source.getLastName(),
                source.getFirstName(),
                source.getSurname(),
                source.getEmail());
    }
}
