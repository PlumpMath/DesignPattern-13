//Employee server stub subclass, which handles all employee related commands
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EmployeeServerStub extends VMServerStub {
	Socket socket;
	EmployeeServer server;

	EmployeeServerStub(Socket sk, EmployeeServer e) {
		socket = sk;
		server = e;
	}

	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			String response;
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				String request = new String(buffer, 0, len);

				System.out.println(">" + request);
				String[] tokens = request.split(" ");
				String command = tokens[0];

				try {
					//employee commands
					if (command.equals(Const.AUTHENTICATE)) {
						String result = server.authenticate(
								Integer.parseInt(tokens[1]),		// employeeid
								tokens[2]); 						// password
						if (result == null) {
							response = Const.ERROR
									+ "Wrong employee or password";
						} else {
							response = Const.OK + result;
						}
					} else if (command.equals(Const.CHECK_MACHINE)) {
						int result = server.checkMachine(Integer.parseInt(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.GET_ITEM_IDS)) {
						String result = server.getItemIDs();
						response = Const.OK + result;
					} else if (command.equals(Const.GET_OTHER_ITEM_IDS)) {
						String result = server.getOtherItemIDs(Integer.parseInt(tokens[1]));		//machineid
						response = Const.OK + result;
					} else if (command.equals(Const.GET_ITEM)) {
						String result = server.getItem(Integer.parseInt(tokens[1])); //itemid
						response = Const.OK + result;
					} else if (command.equals(Const.ADD_MACHINE)) {
						int result = server.addMachine(Integer.parseInt(tokens[1]), tokens[2]); //code, address
						response = Const.OK + result;
					} else if (command.equals(Const.ADD_ITEM_TO_MACHINE)) {
						server.addItemToMachine(
								Integer.parseInt(tokens[1]),	// machineid
								Integer.parseInt(tokens[2]),	// itemid
								Integer.parseInt(tokens[3]),	// capacity
								Integer.parseInt(tokens[4]));	// quantity
						response = Const.OK;
					} else if (command.equals(Const.GET_FILE)) {
						String result = server.getFile(tokens[1]);		//path
						response = Const.OK + result;
					} else if (command
							.equals(Const.UPDATE_MACHINE_ITEM_QUANTITY)) {
						server.updateMachineItemQuantity(
								Integer.parseInt(tokens[1]),	// machineid
								Integer.parseInt(tokens[2]),	// itemid
								Integer.parseInt(tokens[3]));	// quantity
						response = Const.OK;
					} else if (command
							.equals(Const.DELETE_ITEM_FROM_MACHINE)) {
						server.deleteItemFromMachine(
								Integer.parseInt(tokens[1]),	// machineid
								Integer.parseInt(tokens[2]));	// itemid
						response = Const.OK;
					} else {
						response = Const.ERROR + "Unknown command";
					}
				} catch (Exception e) {
					response = Const.ERROR + e.getMessage();
				}
				System.out.println("<" + response);
				out.write((response).getBytes());
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}