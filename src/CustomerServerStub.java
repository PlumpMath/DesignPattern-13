//Customer server stub subclass, which handles all customer related commands
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CustomerServerStub extends VMServerStub {
	Socket socket;
	CustomerServer server;

	CustomerServerStub(Socket sk, CustomerServer c) {
		socket = sk;
		server = c;
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
					//customer commands
					if (command.equals(Const.BUY_CARD)) {
						int result = server.buyCard(Double.parseDouble(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.CHECK_BALANCE)) {
						double result = server.checkBalance(Integer.parseInt(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.UPDATE_BALANCE)) {
						double result = server.updateBalance(Integer.parseInt(tokens[1]), 	//cardid
								Double.parseDouble(tokens[2]));									//deduct
						if (result < 0) {
							response = Const.ERROR;
						} else {
							response = Const.OK + result;
						}
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
