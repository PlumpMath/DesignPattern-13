//Customer server stub subclass, which handles all customer related commands
import java.net.Socket;

public class CustomerServerStub extends VMServerStub {
	CustomerServer server;

	CustomerServerStub(Socket sk, CustomerServer c) {
		super(sk);
		server = c;
	}

	protected String handleRequest(String request){
		String response;
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
		
		return response;
	}
}
