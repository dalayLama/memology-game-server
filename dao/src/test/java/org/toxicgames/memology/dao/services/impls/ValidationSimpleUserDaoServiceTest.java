package org.toxicgames.memology.dao.services.impls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.toxicgames.memology.common.exceptions.ValidationException;
import org.toxicgames.memology.common.objects.SimpleUserRegistrationData;
import org.toxicgames.memology.dao.repositories.UserRepository;
import org.toxicgames.memology.dao.services.UserDaoService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration(classes = PostProcessorConfiguration.class)
class ValidationSimpleUserDaoServiceTest {

    @MockBean
    private UserRepository repository;

    @MockBean
    private MessageSource messageSource;

    @Autowired
    private UserDaoService daoService;

    @Test
    @DisplayName("should throw exception when a validation check does not pass")
    void shouldThrowExceptionWhenAValidationCheckDoesNotPass() {
        ValidationException validationException = assertThrows(ValidationException.class, () -> daoService.create(new SimpleUserRegistrationData(null)));
        assertThat(validationException).isNotNull();
    }

}