/* 
 * VendingMachineServer subclass.
 * It directly communicates with each employee through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class EmployeeServerThread extends VMServerThread {

	private static EmployeeServer employeeServer;
	
	public EmployeeServerThread(EmployeeServer server){
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