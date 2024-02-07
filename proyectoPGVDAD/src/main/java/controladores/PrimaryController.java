package controladores;

import com.mycompany.proyectopgvdad.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    private PieChart graficoCPU;

    @FXML
    private PieChart graficoMemoria;

    @FXML
    private Button botonMas;

    @FXML
    private ImageView botonVolver;

    @FXML
    private ImageView tab;

    @FXML
    private TabPane tabPane;

    @FXML
    void cambiarVentana(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PieChart.Data pie = new PieChart.Data("Usada: 15%", 25);
        PieChart.Data pie2 = new PieChart.Data("Disponible: 75%", 75);
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList(
                pie,
                pie2
        );
        ObservableList<PieChart.Data> datos2 = FXCollections.observableArrayList(
                new PieChart.Data("Usada: 60%", 60),
                new PieChart.Data("Disponible: 40%", 40)
        );
        graficoCPU.setData(datos);
        graficoMemoria.setData(datos2);
        Thread updateThread = new Thread(this::actualizarConsumoCPU);
        updateThread.setDaemon(true);
        updateThread.start();
//        try {
//            int port = 6789;
//            ServerSocket socket = new ServerSocket(port);
//            while (true) {
//                Socket conecctionSocket = socket.accept();
//                Runnable cliente = new ClienteControlador(conecctionSocket);
//                Thread clienteHilo = new Thread(cliente);
//                clienteHilo.start();
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void actualizarConsumoCPU() {
        try {
            while (true) {
                // Simulacion de obtecion de datos prueba
                double consumoCPU = obtenerConsumoCPU();
                double disponible = 101 - consumoCPU;
                double consumoMemoria = obtenerConsumoCPU();
                double disponibleMemoria = 101 - consumoMemoria;
                // Actualizar la interfaz de usuario en el hilo de JavaFX
                Platform.runLater(() -> {
                    graficoCPU.getData().get(0).setPieValue(consumoCPU);
                    graficoCPU.getData().get(0).setName("Usada: " + (int) consumoCPU + "%");
                    graficoCPU.getData().get(1).setPieValue(disponible);
                    graficoCPU.getData().get(1).setName("Disponible: " + (int) disponible + "%");
                });

                Platform.runLater(() -> {
                    graficoMemoria.getData().get(0).setPieValue(consumoMemoria);
                    graficoMemoria.getData().get(0).setName("Usada: " + (int) consumoMemoria + "%");
                    graficoMemoria.getData().get(1).setPieValue(disponibleMemoria);
                    graficoMemoria.getData().get(1).setName("Disponible: " + (int) disponibleMemoria + "%");
                });
                // Esperar un tiempo antes de la próxima actualización
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void volver(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader escena = new FXMLLoader(App.class.getResource("Bienvenida.fxml"));
        Parent looker = escena.load();
        Scene scene = new Scene(looker);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private double obtenerConsumoCPU() {
        return Math.random() * 100;
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
