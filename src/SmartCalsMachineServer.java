/* 
 * VendingMachineServer subclass, holding a singleton for MachineServer.
 * It directly communicates with each machine through socket and starts
 * a server stub thread whenever a request is received.
*/
import java.net.Socket;

public class SmartCalsMachineServer extends VendingMachineServer {

	private static MachineServer machineSingleton;

	public static void setMachineInstance(MachineServer instance) {
		machineSingleton = instance;
	}

	public static MachineServer getMachineInstance() {
		return machineSingleton;
	}

	protected String getName(){
		return "machine server";
	}
	
	protected int getPort(){
		return Const.MACHINE_PORT;
	}
	
	protected VMServerStub getServerStub(Socket sk){
		return new MachineServerStub(sk, getMachineInstance());
	}
}