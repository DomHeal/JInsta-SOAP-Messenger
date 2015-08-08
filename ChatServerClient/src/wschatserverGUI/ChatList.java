package wschatserverGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ChatList extends JLabel implements ListCellRenderer<Object> {
	     public ChatList() {
	         setOpaque(true);
	     }
	 	 private Color COLOR_PURPLE = new Color(200, 170, 212);
	 	 private ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/user.png")));
	 	 
	     public Component getListCellRendererComponent(JList<?> list,
	                                                   Object value,
	                                                   int index,
	                                                   boolean isSelected,
	                                                   boolean cellHasFocus) {

	         setText(value.toString());
	         setIcon(icon);

	         Color background;
	         Color foreground;
	         Font font;

	         // check if this cell represents the current DnD drop location
	         JList.DropLocation dropLocation = list.getDropLocation();
	         if (dropLocation != null
	                 && !dropLocation.isInsert()
	                 && dropLocation.getIndex() == index) {

	             background = Color.BLUE;
	             foreground = Color.WHITE;

	         // check if this cell is selected
	         } else if (isSelected) {
	             background = COLOR_PURPLE;
	             foreground = Color.WHITE;

	         // unselected, and not the DnD drop location
	         } else {
	             background = Color.WHITE;
	             foreground = Color.BLACK;
	         };
	         
	         if (index == 0){
	        	 setIcon(null);
	        	 font = new Font("Tohoma", Font.BOLD, 12);
	         }
	         else {
	        	 font = new Font("Tohoma", Font.PLAIN, 12);
	         }
	         
	         setBackground(background);
	         setForeground(foreground);
	         setFont(font);
	         

	         return this;
	     }
	 }