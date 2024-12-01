package org.openjfx.model.Details;
import jakarta.persistence.*;
import java.math.BigDecimal;
import org.openjfx.model.MenuItem;
import org.openjfx.model.Orders;

@Entity
@Table(name = "ordersDetailds")
public class OrdersDetailds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el pedido
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Orders orders;

    // Relación con el producto (MenuItem)
    @ManyToOne
    @JoinColumn(name = "menuItemId", nullable = false)
    private MenuItem menuItem;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    // Constructores
    public  void DetallePedido() {}

    public void DetallePedido(Orders orders, MenuItem menuItem, Integer cantidad, BigDecimal precio) {
        this.orders = orders;
        this.menuItem = menuItem;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Orders getPedido() {
        return orders;
    }

    public void setPedido(Orders orders) {
        this.orders = orders;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + id +
                ", menuItem=" + menuItem.getName() +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
