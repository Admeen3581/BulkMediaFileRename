package ViewModel.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel
{
   public final JButton renameBtn = new JButton("Rename");
   public final JButton deleteBtn = new JButton("Delete");

   public SelectionPanel(ActionListener renameAction, ActionListener deleteAction) {
      add(renameBtn);
      add(deleteBtn);
      renameBtn.addActionListener(renameAction);
      deleteBtn.addActionListener(deleteAction);
   }
}
