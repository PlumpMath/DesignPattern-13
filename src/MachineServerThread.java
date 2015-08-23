/* 
 * VendingMachineServer subclass.
 * It directly communicates with each machine through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class MachineServerThread extends VMServerThread {

	private static MachineServer machineServer;
	
	public MachineServerThread(MachineServer server){
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