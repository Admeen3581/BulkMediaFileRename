package ViewModel.Components;

import javax.swing.*;
import java.awt.*;

public class RenamingTextPanel extends JPanel
{
   private final JTextField textField = new JTextField(20);

   public RenamingTextPanel() {
      setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      add(new JLabel("New name:"));
      add(textField);
   }

   public String getText() {
      return textField.getText().trim();
   }

   public void clear() {
      textField.setText("");
   }
}
