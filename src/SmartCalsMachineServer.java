/* 
 * VendingMachineServer subclass, holding a singleton for MachineServer.
 * It directly communicates with each machine through socket and starts
 * a server stub thread whenever a request is received.
*/
public class SmartCalsMachineServer extends VendingMachineServer {

	private static MachineServer machineSingleton;

	public static void setMachineInstance(MachineServer instance) {
		machineSingleton = instance;
	}

	protected String getName(){
		return "machine server";
	}
	
	protected int getPort(){
		return Const.MACHINE_PORT;
	}
	
	protected VMServerStub getServerStub(){
		return new MachineServerStub(machineSingleton);
	}
}