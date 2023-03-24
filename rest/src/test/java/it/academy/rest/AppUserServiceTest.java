package it.academy.rest;

import it.academy.configuration.RootConfig;
import it.academy.dto.AppUserDTO;
import it.academy.exception.AppUserNotFoundException;
import it.academy.repository.AppUserRepository;
import it.academy.service.AppUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = RootConfig.class)
public class AppUserServiceTest {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void userSearchWithValidId() {
        Long id = 1L;
        AppUserDTO user = appUserService.getUserByID(id);
        assertNotNull(user);
    }

    @Test
    public void userSearchWithInValidId() {
        Long wrongId = 9999999L;
        Throwable exception = assertThrows(AppUserNotFoundException.class,
                () -> appUserService.getUserByID(wrongId));
        assertEquals("Could not find user " + wrongId, exception.getMessage());
    }

    @Test
    public void allUsersSearch() {
        long count = appUserRepository.count();
        long size = appUserService.allUsers().size();
        assertEquals(count, size);
    }
}
