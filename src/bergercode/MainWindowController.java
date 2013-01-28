/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bergercode;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author damiank
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button comp1;
    @FXML
    private Slider slid1;
    @FXML
    private Line from1to2;
    @FXML
    private Button comp2;
    @FXML
    private Slider slid2;
    @FXML
    private Line from2to3;
    @FXML
    private Button comp3;
    @FXML
    private Slider slid3;
    @FXML
    private Line from3to4;
    @FXML
    private Button comp4;
    @FXML
    private Slider slid4;
    @FXML
    private Line from4to5;
    @FXML
    private Button comp5;
    @FXML
    private Slider slid5;
    @FXML
    private Line from5to6;
    @FXML
    private Button comp6;
    @FXML
    private Slider slid6;
    @FXML
    private Line from6to7;
    @FXML
    private Button comp7;
    @FXML
    private Slider slid7;
    @FXML
    private Line from7to8;
    @FXML
    private Button comp8;
    @FXML
    private Slider slid8;
    @FXML
    private Line from8to9;
    @FXML
    private Button comp9;
    @FXML
    private Slider slid9;
    @FXML
    private Line from9to10;
    @FXML
    private Button comp10;
    @FXML
    private Slider slid10;
    @FXML
    private Line from10to1;
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private Button error;
    @FXML
    private ChoiceBox<String> bitsChoiceBox;
    public static final String IDLE = "#99C2EB;";
    public static final String OK = "#30D630;";
    public static final String ERROR = "#EB3333;";
    private ExecutorService es;
    public static boolean isRunning = false;
    public static int bits = 0;
    public static boolean isTotalWar = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                bits = Integer.valueOf(bitsChoiceBox.getValue());
                BergerCodeCalculator.numBits = bits;
                es = Executors.newFixedThreadPool(10);
                isRunning = true;
                LinkedBlockingQueue<Long> lbq1 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq2 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq3 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq4 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq5 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq6 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq7 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq8 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq9 = new LinkedBlockingQueue<>(20);
                LinkedBlockingQueue<Long> lbq10 = new LinkedBlockingQueue<>(20);


                Computer c1 = new Computer(lbq1, lbq10, from1to2, slid1, comp1);
                c1.setStarting(true);
                c1.setNum(1);
                Computer c2 = new Computer(lbq2, lbq1, from2to3, slid2, comp2);
                c2.setNum(2);
                Computer c3 = new Computer(lbq3, lbq2, from3to4, slid3, comp3);
                c3.setNum(3);
                Computer c4 = new Computer(lbq4, lbq3, from4to5, slid4, comp4);
                c4.setNum(4);
                Computer c5 = new Computer(lbq5, lbq4, from5to6, slid5, comp5);
                c5.setNum(5);
                Computer c6 = new Computer(lbq6, lbq5, from6to7, slid6, comp6);
                c6.setNum(6);
                Computer c7 = new Computer(lbq7, lbq6, from7to8, slid7, comp7);
                c7.setNum(7);
                Computer c8 = new Computer(lbq8, lbq7, from8to9, slid8, comp8);
                c8.setNum(8);
                Computer c9 = new Computer(lbq9, lbq8, from9to10, slid9, comp9);
                c9.setNum(9);
                Computer c10 = new Computer(lbq10, lbq9, from10to1, slid10, comp10);
                c10.setNum(10);

                c1.setNeighbour(c2);
                c2.setNeighbour(c3);
                c3.setNeighbour(c4);
                c4.setNeighbour(c5);
                c5.setNeighbour(c6);
                c6.setNeighbour(c7);
                c7.setNeighbour(c8);
                c8.setNeighbour(c9);
                c9.setNeighbour(c10);
                c10.setNeighbour(c1);

                es.submit(c1);
                es.submit(c2);
                es.submit(c3);
                es.submit(c4);
                es.submit(c5);
                es.submit(c6);
                es.submit(c7);
                es.submit(c8);
                es.submit(c9);
                es.submit(c10);

                es.shutdown();


            }
        });

        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                isRunning = false;
            }
        });

        error.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (isTotalWar) {
                    isTotalWar = false;
                    error.setStyle("-fx-scale-x: 1.0; -fx-scale-y: 1.0;");
                } else {
                    isTotalWar = true;
                    error.setStyle("-fx-scale-x: 0.5; -fx-scale-y: 0.5;");
                }
            }
        });

    }

    private void setAllCompsIdle() {
        comp1.setStyle("-fx-background-color: " + IDLE);
        comp2.setStyle("-fx-background-color: " + IDLE);
        comp3.setStyle("-fx-background-color: " + IDLE);
        comp4.setStyle("-fx-background-color: " + IDLE);
        comp5.setStyle("-fx-background-color: " + IDLE);
        comp6.setStyle("-fx-background-color: " + IDLE);
        comp7.setStyle("-fx-background-color: " + IDLE);
        comp8.setStyle("-fx-background-color: " + IDLE);
        comp9.setStyle("-fx-background-color: " + IDLE);
        comp10.setStyle("-fx-background-color: " + IDLE);

    }
}
