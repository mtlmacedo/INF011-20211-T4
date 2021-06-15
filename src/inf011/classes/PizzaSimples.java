package inf011.classes;

import interfaces.PizzaComponent;

public class PizzaSimples implements PizzaComponent {
    public String preparar() {
        return "Preparando massa + molho + queijo";
    }
}
