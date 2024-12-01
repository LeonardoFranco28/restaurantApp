package org.openjfx.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.openjfx.auth.SessionManager;
import org.openjfx.controller.MenuController;
import org.openjfx.model.MenuItem; // Renombrado para evitar conflicto con esta clase
import org.openjfx.model.User;
import org.openjfx.views.components.CrearPedidos;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private MenuController menuController;

    public Menu() {
        this.menuController = new MenuController();
    }

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
        Button logoutButton = new Button("Cerrar Sesión");
        logoutButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> logout(primaryStage));
        sidebar.getChildren().add(logoutButton);

        // Ajustar el ancho del sidebar
        sidebar.setPrefWidth(250);

        // Posicionar el sidebar en el lado izquierdo
        layout.setLeft(sidebar);

        // Crear el área central
        VBox centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setPadding(new Insets(15));

        Label welcomeLabel = new Label("¡Bienvenido al sistema!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label roleLabel = new Label("Tu rol es: " + (userRole != null ? userRole : "No autenticado"));
        roleLabel.setStyle("-fx-font-size: 16px;");

        centerLayout.getChildren().addAll(welcomeLabel, roleLabel);

        // Obtener la lista de menús desde el controlador
        List<MenuItem> menus = MenuController.getAllMenus();

        System.out.println("Menus obtenidos: " + menus);
        // Si la lista está vacía, insertar menús de ejemplo
        if (menus == null || menus.isEmpty()) {
            MenuController.insertSampleMenus();
            menus = MenuController.getAllMenus();
        }

        // Verificar si 'menus' sigue siendo null para evitar NullPointerException
        if (menus == null) {
            System.err.println("La lista de menus es null despues de intentar obtenerlos.");
            menus = new ArrayList<>(); // Crear una lista vacía para evitar errores
        }

        // Crear una sección para mostrar los menús
        Label menuLabel = new Label("Menú Disponible");
        menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        ListView<String> menuListView = new ListView<>();

        for (MenuItem item : menus) {
            String menuItemString = item.getId() + " - " + item.getName() + " - $" + item.getPrice() + "\n"
                    + item.getDescription();
            menuListView.getItems().add(menuItemString);
        }

        // Agregar el ListView al centro del layout
        centerLayout.getChildren().addAll(menuLabel, menuListView);

        layout.setCenter(centerLayout);

        // Crear la escena y aplicar el CSS
        Scene scene = new Scene(layout, 800, 600);

        // Aplicar estilos CSS si es necesario
        // scene.getStylesheets().add(getClass().getResource("/styles/Menu.css").toExternalForm());

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
            System.out.println("Registro de pedidos seleccionado");
        });
        gestionPedidosBtn.setOnAction(e -> {
            // Lógica para gestión de pedidos
            System.out.println("Gestión del pedido seleccionado");
        });
        generacionFacturaBtn.setOnAction(e -> {
            // Lógica para generación de factura
            System.out.println("Generación de factura seleccionada");
        });

        vbox.getChildren().addAll(adminLabel, registroPedidosBtn, gestionPedidosBtn, generacionFacturaBtn);
        return vbox;
    }

    // Método para crear las opciones del usuario
    private VBox crearOpcionesUser() {
        VBox vbox = new VBox(10);

        Label userLabel = new Label("");
        userLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button pedidosBtn = new Button("Pedidos");
        CrearPedidos crearPedidos = new CrearPedidos();
       

        // Asignar clases CSS a los botones
        pedidosBtn.getStyleClass().add("sidebar-button");
        
        // Agregar eventos a los botones
        pedidosBtn.setOnAction(e -> {
            // Lógica para la creacion de pedidos
            crearPedidos.mostrarFormularioNuevoPedido();
        });
       

        vbox.getChildren().addAll(userLabel, pedidosBtn);
        return vbox;
    }

    private void logout(Stage primaryStage) {
        SessionManager.clearSession();
        // Redirigir a la pantalla de login o realizar otras acciones necesarias
        System.out.println("Sesión cerrada");
        primaryStage.close();
    }
}
