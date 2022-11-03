package lpnu.repository;

import lpnu.entity.Basket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class BasketRepository {
    private List<Basket> baskets = new ArrayList<>();
    private Long id = 0L;

    public List<Basket> getAllBaskets() {
        return new ArrayList<>(baskets);
    }

    public Basket save(final Basket basket) {
        ++id;
        basket.setId(id);

        baskets.add(basket);

        return basket;
    }

    public Basket findById(final Long id) {
        return baskets.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found by id: " + id));
    }

    public void delete(final Long id) {
        baskets = baskets.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Basket update(final Basket basket) {
        final Basket saved = findById(basket.getId());

        saved.setUser(basket.getUser());
        saved.setOrders(basket.getOrders());
        saved.setOrderDateTime(basket.getOrderDateTime());

        return saved;
    }
}
