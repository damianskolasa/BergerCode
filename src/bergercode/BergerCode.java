/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bergercode;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author damiank
 */
public class BergerCode extends Application {

    @Override
    public void start(Stage primaryStage) {
       primaryStage.setTitle("FXML TableView Example");
        try {
            primaryStage.setScene((Scene)FXMLLoader.load(getClass().getResource("mainWindow.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(BergerCode.class.getName()).log(Level.SEVERE, null, ex);
        }
       primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
