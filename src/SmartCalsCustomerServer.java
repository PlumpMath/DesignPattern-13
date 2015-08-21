/* 
 * VendingMachineServer subclass, holding a singleton for CustomerServer.
 * It directly communicates with each customer through socket and starts
 * a server stub thread whenever a request is received.
*/
public class SmartCalsCustomerServer extends VendingMachineServer {

	private static CustomerServer customerSingleton;

	public static void setCustomerInstance(CustomerServer instance) {
		customerSingleton = instance;
	}

	protected String getName(){
		return "customer server";
	}
	
	protected int getPort(){
		return Const.CUSTOMER_PORT;
	}
	
	protected VMServerStub getServerStub(){
		return new CustomerServerStub(customerSingleton);
	}
}
