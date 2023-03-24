package it.academy.service;

import it.academy.dto.AppUserDTO;
import it.academy.dto.NewAppUserDTO;
import it.academy.exception.AppUserNotFoundException;
import it.academy.model.AppUser;
import it.academy.model.AppUserRole;
import it.academy.repository.AppUserRepository;
import it.academy.repository.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserService{

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private AppUserRoleRepository appUserRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUserDTO> allUsers(){
        List<AppUserDTO> appUsers = new ArrayList<>();
        List<AppUser> users = appUserRepository.allUsersOrderByEmail();
        for (AppUser user: users) {
            if (!conversionService.canConvert(AppUser.class, AppUserDTO.class)) {
                throw new RuntimeException("Can not convert AppUser to AppUserDTO");
            }
            appUsers.add(conversionService.convert(user, AppUserDTO.class));
        }
        return appUsers;
    }

    public List<AppUserDTO> allUsersPagination(Integer pageNumber, Integer pageSize){
        List<AppUserDTO> appUsers = new ArrayList<>();
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<AppUser> users = appUserRepository.findAll(paging);
        for (AppUser user: users) {
            if (!conversionService.canConvert(AppUser.class, AppUserDTO.class)) {
                throw new RuntimeException("Can not convert AppUser to AppUserDTO");
            }
            appUsers.add(conversionService.convert(user, AppUserDTO.class));
        }
        return appUsers.stream()
                .sorted(new AppUserDTOComparator())
                .collect(Collectors.toList());
    }

    public NewAppUserDTO addNewUser(NewAppUserDTO newAppUserDTO){
        if (!conversionService.canConvert(NewAppUserDTO.class, AppUser.class)) {
            throw new RuntimeException("Can not convert NewAppUserDTO to AppUser");
        }
        AppUser appUser = conversionService.convert(newAppUserDTO, AppUser.class);
        AppUserRole userRole = appUserRoleRepository.findAppUserRoleByRoleName(newAppUserDTO.getRoleName());
        if(userRole==null){
            userRole = appUserRoleRepository.save(new AppUserRole(null, newAppUserDTO.getRoleName()));
        }
        Objects.requireNonNull(appUser).setAppUserRole(userRole);
        appUserRepository.save(appUser);
        return newAppUserDTO;
    }

    public AppUserDTO getUserByID(Long id){
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundException(id));
        if (!conversionService.canConvert(AppUser.class, AppUserDTO.class)) {
            throw new RuntimeException("Can not convert AppUser to AppUserDTO");
        }
        return conversionService.convert(user, AppUserDTO.class);
    }

}
