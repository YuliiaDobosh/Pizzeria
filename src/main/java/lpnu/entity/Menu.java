package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Long id;
    private List<Long> allPizzas = new ArrayList<>();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return Objects.equals(getId(), menu.getId()) && Objects.equals(getAllPizzas(), menu.getAllPizzas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAllPizzas());
    }
}
