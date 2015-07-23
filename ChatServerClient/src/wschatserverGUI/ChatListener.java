package wschatserverGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import wschatserver.ChatServer;

public class ChatListener extends Thread {

	/* Variables */
	ObjectInputStream in;
	JTextArea otherText;
	int id;
	String username;
	private ChatServer service;
	public static final String DISCONNECTED_TITLE = "Chat Client - Disconnected";
	public static final String CONNECTED_TITLE = "Chat Client - Connected - Users Online: ";
	private long userCountCurrent;
	private JFrame frame;
	DefaultListModel<String> model = new DefaultListModel<String>();

	ChatListener(JFrame frame, JTextArea other, int id, String username, ChatServer service)
			throws IOException {
		otherText = other;
		this.id = id;
		this.service = service;
		this.username = username;
		this.frame = frame;
	}

	public void run() {
		boolean run = true;
		// while connected to service
		while (run) {
			try {
				long userCountUpdate = service.getUserCount();
				if (userCountUpdate != userCountCurrent) {
					userCountCurrent = userCountUpdate;

					frame.setTitle(CONNECTED_TITLE + userCountCurrent);

					HashMap<String, Integer> users = service.getUsernames();

					ChatGUI.setUserList(users);

				}
				String newText = service.listen(id);
				if (!newText.equals("")) {
					otherText.append(newText + "\n");
				}
				Thread.sleep(1000);
			} catch (InterruptedException x) {
				try {
					// if interrupted via disconnect button we broadcast the
					// disconnect message
					service.leave(id, username, " has disconnected! \n");
				} catch (Exception ex) {
				}
				// Remove port / service.
				ChatGUI.service = null;

				// Breaks While loop - disconnected
				run = false;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Error reading from server.");
			}
		}

	}
}
