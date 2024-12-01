package org.openjfx.views.components;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import org.openjfx.controller.MenuController;
import org.openjfx.model.MenuItem;

public class CrearPedidos {

    public void mostrarFormularioNuevoPedido() {
        Stage pedidoStage = new Stage();
        pedidoStage.setTitle("Nuevo Pedido");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ecf0f1;");

        Label titleLabel = new Label("Crear Nuevo Pedido");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> clienteComboBox = new ComboBox<>();
        clienteComboBox.setPromptText("Seleccionar la Mesa");
        // Aquí deberías cargar la lista de clientes desde tu base de datos
        clienteComboBox.getItems().addAll("Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4", "Mesa 5", "Mesa 6", "Mesa 7",
                "Mesa 8", "Mesa 9", "Mesa 10");

        Label menuItemsLabel = new Label("Seleccionar Ítems del Menú");
        // Obtener la lista de menús desde el controlador

        // Obtener la lista de menús desde el controlador
        List<MenuItem> menuItems = MenuController.getAllMenus();

        ListView<String> menuItemsListView = new ListView<>();
        menuItemsListView.setPrefHeight(150);

        // Convertir los MenuItem a String y agregarlos al ListView usando streams
        menuItemsListView.getItems().addAll(
                menuItems.stream()
                        .map(MenuItem::getName) // Asumiendo que MenuItem tiene un método getName()
                        .collect(Collectors.toList()));

        TextField cantidadField = new TextField();
        cantidadField.setPromptText("Cantidad");

        Button agregarItemBtn = new Button("Agregar Item");
        agregarItemBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        ListView<String> pedidoItemsListView = new ListView<>();
        pedidoItemsListView.setPrefHeight(150);

        Button crearPedidoBtn = new Button("Crear Pedido");
        crearPedidoBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");

        agregarItemBtn.setOnAction(e -> {
            String selectedItem = menuItemsListView.getSelectionModel().getSelectedItem();
            String cantidad = cantidadField.getText();
            if (selectedItem != null && !cantidad.isEmpty()) {
                pedidoItemsListView.getItems().add(selectedItem + " x " + cantidad);
                cantidadField.clear();
            }
        });

        crearPedidoBtn.setOnAction(e -> {
            // Aquí iría la lógica para guardar el pedido en la base de datos
            System.out.println("Pedido creado para: " + clienteComboBox.getValue());
            System.out.println("Items del pedido: " + pedidoItemsListView.getItems());
            pedidoStage.close();
        });

        layout.getChildren().addAll(titleLabel, clienteComboBox, menuItemsLabel, menuItemsListView,
                cantidadField, agregarItemBtn, pedidoItemsListView, crearPedidoBtn);

        Scene scene = new Scene(layout, 400, 500);
        pedidoStage.setScene(scene);
        pedidoStage.show();
    }
}