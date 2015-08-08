package wschatserverGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Remote;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyledDocument;

import wschatserver.ChatServer;
import wschatserver.ChatServerServiceLocator;
import wschatserverAdmin.AdminGUI;

public class ChatGUI {

	/* Variables */
	public JFrame frame;
	private JTextArea myText;
	private static JTextField usernameText;
	private JScrollPane myTextScroll;
	private static ChatListener otherTextThread;
	private String messageText = "";
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JPanel topPanel;
	private JLabel usernameLbl;
	private Color COLOR_PURPLE = new Color(200, 170, 212);

	public static final String DISCONNECTED_TITLE = "Chat Client - Disconnected";
	public static final String CONNECTED_TITLE = "Chat Client - Connected - Users Online: ";

	private static final int WEST_HOR_SIZE = 100;
	private static final int WEST_VER_SIZE = 200;

	private static final int HOR_SIZE_INPUT = 500;
	private static final int VER_SIZE_INPUT = 50;

	private static final int MAX_USERNAME_LENGTH = 12;

	public static ChatServer service;
	public static Remote port;
	private int id;
	private String username;
	private String receiverUsername;
	private JPanel westPanel;
	private static JList<String> userList;
	private static DefaultListModel<String> model = new DefaultListModel<String>();

	private final int USERNAME_TAKEN = -1;
	private JTextPane textPane;
	private JLabel lblAdmin;
	
	

	/* Creates GUI */
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initComponents() {

		frame = new JFrame("Chat Client");

		usernameLbl = new JLabel("Username");
		usernameText = new JTextField(12);
		connectBtn = new JButton("Connect");
		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setEnabled(false);
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setBackground(COLOR_PURPLE);
		
		lblAdmin = new JLabel();
		lblAdmin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.png"))));
		lblAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AdminGUI();
			}
		});
		topPanel.add(lblAdmin);

		topPanel.add(usernameLbl);
		topPanel.add(usernameText);
		topPanel.add(connectBtn);
		topPanel.add(disconnectBtn);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);

		westPanel = new JPanel();
		westPanel.setBorder(new TitledBorder(null, "Users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setBackground(Color.WHITE);
		westPanel.setSize(new Dimension(WEST_HOR_SIZE, WEST_VER_SIZE));
		westPanel.setMinimumSize(new Dimension(WEST_HOR_SIZE, WEST_VER_SIZE));
		westPanel.setPreferredSize(new Dimension(WEST_HOR_SIZE, WEST_VER_SIZE));

		textPane = new JTextPane();
		JScrollPane sp = new JScrollPane(textPane);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		textPane.setEditable(false);
		StyledDocument doc = textPane.getStyledDocument();
		frame.getContentPane().add(sp, BorderLayout.CENTER);
		myText = new JTextArea();
		myText.setLineWrap(true);

		myTextScroll = new JScrollPane(myText);
		myTextScroll.setViewportBorder(new MatteBorder(5, 5, 5, 5, COLOR_PURPLE));
		frame.getContentPane().add(myTextScroll, BorderLayout.SOUTH);
		myTextScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myTextScroll.setMaximumSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setMinimumSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setPreferredSize(new Dimension(HOR_SIZE_INPUT, VER_SIZE_INPUT));
		myTextScroll.setEnabled(false);

		myText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				textTyped(evt);
			}
		});

		userList = new JList<String>();
		userList.setForeground(Color.BLACK);
		westPanel.add(userList);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/* Listener for the Connect Button */
		connectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (usernameText.getText().isEmpty() || usernameText.getText().length() > MAX_USERNAME_LENGTH) {
						JOptionPane.showMessageDialog(frame, "Enter a Username between 1-15");
					} else {
						
						// Creates Service
						service = new ChatServerServiceLocator().getChatServer();
						// sets Port
						port = new ChatServerServiceLocator().getPort(ChatServer.class);
						// Gets username for the client
						username = usernameText.getText();

						// Broadcast a user has joined
						id = service.join(id, username);

						if (id == USERNAME_TAKEN) {
							JOptionPane.showMessageDialog(frame, "Username is already Taken!");

						} else {
							// Starts Threads
							otherTextThread = new ChatListener(frame, doc, textPane, id, username, service);
							otherTextThread.start();

							// Displays Connected
							frame.setTitle(CONNECTED_TITLE + service.getUserCount());
							userList.setSelectedIndex(0);

	
							// Enables and Disables buttons for GUI
							disconnectBtn.setEnabled(true);
							usernameText.setEnabled(false);
							connectBtn.setEnabled(false);
						}
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
					service.leave(username);
					otherTextThread.terminate();
					
					// Make connection null
					service = null;
					port = null;
					
					// Enable / Disable buttons for GUI
					disconnectBtn.setEnabled(false);
					usernameText.setEnabled(true);
					connectBtn.setEnabled(true);

					// Displays Disconnected
					frame.setTitle(DISCONNECTED_TITLE);
				
					// Clear View
					myText.setText("");
					textPane.setText(null);
					model.clear();
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "Unable to connect to server");
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

					disconnectBtn.doClick();
					System.exit(0);
			
			}
		});
	}
	
	/*
	 * This method controls the Global and Private messages by, waiting until the enter
	 * key has been pressed. It will remove all invalid characters from the Message before
	 * it will broadcast it to all users.
	 */

	private void textTyped(KeyEvent evt) {
		char c = evt.getKeyChar();

		if (c == '\n') {
			try {
				// Gets Message
				messageText = myText.getText();
				
				// Removes unwanted characters
				messageText = messageText.replaceAll("[^A-Za-z0-9?!.,/!£$%^&*-=+_#@ ]", "");

				// if String is Empty, Display Pop-up Message
				if (messageText.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Enter a message");
				}

				// Checking if All Users is Selected and not own username
				else if ((userList.getSelectedIndex() != 0) && (userList.getSelectedValue() != null)) {

					receiverUsername = userList.getSelectedValue().toString();

					if (receiverUsername.equals(username)) {

						JOptionPane.showMessageDialog(frame, "Can't Send Message to Self");
					}

					else {
						// Send Private Message
						service.privateMsg(id, username, receiverUsername, ": " + messageText);
					}
				} else {
					// if its not a private message, broadcast to all;
					service.talk(id, username + ": " + messageText);
				}
				// Once message has sent, input chat box.
				myText.setText("");

			} catch (Exception ie) {
				System.out.println(ie);
				myText.setText("");
				JOptionPane.showMessageDialog(frame, "Failed to send message");
			}
		}
	}

	public static void setUserList(HashMap<String, Integer> users) {
		// Clear List before Creating
		model.clear();
		
		// Add All Users to list
		model.addElement("All Users (" + users.size() + ")");
		
		// Loop through users
		for (Object x : users.keySet()) {
			model.addElement(String.valueOf(x));
		}
		addUsers(model);

	}

	private static void addUsers(DefaultListModel<String> model) {
		userList.setModel(model);
		userList.setCellRenderer(new ChatList());

	}
}