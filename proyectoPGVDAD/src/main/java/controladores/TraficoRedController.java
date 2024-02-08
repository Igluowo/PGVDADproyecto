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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
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

        // Crear la serie de datos
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Tráfico de Red");

        // Agregar datos a la serie
        series.getData().add(new XYChart.Data<>("5 minutos", 100));
        series.getData().add(new XYChart.Data<>("4 minutos", 150));
        series.getData().add(new XYChart.Data<>("3 minutos", 120));
        series.getData().add(new XYChart.Data<>("2 minutos", 200));
        series.getData().add(new XYChart.Data<>("1 minuto", 180));

        // Limpiar datos existentes (puede ser opcional según tus necesidades)
        grafica.getData().clear();

        // Configurar categorías en el eje X
        CategoryAxis xAxis = (CategoryAxis) grafica.getXAxis();
        xAxis.setCategories(FXCollections.observableArrayList("5 minutos", 
                "4 minutos", "3 minutos", "2 minutos", "1 minuto"));

        // Agregar la serie al gráfico
        grafica.getData().add(series);
    }

    @FXML
    private ImageView botonVolver;

    @FXML
    private LineChart<String, Number> grafica;

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
