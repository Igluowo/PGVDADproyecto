package com.mycompany.proyectopgvdad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class PrimaryController implements Initializable {

    @FXML
    private PieChart graficoCPU;

    @FXML
    private PieChart graficoMemoria;

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
                double disponible= 100 - consumoCPU; 
                
                // Actualizar la interfaz de usuario en el hilo de JavaFX
                Platform.runLater(() -> {
                    graficoCPU.getData().get(0).setPieValue(consumoCPU);
                    graficoCPU.getData().get(0).setName("Usada: " + (int) consumoCPU + "%");
                    graficoCPU.getData().get(1).setPieValue(disponible);
                    graficoCPU.getData().get(1).setName("Disponible: " + (int) disponible + "%");
                });

                // Esperar un tiempo antes de la próxima actualización (puedes ajustar esto según tus necesidades)
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double obtenerConsumoCPU() {
        // Simulamos un valor entre 0 y 100 para propósitos de ejemplo
        return Math.random() * 100;
    }
}
