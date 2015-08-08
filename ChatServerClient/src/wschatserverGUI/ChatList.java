package wschatserverGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import wschatserver.ChatServer;

public class ChatList extends JLabel implements ListCellRenderer<Object> {

	private static final long serialVersionUID = -4205179838382831753L;

	public ChatList(ChatServer service) {
		setOpaque(true);
		this.service = service;
	}

	private ChatServer service;
	private Color COLOR_PURPLE = new Color(200, 170, 212);
	private ImageIcon normalUser = new ImageIcon(
			Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/user.png")));
	private ImageIcon adminUser = new ImageIcon(
			Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.png")));
	private int AllUserPos = 0;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		setText(value.toString());
		setIcon(normalUser);

		Color background;
		Color foreground;
		Font font;

		// check if this cell represents the current DnD drop location
		JList.DropLocation dropLocation = list.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {

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
		}
		
		// Different font attributes for All Users Selection
		if (index == AllUserPos) {
			setIcon(null);
			font = new Font("Tohoma", Font.BOLD, 12);
		} else {
			font = new Font("Tohoma", Font.PLAIN, 12);
		}
		
		// If the user is an admin
		try {
			if (service.isAdmin(value.toString())) {
				foreground = Color.RED;
				setIcon(adminUser);
			}
		} catch (RemoteException e) {
			
		}

		setBackground(background);
		setForeground(foreground);
		setFont(font);

		return this;
	}

}