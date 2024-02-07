/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.mycompany.proyectopgvdad.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class GraficoPieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grafico.setTitle(App.tituloGrafico);
        PieChart.Data pie = new PieChart.Data("Usada: 15%", 25);
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList(
                new PieChart.Data("Usada: 60%", 60),
                new PieChart.Data("Disponible: 40%", 40)
        );
        grafico.setData(datos);
        Thread updateThread = new Thread(this::actualizarConsumo);
        updateThread.setDaemon(true);
        updateThread.start();
    }

    @FXML
    private ImageView botonVolver;

    @FXML
    private PieChart grafico;

    @FXML
    void volver(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader escena = new FXMLLoader(App.class.getResource("Monitoreo.fxml"));
        Parent looker = escena.load();
        Scene scene = new Scene(looker);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void actualizarConsumo() {
        try {
            while (true) {
                // Simulacion de obtecion de datos prueba
                double consumo = obtenerConsumo();
                double disponible = 101 - consumo;
                // Actualizar la interfaz de usuario en el hilo de JavaFX
                Platform.runLater(() -> {
                    grafico.getData().get(0).setPieValue(consumo);
                    grafico.getData().get(0).setName("Usada: " + (int) consumo + "%");
                    grafico.getData().get(1).setPieValue(disponible);
                    grafico.getData().get(1).setName("Disponible: " + (int) disponible + "%");
                });
                // Esperar un tiempo antes de la próxima actualización
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ponerManito(MouseEvent event) {
        botonVolver.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    void quitarManito(MouseEvent event) {
        botonVolver.getScene().setCursor(Cursor.DEFAULT);
    }

    private double obtenerConsumo() {
        return Math.random() * 100;
    }
}
