//VendingMachineServer superclass
import java.net.ServerSocket;
import java.net.Socket;

abstract class VMServerThread implements Runnable {
	private ServerSocket mSocket = null;
	
	//stopServer() method to stop the thread
	public final void stopServer() {
		try{
			if(mSocket != null){
				mSocket.close();
				System.out.println("SmartCalsVendingMachine " + getName() + " is off!");
			} else {
				System.out.println("Error! SmartCalsVendingMachine " + getName() + " is not on!");
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	//template method
	public final void run() {
		System.out.println("SmartCalsVendingMachine " + getName() + " is on!");
		try {
			mSocket = new ServerSocket(getPort());
			while (true) {
				Socket sk = mSocket.accept();
				VMServerStub stub = getServerStub();
				stub.setSocket(sk);
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//abstract methods to be implemented in concrete classes
	abstract protected String getName();
	
	abstract protected int getPort();
	
	abstract protected VMServerStub getServerStub();
	
}