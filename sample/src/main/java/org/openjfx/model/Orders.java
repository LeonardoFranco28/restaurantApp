package org.openjfx.model;
import org.openjfx.model.Details.OrdersDetailds;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 'order' es una palabra reservada en SQL
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el usuario que realizó el pedido
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "fechaPedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private StatusOrders estado;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    // Relación con los detalles del pedido
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersDetailds> detalles = new ArrayList<>();

    // Constructores
   public Orders() {}
    public  Orders(User user, LocalDateTime fechaPedido, StatusOrders estado, BigDecimal total) {
        this.user = user;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuaro(User user) {
        this.user = user;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public StatusOrders getEstado() {
        return estado;
    }

    public void setEstado(StatusOrders estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrdersDetailds> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdersDetailds> detalles) {
        this.detalles = detalles;
    }

    // Métodos de utilidad
    public void addDetalle(OrdersDetailds detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
    }

    public void removeDetalle(OrdersDetailds detalle) {
        detalles.remove(detalle);
        detalle.setPedido(null);
    }

    public void calcularTotal() {
        this.total = detalles.stream()
                             .map(detalle -> detalle.getPrecio().multiply(new BigDecimal(detalle.getCantidad())))
                             .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuario=" + user.getUsername() +
                ", fechaPedido=" + fechaPedido +
                ", estado=" + estado +
                ", total=" + total +
                '}';
    }
}
