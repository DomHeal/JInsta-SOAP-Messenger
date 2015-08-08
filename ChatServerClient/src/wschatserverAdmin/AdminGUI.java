package wschatserverAdmin;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import wschatserverGUI.ChatGUI;

public class AdminGUI extends JDialog {

	private static final long serialVersionUID = 6434228078327058813L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private static boolean isAdmin = false;
	private ImageIcon adminIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(AdminGUI.class.getResource("/images/adminIcon.png")));
	private ImageIcon admin_enabled = new ImageIcon(Toolkit.getDefaultToolkit().getImage(AdminGUI.class.getResource("/images/admin_enabled.png")));
	private ImageIcon admin_disabled = new ImageIcon(Toolkit.getDefaultToolkit().getImage(AdminGUI.class.getResource("/images/admin_disabled.png")));
	private Image icon = Toolkit.getDefaultToolkit().getImage(AdminGUI.class.getResource("/images/icon.png"));
	
	private JButton cancelButton;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public AdminGUI() {
		Dialog dialog = this;
		setModal(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setIconImage(icon);
		setTitle("Admin Login");
		
		setBounds(100, 100, 349, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 255, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 20, 14, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.NORTH;
		gbc_lblUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		contentPanel.add(lblUsername, gbc_lblUsername);

		textFieldUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.gridx = 0;
		gbc_textFieldUsername.gridy = 1;
		contentPanel.add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.NORTH;
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		contentPanel.add(lblPassword, gbc_lblPassword);

		textFieldPassword = new JTextField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.anchor = GridBagConstraints.NORTH;
		gbc_textFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPassword.gridx = 0;
		gbc_textFieldPassword.gridy = 3;
		contentPanel.add(textFieldPassword, gbc_textFieldPassword);
		textFieldPassword.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Login");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldUsername.getText().equals("admin") && (textFieldPassword.getText().equals("admin"))) {
					grantAdmin(true);
					setVisible(false);
					ChatGUI.lblAdmin.setIcon(admin_enabled);					
					okButton.setEnabled(false);
					cancelButton.setText("Logout");
					textFieldUsername.setEnabled(false);
					textFieldPassword.setEnabled(false);
				}
				else{
					grantAdmin(false);
					ChatGUI.lblAdmin.setIcon(admin_disabled);
				JOptionPane.showMessageDialog(dialog, "Wrong Username / Password");
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancelButton.getText().equals("Logout")){
					okButton.setEnabled(true);
					cancelButton.setText("Cancel");
					grantAdmin(false);
					ChatGUI.lblAdmin.setIcon(admin_disabled);
					textFieldUsername.setEnabled(true);
					textFieldPassword.setEnabled(true);
				}
				else{
					dispose();
				}
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);

		JLabel lblAdminLoginIcon = new JLabel("");
		lblAdminLoginIcon.setIcon(adminIcon);
		northPanel.add(lblAdminLoginIcon);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	protected void grantAdmin(boolean b) {
		isAdmin = true;
	}

	public static boolean isAdmin() {
		return isAdmin;
	}
}
