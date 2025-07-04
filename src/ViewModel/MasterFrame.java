package ViewModel;

//Imports
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The MasterFrame class extends the JavaFX Application class to configure
 * and launch a primary JavaFX application window. This class initializes
 * the JavaFX application and sets up a basic scene containing a group of
 * elements.
 *
 * The application's main responsibility is to provide an entry point for
 * launching a JavaFX-based graphical user interface (GUI).
 */

public class MasterFrame extends Application
{
   /**
    * Initializes and displays the primary stage of the JavaFX application.
    * This method is invoked by the JavaFX framework after the application has
    * started. It sets up the stage with a scene containing a group of elements
    * and makes the stage visible.
    *
    * @param stage the primary stage provided by the JavaFX runtime. This stage
    *              serves as the top-level container for the application.
    * @throws Exception if any error occurs during the initialization or setup
    *                   of the stage.
    */
   @Override
   public void start(Stage stage) throws Exception
   {
      Group group = new Group();
      Scene scene = new Scene(group);

      stage.setScene(scene);
      stage.show();
   }
}
