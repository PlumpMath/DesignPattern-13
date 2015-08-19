/* 
 * VendingMachineServer subclass, holding a singleton for CustomerServer and is in charge of 
 * starting a server stub to directly communicate with each customer through socket
*/
import java.net.ServerSocket;
import java.net.Socket;

public class SmartCalsCustomerServer extends VendingMachineServer {

	static CustomerServer customerSingleton;

	static void setCustomerInstance(CustomerServer instance) {
		customerSingleton = instance;
	}

	public static CustomerServer getCustomerInstance() {
		return customerSingleton;
	}

	@Override
	public void run() {
		System.out.println("SmartCalsVendingMachine customer server is on!");
		try {
			ServerSocket ss = new ServerSocket(Const.CUSTOMER_PORT);
			while (true) {
				Socket sk = ss.accept();
				VMServerStub stub = new CustomerServerStub(sk, getCustomerInstance());
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
}
