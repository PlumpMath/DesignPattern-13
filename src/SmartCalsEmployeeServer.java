/* 
 * VendingMachineServer subclass, holding a singleton for EmployeeServer.
 * It directly communicates with each employee through socket and starts
 * a server stub thread whenever a request is received.
*/
import java.net.Socket;

public class SmartCalsEmployeeServer extends VendingMachineServer {

	private static EmployeeServer employeeSingleton;

	public static void setEmployeeInstance(EmployeeServer instance) {
		employeeSingleton = instance;
	}

	public static EmployeeServer getEmployeeInstance() {
		return employeeSingleton;
	}

	protected String getName(){
		return "employee server";
	}
	
	protected int getPort(){
		return Const.EMPLOYEE_PORT;
	}
	
	protected VMServerStub getServerStub(Socket sk){
		return new EmployeeServerStub(sk, getEmployeeInstance());
	}
}