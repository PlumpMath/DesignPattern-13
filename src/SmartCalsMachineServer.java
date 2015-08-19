/* 
 * VendingMachineServer subclass, holding a singleton for MachineServer and is in charge of 
 * starting a server stub to directly communicate with each machine through socket
*/
import java.net.ServerSocket;
import java.net.Socket;

public class SmartCalsMachineServer extends VendingMachineServer {

	static MachineServer machineSingleton;

	static void setMachineInstance(MachineServer instance) {
		machineSingleton = instance;
	}

	public static MachineServer getMachineInstance() {
		return machineSingleton;
	}

	@Override
	public void run() {
		System.out.println("SmartCalsVendingMachine machine server is on!");
		try {
			ServerSocket ss = new ServerSocket(Const.MACHINE_PORT);
			while (true) {
				Socket sk = ss.accept();
				VMServerStub stub = new MachineServerStub(sk, getMachineInstance());
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
}