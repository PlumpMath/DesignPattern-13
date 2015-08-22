//VendingMachineServer superclass
import java.net.ServerSocket;
import java.net.Socket;

abstract class VendingMachineServer implements Runnable {
	private boolean stopServer = false;
	
	//setStop() method to stop the thread
	public final void setStop(boolean stop) {
		stopServer = stop;
		if(stop){
			System.out.println("SmartCalsVendingMachine " + getName() + " is off!");
		}
	}
	
	//template method
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
			ss.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	//abstract methods to be implemented in concrete classes
	abstract protected String getName();
	
	abstract protected int getPort();
	
	abstract protected VMServerStub getServerStub();
	
}