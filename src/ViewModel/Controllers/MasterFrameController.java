package ViewModel.Controllers;

//Imports
import FileHandler.DirectoryBrowser;
import FileHandler.DirectoryIterator;
import FileHandler.ExtensionHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MasterFrameController implements Initializable
{
   @FXML
   private MediaView videoViewer;
   private MediaPlayer mediaPlayer;
   @FXML
   private ImageView imageViewer;
   @FXML
   private TextField renameTextfield;
   @FXML
   private Label renameErrorFieldLabel;
   @FXML
   private Label extensionLabel;

   private File file;//doesn't support .MOV
   private List<File> directory;
   private int directoryCurrentIndex;

   public MasterFrameController()
   {
      this.directory = new ArrayList<>();
   }

   /**
    * Initializes the controller and sets up media display functionality.
    *
    * This method is called after the FXML loader has fully initialized the scene. It attempts
    * to load a media file and determine its type based on the file extension. Supported file
    * types include videos (e.g., ".mp4") and images (e.g., ".gif", ".jpg", ".png", ".jpeg").
    * It then displays the appropriate media type in the respective viewer component. If the
    * file type is unsupported, no media is displayed, and an error message is logged.
    *
    * @param url            the location used to resolve relative paths for the root object
    * @param resourceBundle the resource bundle to localize the root object
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle)
   {
      try
      {
         try
         {
            this.directory.addAll(DirectoryIterator.iterate(DirectoryBrowser.selectDirectory("Select a directory to browse")));
            this.file = this.directory.get(0);//Future: Allow user to override and select a specific start point.
            this.directoryCurrentIndex = 0;
         }
         catch (NullPointerException e)//User Cancel Action
         {
            System.err.println("No directory selected");
            Platform.exit();//force closes the GUI
            return;
         }

         this.runMediaRename();
      }
      catch (Exception e)
      {
         System.err.println("Error initializing media player: " + e.getMessage());
         e.printStackTrace();
      }
   }

   private void runMediaRename()
   {
      this.file = this.directory.get(this.directoryCurrentIndex);

      if (!file.exists())
      {
         System.err.println("Media file not found: " + file.getAbsolutePath());
         return;
      }

      if (ExtensionHandler.isVideo(this.file))
      {
         this.displayVideo();
      }
      else if (ExtensionHandler.isImage(this.file))
      {
         this.displayImage();
      }
      else
      {
         System.err.println("Unsupported file type: " + this.file.getName());
         this.videoViewer.setVisible(false);
         this.imageViewer.setVisible(false);
         return;
      }

      this.extensionLabel.setText("." + this.file.getName().substring(this.file.getName().lastIndexOf(".") + 1).toUpperCase());
      this.renameErrorFieldLabel.setVisible(false);
      this.renameTextfield.setText(this.file.getName().substring(0, this.file.getName().lastIndexOf(".")));

      //Await loading of JavaFX Stage & Scene
      Platform.runLater(() -> {
         renameTextfield.requestFocus();
         renameTextfield.selectAll();
      });
   }

   /**
    * Handles the action event triggered by pressing the rename button.
    *
    * @param event the ActionEvent object that represents the user interaction with the rename button
    */
   @FXML
   public void renameButtonTrigger(ActionEvent event)
   {
      String textFieldInput = this.renameTextfield.getText().trim();

      if (textFieldInput.equals(""))
      {
         this.displayTextfieldError("! - Please enter a valid name");
      }
      else if(textFieldInput.contains("."))
      {
         this.displayTextfieldError("! - Do not include file extension");
      }
      else if(textFieldInput.matches(".*[\\\\/:*?\"<>|!@#$%^&*()\\[\\]{}'\"`~+=;:?].*"))
      {
         this.displayTextfieldError("! - Do not include special characters");
      }
      else if(textFieldInput.length() > 70)
      {
         this.displayTextfieldError("! - File name is too long");
      }
      else if(textFieldInput.length() < 6)
      {
         this.displayTextfieldError("! - File name is too short");
      }
      else
      {
         this.renameErrorFieldLabel.setVisible(false);

         //Video files get locked when playing. Kill the media before renaming.
         if(this.mediaPlayer != null)
         {
            this.mediaPlayer.stop();
            this.mediaPlayer.dispose();
            this.mediaPlayer = null;
         }

         if(this.file.renameTo(new File(this.file.getParentFile(), textFieldInput + this.extensionLabel.getText())))
         {
            //switch scene
            this.directoryCurrentIndex++;
            if(this.directoryCurrentIndex >= this.directory.size())
            {
               this.exit();
            }
            else
            {
               this.runMediaRename();
            }
         }
         else
         {
            this.displayTextfieldError("! - Unable to rename file");
         }
      }
   }


   /**
    * Handles the action event triggered by pressing the delete button.
    *
    * @param event the ActionEvent object that represents the user interaction with the delete button
    */
   @FXML
   public void deleteButtonTrigger(ActionEvent event)
   {
      //Video files get locked when playing. Kill the media before renaming.
      if(this.mediaPlayer != null)
      {
         this.mediaPlayer.stop();
         this.mediaPlayer.dispose();
         this.mediaPlayer = null;
      }

      if(this.file.delete())
      {
         //switch scene
         this.directoryCurrentIndex++;
         if(this.directoryCurrentIndex >= this.directory.size())
         {
            this.exit();
         }
         else
         {
            this.runMediaRename();
         }
      }
      else
      {
         this.displayTextfieldError("! - Unable to delete file");
      }
   }

   /**
    * Displays a video in the assigned MediaView component.
    * <p>
    * This method initializes a Media object with the file URI, creates a MediaPlayer to play the video,
    * and associates the MediaPlayer with the MediaView component (`videoViewer`). The video is
    * configured to autoplay and loop indefinitely. If an image is currently being displayed in the
    * ImageView component (`imageViewer`), it is hidden to ensure only the video is visible.
    */
   private void displayVideo()
   {
      Media media = new Media(this.file.toURI().toString());
      this.mediaPlayer = new MediaPlayer(media);
      this.mediaPlayer.setAutoPlay(true);
      this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      this.videoViewer.setMediaPlayer(this.mediaPlayer);

      this.videoViewer.setVisible(true);
      this.imageViewer.setVisible(false);
   }

   /**
    * Displays an image in the assigned ImageView component.
    * <p>
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

   /**
    * Displays an error message on the rename error label and makes the label visible.
    *
    * This method sets the text of the `renameErrorFieldLabel` to the specified error message
    * and ensures the label is visible to notify the user of an error. Additionally, it
    * triggers a shake animation on the error label to emphasize the message.
    *
    * @param title the error message to be displayed on the rename error label
    */
   private void displayTextfieldError(String title)
   {
      this.renameErrorFieldLabel.setText(title);
      this.renameErrorFieldLabel.setVisible(true);
      shakeErrorMessage();
   }

   /**
    * Creates a shake animation for the rename error message label if it is currently visible.
    *
    * This method applies a horizontal shaking motion to the label `renameErrorFieldLabel`
    * to visually draw attention to an error message. The animation is implemented using a
    * `TranslateTransition` that moves the label horizontally back and forth over a short
    * duration of 50 milliseconds per cycle. The motion consists of four cycles with
    * auto-reversing movements, ensuring the label returns to its original position.
    *
    * The method first checks if the `renameErrorFieldLabel` is visible before initiating
    * the animation, ensuring it only activates when an error message is displayed.
    */
   private void shakeErrorMessage()
   {
      if(this.renameErrorFieldLabel.isVisible())
      {
         // Create a translate transition for the shake effect
         TranslateTransition shake = new TranslateTransition(Duration.millis(50), this.renameErrorFieldLabel);

         shake.setFromX(0);
         shake.setToX(10);

         shake.setCycleCount(4);
         shake.setAutoReverse(true);

         shake.play();
      }
   }


   private void exit()
   {
      //Display that directory rename is complete.
      //Force exit.
   }

   public void addToDirectory(File file)
   {
      this.directory.add(file);
   }
}
