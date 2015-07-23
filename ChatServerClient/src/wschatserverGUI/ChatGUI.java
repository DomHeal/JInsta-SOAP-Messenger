package wschatserverGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.rmi.Remote;
import java.util.HashMap;

import javax.swing.*;
import wschatserver.ChatServer;
import wschatserver.ChatServerServiceLocator;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import com.sun.prism.Image;

import javax.swing.border.MatteBorder;

public class ChatGUI {

	/* Variables */
	public JFrame frame;
	private JTextArea myText;
	private static JTextArea otherText;
	private static JTextField usernameText;
	private JScrollPane myTextScroll;
	private JScrollPane otherTextScroll;
	private static ChatListener otherTextThread;
	private String textString = "";
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JPanel topPanel;
	private JLabel usernameLbl;

	public static final String DISCONNECTED_TITLE = "Chat Client - Disconnected";
	public static final String CONNECTED_TITLE = "Chat Client - Connected - Users Online: ";

	private static final int HOR_SIZE = 500;
	private static final int VER_SIZE = 250;

	private static final int HOR_SIZE_INPUT = 500;
	private static final int VER_SIZE_INPUT = 50;

	public static ChatServer service;
	public static Remote port;
	private int id;
	private String username;
	private String receiverUsername;
	private JPanel westPanel;
	private static JList<String> userList;

	private final int USERNAME_TAKEN = -1;

	/* Creates GUI */
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initComponents() {

		frame = new JFrame("Chat Client");

		usernameLbl = new JLabel("Username");
		usernameText = new JTextField(15);
		connectBtn = new JButton("Connect");
		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setEnabled(false);
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setBackground(new Color(200, 170, 212));

		topPanel.add(usernameLbl);
		topPanel.add(usernameText);
		topPanel.add(connectBtn);
		topPanel.add(disconnectBtn);
		frame.getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

		westPanel = new JPanel();
		westPanel.setBorder(new TitledBorder(null, "Users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setBackground(Color.WHITE);
		westPanel.setSize(new Dimension(200, 200));
		westPanel.setMinimumSize(new Dimension(200, 200));
		westPanel.setPreferredSize(new Dimension(100, 200));

		otherText = new JTextArea();
		otherText.setFont(new Font("Calibri", Font.PLAIN, 14));
		otherText.setLineWrap(true);

		otherTextScroll = new JScrollPane(otherText);
		otherText.setBackground(new Color(255, 255, 255));
		otherTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		otherTextScroll.setMaximumSize(new Dimension(HOR_SIZE, VER_SIZE));
		otherTextScroll.setMinimumSize(new Dimension(HOR_SIZE, VER_SIZE));
		otherTextScroll.setPreferredSize(new Dimension(HOR_SIZE, VER_SIZE));
		otherText.setEditable(false);

		frame.getContentPane().add(otherTextScroll, BorderLayout.CENTER);
		myText = new JTextArea();
		myText.setLineWrap(true);

		myTextScroll = new JScrollPane(myText);
		myTextScroll.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(200, 170, 212)));
		frame.getContentPane().add(myTextScroll, BorderLayout.SOUTH);
		myTextScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myTextScroll.setMaximumSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setMinimumSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setPreferredSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setEnabled(false);

		myText.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				textTyped(evt);
			}
		});

		userList = new JList<String>();
		userList.setForeground(Color.BLACK);
		westPanel.add(userList);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		/* Listener for the Connect Button */
		connectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {

					// Creates Service
					service = new ChatServerServiceLocator().getChatServer();
					// sets Port
					port = new ChatServerServiceLocator().getPort(ChatServer.class);
					// Gets username for the client
					username = usernameText.getText();

					// Broadcast a user has joined
					id = service.join(id, username, " has joined!");
					if (id == USERNAME_TAKEN) {
						JOptionPane.showMessageDialog(frame, "Username is already Taken!");

					} else {
						// Starts Threads
						otherTextThread = new ChatListener(frame, otherText, id, username, service);
						otherTextThread.start();

						// Displays Connected
						frame.setTitle(CONNECTED_TITLE + service.getUserCount());
						userList.setSelectedIndex(0);

						frame.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent e) {
								try {
									// Broadcast that a user has left
									service.leave(id, username, " has disconnected! \n");
								} catch (Exception ex) {

									System.out.println("Exit Failed");
								}
								System.exit(0);
							}
						});
						// Enables and Disables buttons for GUI
						disconnectBtn.setEnabled(true);
						usernameText.setEnabled(false);
						connectBtn.setEnabled(false);
					}

				} catch (Exception ex) {
					System.out.println(ex);
					JOptionPane.showMessageDialog(frame, "Unable to connect to server");
				}
			}
		});

		/* Disconnect button Listener */
		disconnectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					// When disconnected, interrupt Thread
					otherTextThread.interrupt();
					// Enable / Disable buttons for GUI
					disconnectBtn.setEnabled(false);
					usernameText.setEnabled(true);
					connectBtn.setEnabled(true);

					// Displays Disconnected

					frame.setTitle(DISCONNECTED_TITLE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "Unable to connect to server");
				}
			}
		});

	}
	/*
	 * This method controls the talking and private messages by check if the
	 * first character is an "@" sign, if it is, it will send as a private
	 * message, if not it will broadcast it to all users.
	 */

	private void textTyped(KeyEvent evt) {
		char c = evt.getKeyChar();

		if (c == '\n') {
			try {
				// Removes unwanted characters
				textString = textString.replaceAll("[^A-Za-z0-9/ ]", "");
				
				// if String is Empty, Display Pop-up Message
				if (textString.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Enter a message");
				}

				// Checking if All Users is Selected and not own username 
				else if ((userList.getSelectedValue() != "All Users") && (userList.getSelectedValue() != null)) {
					
					receiverUsername = userList.getSelectedValue().toString();
					
					if (receiverUsername.equals(username)){
						
						JOptionPane.showMessageDialog(frame, "Can't Send Message to Self");
					}
					
					else{
						// Send Private Message
						service.privateMsg(id, username, receiverUsername, ": " + textString);
					}
				} else {
					// if its not a private message, broadcast to all;
					service.talk(id, username + ": " + textString);
				}
				// Once message has sent, input chat box.
				myText.setText("");
				
			} catch (Exception ie) {
				System.out.println(ie);
				myText.setText("");
				JOptionPane.showMessageDialog(frame, "Failed to send message");
			}
			textString = "";
		} else if (c == '\b') {
			textString = textString.substring(0, textString.length() - 1);
		} 
		else {
				textString = textString + c;
		}
	}

	public static void setUserList(HashMap<String, Integer> users) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("All Users");
		for (Object x : users.keySet()) {
			model.addElement(String.valueOf(x));
		}
		addUsers(model);

	}

	private static void addUsers(DefaultListModel<String> model) {
		userList.setModel(model);

	}
}