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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class MonitoreoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button botonTemperatura;

    @FXML
    private Button generarInforme;

    @FXML
    private Button graficoCPU;

    @FXML
    private Button graficoRAM;

    @FXML
    private Button graficoRed;

    @FXML
    private Button monitorUSB;

    @FXML
    private Label titulo;

    @FXML
    private ImageView volverBoton;

    @FXML
    void graficoPie(ActionEvent event) throws IOException {
        App.tituloGrafico = (event.getSource().equals(graficoRAM)) ? "Monitoreo RAM" : "Monitoreo CPU";
        cambiarVentana("GraficoPie.fxml", event);
    }

    @FXML
    void graficoRed(ActionEvent event) throws IOException {
        cambiarVentana("TraficoRed.fxml", event);
    }

    @FXML
    void monitor(ActionEvent event) throws IOException {
        cambiarVentana("MonitorUSB.fxml", event);
    }

    @FXML
    void temperatura(ActionEvent event) throws IOException {
        cambiarVentana("Temperatura.fxml", event);
    }

    private void cambiarVentana(String pantalla, ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader escena = new FXMLLoader(App.class.getResource(pantalla));
        Parent looker = escena.load();
        Scene scene = new Scene(looker);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void generarInforme(ActionEvent event) {

    }

    @FXML
    void volver(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader escena = new FXMLLoader(App.class.getResource("PantallaPrincipal.fxml"));
        Parent looker = escena.load();
        Scene scene = new Scene(looker);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    void ponerManito(MouseEvent event) {
        monitorUSB.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    void quitarManito(MouseEvent event) {
        monitorUSB.getScene().setCursor(Cursor.DEFAULT);
    }
}
