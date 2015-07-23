package wschatserverGUI;

public class ChatClient {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	ChatGUI client = new ChatGUI();

            public void run() {
                client.initComponents();
            }
        });

    }
}
