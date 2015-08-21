//VendingMachineServer superclass
import java.net.ServerSocket;
import java.net.Socket;

abstract class VendingMachineServer implements Runnable {
	private boolean stopServer = false;
	
	public void setStop(boolean stop){
		stopServer = stop;
	}
	
	public final void run() {
		System.out.println("SmartCalsVendingMachine " + getName() + " is on!");
		try {
			ServerSocket ss = new ServerSocket(getPort());
			while (!stopServer) {
				Socket sk = ss.accept();
				VMServerStub stub = getServerStub();
				stub.setSocket(sk);
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	abstract protected String getName();
	
	abstract protected int getPort();
	
	abstract protected VMServerStub getServerStub();
	
}