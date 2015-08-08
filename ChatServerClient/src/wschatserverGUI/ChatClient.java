package wschatserverGUI;

import javax.swing.SwingUtilities;

public class ChatClient {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
        	ChatGUI client = new ChatGUI();

            public void run() {
                client.initComponents();
            }
        });

    }
}
