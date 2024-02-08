/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.mycompany.proyectopgvdad.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class BienvenidaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button botonAyuda;

    @FXML
    private ImageView botonCerrar;

    @FXML
    private Button botonIniciar;

    @FXML
    private Button botonSobre;

    int contador;

    @FXML
    void abrirAyuda(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("Esta función todavía no está implementada");
        alert.setContentText("La añadiremos próximamente.");
        alert.show();
    }

    @FXML
    void cambiarVentana(ActionEvent event) throws IOException {
        if (contador < 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se han encontrado servidores");
            alert.setContentText("Conecte al menos un servidor y vueva a intentarlo");
            alert.show();
            contador++;
        } else {
            String ventana = (event.getSource().equals(botonIniciar)) ? "PantallaPrincipal.fxml" : "SobreNosotros.fxml";
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            FXMLLoader escena = new FXMLLoader(App.class.getResource(ventana));
            Parent looker = escena.load();
            Scene scene = new Scene(looker);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void ponerManito(MouseEvent event) {
        botonIniciar.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    void quitarManito(MouseEvent event) {
        botonIniciar.getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    void cerrar(MouseEvent event) {
        System.exit(0);
    }
}
