//start server with VolatileDatabase object
public class StartVolatileServer {
	public static void main(String[] args){
	    VendingMachineServer market = new VendingMachineServer();
	    (new Thread(market)).start();
	    new BasicServer(new VolatileDatabase());
	}
}
