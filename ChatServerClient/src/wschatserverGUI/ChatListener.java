package wschatserverGUI;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
	private StyledDocument doc;
	private SimpleAttributeSet pmRecieveATT = new SimpleAttributeSet();
	private SimpleAttributeSet pmSendATT = new SimpleAttributeSet();
	private boolean run = true;
	
	

	ChatListener(JFrame frame, StyledDocument doc, JTextPane textPane, int id, String username, ChatServer service)
			throws IOException {
		this.doc = doc;
		this.id = id;
		this.service = service;
		this.username = username;
		this.frame = frame;
			
	}

	public void run() {
		
		// while connected to service
		while (run) {
			try {
				// Store UserCount locally
				long userCountUpdate = service.getUserCount();
				
				// Check if the Usercount has changed
				if (userCountUpdate != userCountCurrent) {
					userCountCurrent = userCountUpdate;

					frame.setTitle(CONNECTED_TITLE + userCountCurrent);

					HashMap<String, Integer> users = service.getUsernames();

					ChatGUI.setUserList(users);

				}
				// Listen for Messages
				String newText = service.listen(id);
				
				// Check if the Message is a PM
				if (!newText.equals("")) {
					if (newText.startsWith("[PM]")){
						 pmRecieveATT.addAttribute(StyleConstants.CharacterConstants.Foreground, new Color(142, 68, 173));
						 doc.insertString(doc.getLength(), newText + "\n", pmRecieveATT );
						 
					} else if (newText.startsWith("[YOU]")){
						pmSendATT.addAttribute(StyleConstants.CharacterConstants.Foreground, new Color(22, 160, 133));
						doc.insertString(doc.getLength(), newText + "\n", pmSendATT );
					}
					else{
						doc.insertString(doc.getLength(), newText + "\n", null );
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException x) {
				try {
					service.leave(username);
				} catch (Exception ex) {
				}
				// Remove port / service.
				ChatGUI.service = null;

				// Breaks While loop - disconnected
				run = false;
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(frame, "Error reading from server.");
			}
		}

	}

	public void terminate() {
		this.run = false;
		
	}
}
