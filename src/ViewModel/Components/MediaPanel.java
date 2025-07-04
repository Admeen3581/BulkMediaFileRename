package ViewModel.Components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaPanel extends JPanel
{
   private final CardLayout cardLayout = new CardLayout();
   private final JPanel cardPanel = new JPanel(cardLayout);

   private final JLabel placeholderLabel = new JLabel("No media loaded", SwingConstants.CENTER);
   private final JLabel imageLabel = new JLabel(null, SwingConstants.CENTER);
   private final JFXPanel videoPanel = new JFXPanel();

   public MediaPanel() {
      // Initialize Swing panel with CardLayout
      setLayout(new BorderLayout());

      // Placeholder card
      cardPanel.add(placeholderLabel, "placeholder");
      // Image card
      cardPanel.add(new JScrollPane(imageLabel), "image");
      // Video card (JavaFX)
      cardPanel.add(videoPanel, "video");

      add(cardPanel, BorderLayout.CENTER);
      cardLayout.show(cardPanel, "placeholder");

      // Ensure JavaFX platform is initialized
      Platform.setImplicitExit(false);
   }

   /**
    * Display an image
    */
   public void showImage(ImageIcon img) {
      imageLabel.setIcon(img);
      placeholderLabel.setText(null);
      cardLayout.show(cardPanel, "image");
   }

   /**
    * Display placeholder text
    */
   public void showPlaceholder(String text) {
      imageLabel.setIcon(null);
      placeholderLabel.setText(text);
      cardLayout.show(cardPanel, "placeholder");
   }

   /**
    * Play video from a file path
    */
   public void showVideo(String videoPath) {
      File file = new File(videoPath);
      if (!file.exists()) {
         showPlaceholder("Video not found");
         return;
      }

      Platform.runLater(() -> {
         try {
            Media media = new Media(file.toURI().toString());
            MediaPlayer player = new MediaPlayer(media);
            MediaView mediaView = new MediaView(player);

            Group root = new Group();
            root.getChildren().add(mediaView);
            Scene scene = new Scene(root);

            videoPanel.setScene(scene);
            player.setAutoPlay(true);

            // Switch to video card on EDT
            SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "video"));
         } catch (Exception e) {
            SwingUtilities.invokeLater(() -> showPlaceholder("Failed to play video"));
         }
      });
   }
}
