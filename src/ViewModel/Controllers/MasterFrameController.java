package ViewModel.Controllers;

import FileHandler.DirectoryBrowser;
import FileHandler.DirectoryIterator;
import FileHandler.ExtensionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MasterFrameController implements Initializable
{
   @FXML
   private MediaView videoViewer;
   @FXML
   private ImageView imageViewer;

   private File file;//doesn't support .MOV

   /**
    * Initializes the controller and sets up media display functionality.
    *
    * This method is called after the FXML loader has fully initialized the scene. It attempts
    * to load a media file and determine its type based on the file extension. Supported file
    * types include videos (e.g., ".mp4") and images (e.g., ".gif", ".jpg", ".png", ".jpeg").
    * It then displays the appropriate media type in the respective viewer component. If the
    * file type is unsupported, no media is displayed, and an error message is logged.
    *
    * @param url the location used to resolve relative paths for the root object
    * @param resourceBundle the resource bundle to localize the root object
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle)
   {
      try
      {
         //Get a single file to start.
         this.file = DirectoryIterator.iterate(DirectoryBrowser.selectDirectory("Select a directory to browse")).get(0);

         if (!file.exists())
         {
            System.err.println("Media file not found: " + file.getAbsolutePath());
            return;
         }
         String fileName = this.file.getName().toUpperCase();

         if (ExtensionHandler.isVideo(fileName))
         {
            displayVideo();
         }

         else if(ExtensionHandler.isImage(fileName))
         {
            displayImage();
         }
         else
         {
            System.err.println("Unsupported file type: " + fileName);
            videoViewer.setVisible(false);
            imageViewer.setVisible(false);
         }
      }
      catch (Exception e)
      {
         System.err.println("Error initializing media player: " + e.getMessage());
         e.printStackTrace();
      }
   }

   /**
    * Handles the action event triggered by pressing the rename button.
    *
    * @param event the ActionEvent object that represents the user interaction with the rename button
    */
   @FXML
   public void renameButtonTrigger(ActionEvent event)
   {
      System.out.println( "Hello World! Rename");
   }

   /**
    * Handles the action event triggered by pressing the delete button.
    *
    * @param event the ActionEvent object that represents the user interaction with the delete button
    */
   @FXML
   public void deleteButtonTrigger(ActionEvent event)
   {
      System.out.println( "Hello World! Delete");
   }

   /**
    * Displays a video in the assigned MediaView component.
    *
    * This method initializes a Media object with the file URI, creates a MediaPlayer to play the video,
    * and associates the MediaPlayer with the MediaView component (`videoViewer`). The video is
    * configured to autoplay and loop indefinitely. If an image is currently being displayed in the
    * ImageView component (`imageViewer`), it is hidden to ensure only the video is visible.
    */
   private void displayVideo()
   {
      Media media = new Media(file.toURI().toString());
      MediaPlayer player = new MediaPlayer(media);
      player.setAutoPlay(true);
      player.setCycleCount(MediaPlayer.INDEFINITE);
      this.videoViewer.setMediaPlayer(player);

      this.videoViewer.setVisible(true);
      this.imageViewer.setVisible(false);
   }

   /**
    * Displays an image in the assigned ImageView component.
    *
    * This method initializes an Image object with the URI of the file, sets it
    * to the ImageView component (`imageViewer`), and ensures the component is
    * visible. If a video is currently being displayed in the MediaView component
    * (`videoViewer`), it is hidden to ensure only the image is visible.
    */
   private void displayImage()
   {
      Image image = new Image(file.toURI().toString());
      this.imageViewer.setImage(image);

      this.imageViewer.setVisible(true);
      this.videoViewer.setVisible(false);
   }
}
