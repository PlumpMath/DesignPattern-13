/* 
 * VendingMachineServer subclass.
 * It directly communicates with each employee through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class SmartCalsEmployeeServer extends VendingMachineServer {

	private static EmployeeServer employeeServer;
	
	public SmartCalsEmployeeServer(EmployeeServer server){
		employeeServer = server;
	}
	
	protected String getName(){
		return "employee server";
	}
	
	protected int getPort(){
		return Const.EMPLOYEE_PORT;
	}
	
	protected VMServerStub getServerStub(){
		return new EmployeeServerStub(employeeServer);
	}
}