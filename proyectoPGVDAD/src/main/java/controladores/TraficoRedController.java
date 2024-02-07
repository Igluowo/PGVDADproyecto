/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.mycompany.proyectopgvdad.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class TraficoRedController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Tiempo (segundos)");
        yAxis.setLabel("Tráfico de Red");

        // Crear serie de datos
        series = new XYChart.Series<>();
        series.setName("Trafico de Red");

        // Añadir serie al gráfico
        grafica.getData().add(series);

        // Crear animación
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> {
                    // Simular datos de tráfico de red (números aleatorios en este ejemplo)
                    double datosTrafico = Math.random() * 100;
                    // Obtener el tiempo actual en segundos (simulado)
                    double tiempo = System.currentTimeMillis() / 1000.0;
                    // Añadir punto a la serie de datos
                    series.getData().add(new XYChart.Data<>(tiempo, datosTrafico));
                    // Limitar la cantidad de puntos para mantener la visualización manejable
                    if (series.getData().size() > 20) {
                        series.getData().remove(0);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    private XYChart.Series<Number, Number> series;

    @FXML
    private ImageView botonVolver;

    @FXML
    private LineChart<Number, Number> grafica;

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

    @FXML
    void ponerManito(MouseEvent event) {
        botonVolver.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    void quitarManito(MouseEvent event) {
        botonVolver.getScene().setCursor(Cursor.DEFAULT);
    }
}
