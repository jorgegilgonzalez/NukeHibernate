module com.registro.nuke {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;// para que cargue la dependencia de conexion mysql
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    opens com.registro.nuke to javafx.fxml;
    exports com.registro.nuke;//para que pueda acceder el fxml a las clases
    exports modelo;//para que pueda acceder el fxml a las clases
    opens modelo to org.hibernate.orm.core;
}
