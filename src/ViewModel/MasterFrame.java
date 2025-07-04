package ViewModel;

//Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The MasterFrame class extends the JavaFX Application class to configure
 * and launch a primary JavaFX application window. This class initializes
 * the JavaFX application and sets up a basic scene containing a group of
 * elements.
 * <p>
 * The application's main responsibility is to provide an entry point for
 * launching a JavaFX-based graphical user interface (GUI).
 */

public class MasterFrame extends Application
{
   @Override
   public void start(Stage stage) throws Exception
   {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MasterFrame.fxml")));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
   }
}
