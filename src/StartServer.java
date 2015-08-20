//StartServer class containing main() method to start the server
import java.util.Scanner;

public class StartServer {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Which database would you like to use?\n1 for SQLDatabase, 2 for VolatileDatabase, 3 for NullDatabase");
	    int choice = scanner.nextInt();
	    scanner.close();
	    if (choice == 1){
	    	startServer();
	    	new BasicServer(new SQLDatabase());
	    } else if (choice == 2){
	    	startServer();
	    	new BasicServer(new VolatileDatabase());
	    } else if (choice == 3){
	    	startServer();
	    	new BasicServer(new NullDatabase());
	    } else {
	    	System.out.println("Wrong input!");
	    }
	    
	}
	
	public static void startServer(){
		VendingMachineServer customer = new SmartCalsCustomerServer();
	    VendingMachineServer employee = new SmartCalsEmployeeServer();
	    VendingMachineServer machine = new SmartCalsMachineServer();
	    (new Thread(customer)).start();
	    (new Thread(employee)).start();
	    (new Thread(machine)).start();
	}
}
