package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private Long id = 0L;

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User save(final User user) {
        ++id;
        user.setId(id);

        users.add(user);

        return user;
    }

    public User findById(final Long id) {
        return users.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "User can not be found by id: " + id));
    }

    public void delete(final Long id) {
        users = users.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public User update(final User user) {
        final User saved = findById(user.getId());

        saved.setName(user.getName());
        saved.setSurname(user.getSurname());
        saved.setEmail(user.getEmail());

        return saved;
    }

    @PostConstruct
    public void init() {

        final Path file = Paths.get("user.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            users = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<>() {
            });

            if (users == null) {
                users = new ArrayList<>();
                return;
            }

            final long maxId = users.stream().mapToLong(User::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue in user");
            users = new ArrayList<>();
        }
    }


    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("user.txt");

        try {
            Files.writeString(file, JacksonUtil.serialize(users), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue in user");
        }

    }
}