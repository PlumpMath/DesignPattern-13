/* 
 * VendingMachineServer subclass.
 * It directly communicates with each machine through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class SmartCalsMachineServer extends VendingMachineServer {

	private static MachineServer machineServer;
	
	public SmartCalsMachineServer(MachineServer server){
		machineServer = server;
	}

	protected String getName(){
		return "machine server";
	}
	
	protected int getPort(){
		return Const.MACHINE_PORT;
	}
	
	protected VMServerStub getServerStub(){
		return new MachineServerStub(machineServer);
	}
}