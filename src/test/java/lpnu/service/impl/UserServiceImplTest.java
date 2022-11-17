package lpnu.service.impl;

import lpnu.Application;
import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.entity.enumeration.UserRole;
import lpnu.mapper.UserMapper;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc

public class UserServiceImplTest {
    @Test
    public void test_findUserById_userExist() throws Exception {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserMapper userMapper = Mockito.mock(UserMapper.class);

        final UserService userService = new UserServiceImpl(userRepository, userMapper);

        final String userName = "Tom";
        final String userSurname = "Cat";
        final String userEmail = "Tom@gmail.com";

        final User user = new User(1L, userName,
                userSurname, userEmail, UserRole.ADMIN);

        when(userRepository.findById(1L)).thenReturn(user);
        when(userMapper.toDTO(any())).thenCallRealMethod();

        final UserDTO userDTO = userService.findById(1L);

        assertNotNull(userDTO);

        assertEquals(1L, userDTO.getId().longValue());
        assertEquals(userName, userDTO.getName());
        assertEquals(userSurname, userDTO.getSurname());
        assertEquals(userEmail, userDTO.getEmail());
        assertEquals(user.getRole(), userDTO.getRole());

    }
}
