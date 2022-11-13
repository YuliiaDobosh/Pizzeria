package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Order;
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
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    private Long id = 0L;

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order save(final Order order) {
        ++id;
        order.setId(id);

        orders.add(order);

        return order;
    }

    public Order findById(final Long id) {
        return orders.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "Order can not be found by id: " + id));
    }

    public void delete(final Long id) {
        orders = orders.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Order update(final Order order) {
        final Order saved = findById(order.getId());

        saved.setUser(order.getUser());
        saved.setOrders(order.getOrders());
        saved.setOrderDateTime(order.getOrderDateTime());

        return saved;
    }

    @PostConstruct
    public void init() {

        final Path file = Paths.get("order.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            orders = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<>() {
            });

            if (orders == null) {
                orders = new ArrayList<>();
                return;
            }

            final long maxId = orders.stream().mapToLong(Order::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue in order");
            orders = new ArrayList<>();
        }
    }


    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("order.txt");

        try {
            Files.writeString(file, JacksonUtil.serialize(orders), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue in order");
        }

    }

}
