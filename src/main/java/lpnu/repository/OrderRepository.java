package lpnu.repository;

import lpnu.entity.Order;
import lpnu.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

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
}
