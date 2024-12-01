package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.openjfx.utils.HibernateUtil;
import org.openjfx.model.User;
import org.openjfx.views.Menu;
import org.openjfx.auth.SessionManager;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Sistema de Autenticación");

        // Crear etiquetas y campos de texto
        Label titleLabel = new Label("Bienvenido, por favor ingresa tus credenciales");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label userLabel = new Label("Usuario:");
        TextField userTextField = new TextField();
        userTextField.setPromptText("Ingresa tu usuario");

        Label pwLabel = new Label("Contraseña:");
        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Ingresa tu contraseña");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        Button loginButton = new Button("Ingresar");
        loginButton.setPrefWidth(100);

        // Evento del botón de ingresar
        loginButton.setOnAction(e -> {
            String username = userTextField.getText().trim();
            String password = pwBox.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Por favor, completa todos los campos.");
            } else if (SessionManager.authenticateUser(username, password)) {
                // Cambiar la escena en el Stage principal
               Menu menu = new Menu();
               menu.mostrarPantallaPrincipal(primaryStage);
            } else {    
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Usuario o contraseña incorrectos.");
            }
        });

        // Diseño con GridPane para los campos
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.setHgap(10);

        // Posicionamiento de elementos
        GridPane.setConstraints(userLabel, 0, 0);
        GridPane.setConstraints(userTextField, 1, 0);
        GridPane.setConstraints(pwLabel, 0, 1);
        GridPane.setConstraints(pwBox, 1, 1);

        grid.getChildren().addAll(userLabel, userTextField, pwLabel, pwBox);

        // Layout principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, grid, loginButton, messageLabel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para registrar un nuevo usuario (opcional)
    public void registrarUsuario(String username, String password, String role) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            User user = new User();
            user.setUsername(username);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setRole(role);
            session.persist(user);
            System.out.println("Usuario registrado exitosamente.");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void stop() throws Exception {
        HibernateUtil.getSessionFactory().close();
        super.stop();
    }

    public static void main(String[] args) {

        // Registrar un nuevo usuario (opcional)
        App app = new App();
        try {
            
            //app.registrarUsuario("admin", "1234", "ADMIN");
            //app.registrarUsuario("user", "1234", "USER");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        launch(args);
    }
}
