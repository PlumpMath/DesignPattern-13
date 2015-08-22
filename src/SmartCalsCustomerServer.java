/* 
 * VendingMachineServer subclass.
 * It directly communicates with each customer through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class SmartCalsCustomerServer extends VendingMachineServer {

	private static CustomerServer customerServer;
	
	public SmartCalsCustomerServer(CustomerServer server){
		customerServer = server;
	}

	protected String getName(){
		return "customer server";
	}
	
	protected int getPort(){
		return Const.CUSTOMER_PORT;
	}
	
	protected VMServerStub getServerStub(){
		return new CustomerServerStub(customerServer);
	}
}
