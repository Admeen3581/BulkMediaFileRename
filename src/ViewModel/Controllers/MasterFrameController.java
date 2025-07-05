package ViewModel.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MasterFrameController implements Initializable
{
   @FXML
   private MediaView mediaViewer;
   private Button renameButton, deleteButton;

   private File file;
   private Media media;
   private MediaPlayer mediaPlayer;

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle)
   {
      this.file = new File("assets/AUPJ8534.mp4");
      this.media = new Media(file.toURI().toString());
      this.mediaPlayer = new MediaPlayer(media);
      this.mediaPlayer.setAutoPlay(true);
      this.mediaViewer.setMediaPlayer(mediaPlayer);
   }

   public void renameButtonTrigger(ActionEvent event)
   {
      System.out.println( "Hello World! Rename");
   }

   public void deleteButtonTrigger(ActionEvent event)
   {
      System.out.println( "Hello World! Delete");
   }
}
