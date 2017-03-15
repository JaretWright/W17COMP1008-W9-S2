
package w17march15;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author JWright
 */
public class LaunchCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        // this will load the .fxml document
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CalculatorView.fxml"));
        
        AnchorPane calculatorPane = loader.load();
        
        Scene scene = new Scene(calculatorPane);
        
        primaryStage.setTitle("The Calculator!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
