//start server with VolatileDatabase object
public class StartVolatileServer {
	public static void main(String[] args){
	    VendingMachineServer customer = new SmartCalsCustomerServer();
	    VendingMachineServer employee = new SmartCalsEmployeeServer();
	    VendingMachineServer machine = new SmartCalsMachineServer();
	    (new Thread(customer)).start();
	    (new Thread(employee)).start();
	    (new Thread(machine)).start();
	    new BasicServer(new VolatileDatabase());
	}
}
