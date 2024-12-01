module org.openjfx {
    requires javafx.controls;
    requires org.hibernate.orm.core; // Módulo de Hibernate ORM
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt; // Para encriptar contraseñas
  
    // Abre paquetes a otros módulos
    opens org.openjfx to javafx.fxml;
    opens org.openjfx.model to org.hibernate.orm.core;
    opens org.openjfx.views;
    opens org.openjfx.model.Details to org.hibernate.orm.core;


    // Exporta paquetes
    exports org.openjfx;
    // Exporta otros paquetes si es necesario

}