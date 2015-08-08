package wschatserverAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Create the dialog.
	 */
	public AdminGUI() {

		setTitle("Admin Login");
		setLocationRelativeTo(null);
		//setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png"))));
		setBounds(100, 100, 298, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 48, 14);
		contentPanel.add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(10, 27, 255, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 54, 46, 14);
		contentPanel.add(lblPassword);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(10, 74, 255, 20);
		contentPanel.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		setVisible(true);

	}

}
