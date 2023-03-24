package it.academy.rest;

import it.academy.converter.AppUserToAppUserDTOConverter;
import it.academy.converter.NewAppUserToAppUserConverter;
import it.academy.repository.AppUserRepository;
import it.academy.repository.AppUserRoleRepository;
import it.academy.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ServiceConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void givenBeanWhenSearchingInAppContextThenFindIt() {
        assertNotNull(applicationContext.getBean(AppUserService.class));
    }

    @Test
    public void givenBeanDaoWhenSearchingInAppContextThenFindIt() {
        assertNotNull(applicationContext.getBean(AppUserRepository.class));
        assertNotNull(applicationContext.getBean(AppUserRoleRepository.class));
    }

    @Test
    public void givenScannedScopeComponent_whenSearchingInApplicationContext_thenFindIt() {
        assertNotNull(applicationContext.getBean(AppUserToAppUserDTOConverter.class));
        assertNotNull(applicationContext.getBean(NewAppUserToAppUserConverter.class));
    }
}
