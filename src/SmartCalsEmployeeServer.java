/* 
 * VendingMachineServer subclass, holding a singleton for EmployeeServer and is in charge of 
 * starting a server stub to directly communicate with each employee through socket
*/
import java.net.ServerSocket;
import java.net.Socket;

public class SmartCalsEmployeeServer extends VendingMachineServer {

	static EmployeeServer employeeSingleton;

	static void setEmployeeInstance(EmployeeServer instance) {
		employeeSingleton = instance;
	}

	public static EmployeeServer getEmployeeInstance() {
		return employeeSingleton;
	}

	@Override
	public void run() {
		System.out.println("SmartCalsVendingMachine employee server is on!");
		try {
			ServerSocket ss = new ServerSocket(Const.EMPLOYEE_PORT);
			while (true) {
				Socket sk = ss.accept();
				VMServerStub stub = new EmployeeServerStub(sk, getEmployeeInstance());
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
}