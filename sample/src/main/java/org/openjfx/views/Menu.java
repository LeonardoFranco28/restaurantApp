package org.openjfx.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.openjfx.auth.SessionManager;
import org.openjfx.model.User;

public class Menu {

    // Método para mostrar la pantalla principal
    public void mostrarPantallaPrincipal(Stage primaryStage) {
        // Obtener el usuario actual
        User currentUser = SessionManager.getCurrentUser();
        String userRole = currentUser != null ? currentUser.getRole() : null;

        // Crear el layout principal
        BorderPane layout = new BorderPane();

        // Crear el sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(15));
        sidebar.setStyle("-fx-background-color: #2C3E50;"); // Estilo de fondo

        // Crear opciones del sidebar según el rol
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            sidebar.getChildren().addAll(crearOpcionesAdmin());
        } else if ("USER".equalsIgnoreCase(userRole)) {
            sidebar.getChildren().addAll(crearOpcionesUser());
        } else {
            Label noAccessLabel = new Label("Acceso restringido");
            noAccessLabel.setStyle("-fx-text-fill: white;");
            sidebar.getChildren().add(noAccessLabel);
        }

        // Ajustar el ancho del sidebar
        sidebar.setPrefWidth(250);

        // Posicionar el sidebar en el lado izquierdo
        layout.setLeft(sidebar);

        // Crear el área central
        Label welcomeLabel = new Label("¡Bienvenido al sistema!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label roleLabel = new Label("Tu rol es: " + (userRole != null ? userRole : "No autenticado"));
        roleLabel.setStyle("-fx-font-size: 16px;");

        VBox centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.getChildren().addAll(welcomeLabel, roleLabel);

        layout.setCenter(centerLayout);

        // Crear la escena y aplicar el CSS
        Scene scene = new Scene(layout, 800, 600);
        
        //scene.getStylesheets().add(getClass().getResource("Menu.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Gestión");
        primaryStage.show();
    }

    // Método para crear las opciones del administrador
    private VBox crearOpcionesAdmin() {
        VBox vbox = new VBox(10);

        Label adminLabel = new Label("Opciones de Administrador");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button registroPedidosBtn = new Button("Registro de pedidos");
        Button gestionPedidosBtn = new Button("Gestión del pedido");
        Button generacionFacturaBtn = new Button("Generación de factura");

        // Asignar clases CSS a los botones
        registroPedidosBtn.getStyleClass().add("sidebar-button");
        gestionPedidosBtn.getStyleClass().add("sidebar-button");
        generacionFacturaBtn.getStyleClass().add("sidebar-button");

        // Agregar eventos a los botones
        registroPedidosBtn.setOnAction(e -> {
            // Lógica para registro de pedidos
        });
        gestionPedidosBtn.setOnAction(e -> {
            // Lógica para gestión de pedidos
        });
        generacionFacturaBtn.setOnAction(e -> {
            // Lógica para generación de factura
        });

        vbox.getChildren().addAll(adminLabel, registroPedidosBtn, gestionPedidosBtn, generacionFacturaBtn);
        return vbox;
    }

    // Método para crear las opciones del usuario
    private VBox crearOpcionesUser() {
        VBox vbox = new VBox(10);

        Label userLabel = new Label("Administración de Catálogos");
        userLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button clientesBtn = new Button("Clientes");
        Button reservasBtn = new Button("Reservas");
        Button platosBtn = new Button("Platos");
        Button bebidasBtn = new Button("Bebidas");
        Button salonerosBtn = new Button("Saloneros");

        // Asignar clases CSS a los botones
        clientesBtn.getStyleClass().add("sidebar-button");
        reservasBtn.getStyleClass().add("sidebar-button");
        platosBtn.getStyleClass().add("sidebar-button");
        bebidasBtn.getStyleClass().add("sidebar-button");
        salonerosBtn.getStyleClass().add("sidebar-button");

        // Agregar eventos a los botones
        clientesBtn.setOnAction(e -> {
            // Lógica para administración de clientes
        });
        reservasBtn.setOnAction(e -> {
            // Lógica para administración de reservas
        });
        platosBtn.setOnAction(e -> {
            // Lógica para administración de platos
        });
        bebidasBtn.setOnAction(e -> {
            // Lógica para administración de bebidas
        });
        salonerosBtn.setOnAction(e -> {
            // Lógica para administración de saloneros
        });

        vbox.getChildren().addAll(userLabel, clientesBtn, reservasBtn, platosBtn, bebidasBtn, salonerosBtn);
        return vbox;
    }

    private void logout(Stage primaryStage) {
        SessionManager.clearSession();
        // Redirigir a la pantalla de login o realizar otras acciones necesarias
    }
}
