package wschatserver;

import javax.jws.WebService;
import java.util.*;

@WebService(serviceName = "ChatServer")
public class ChatServer {

	// Variables
	private static ArrayList<String> serverList = new ArrayList<String>();
	private static HashMap<String, Integer> userList = new HashMap<String, Integer>();
	private Object lock = new Object();

	
	public int getUserCount(){
		int i = userList.size();
		return i;
	}
	
	public HashMap<String, Integer> getUsernames(){
		return userList;
	}
	/* On Connect, Broadcasts messages and adds usernames and id to HashMap */
	public int join(int id, String username) throws InterruptedException {
		synchronized (serverList) {
			
			// If Username is Taken
			if (userList.containsKey(username)){
				return -1;
			}
			
			// Gets user ID
			id = serverList.size();
			// Assign username to id in HashMap
			userList.put(username, id);
			// Add a element to serverList
			serverList.add("");
			return id;
		}
	}

	

	/* Default Talk method, Broadcast message to all users */
	public void talk(int id, String message) throws InterruptedException {
		synchronized (serverList) {
			for (int i = 0; i < serverList.size(); i++) {
				String val = serverList.get(i) + message;
				serverList.set(i, val);
			}

		}
	}

	/* Default Listne method, Listens to all users messages */
	public String listen(int id) throws InterruptedException {
		synchronized (serverList) {
			String temp = serverList.get(id);
			serverList.set(id, "");
			return temp;
		}
	}

	/* Broadcasts to all users that a user has left */
	public void leave(String username) throws InterruptedException {
			userList.remove(username);

	}

	/* Private message Method */
	public void privateMsg(int id, String username, String receiverUsername, String message)
			throws InterruptedException {
		synchronized (serverList) {
			try {
				
				// Takes receieverUsername and finds its value
				Integer i = userList.get(receiverUsername);
				
				// Construct the message to display as string
				String val = "[PM] " + username + serverList.get(i) + message;
				
				// Display to receiver
				serverList.set(i, val);

				// Takes senderUsername and finds its value
				Integer y = userList.get(username);
				
				// Construct the message to display as string
				String vax = "[YOU] " + receiverUsername + serverList.get(y) + message;
				
				// Display to sender
				serverList.set(y, vax);
				
			} catch (Exception e) {
				serverList.set(id, "Failed to send Private MSG");
			}

		}
	}
}
