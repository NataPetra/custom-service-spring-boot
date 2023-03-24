package it.academy.rest;

import it.academy.configuration.RootConfig;
import it.academy.dto.AppUserDTO;
import it.academy.service.AppUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = RootConfig.class)
public class AppUserServiceTest {

    @Autowired
    private AppUserService appUserService;

    @Test
    public void userSearchWithValidId() {
        Long id = 1L;
        AppUserDTO user = appUserService.getUserByID(id);
        assertNotNull(user);
    }




}
