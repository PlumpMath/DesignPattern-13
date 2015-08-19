//Machine server stub subclass, which handles all machine related commands
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MachineServerStub extends VMServerStub {
	Socket socket;
	MachineServer server;

	MachineServerStub(Socket sk, MachineServer m) {
		socket = sk;
		server = m;
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
					//machine commands
					if (command.equals(Const.CHECK_MACHINE)) {
						int result = server.checkMachine(Integer.parseInt(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.GET_UPDATED_IDS)) {
						String result = server.getUpdatedIDs(Integer
								.parseInt(tokens[1])); // machineid
						response = Const.OK + result;
					} else if (command.equals(Const.GET_ITEM)) {
						String result = server.getItem(Integer.parseInt(tokens[1])); //itemid
						response = Const.OK + result;
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
					} else if (command.equals(Const.ADD_SALE)) {
						server.addSale(Integer.parseInt(tokens[1]),		// machineid
								Integer.parseInt(tokens[2]),			// itemid
								Double.parseDouble(tokens[3]),			// profit
								tokens[4]);								// date
						response = Const.OK;
					} else if (command.equals(Const.UPDATE_SYNC_DATE)) {
						server.updateSyncDate(Integer.parseInt(tokens[1]));		// machineid
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