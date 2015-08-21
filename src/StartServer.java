//StartServer class containing main() method to start the server
import java.util.ArrayList;
import java.util.Scanner;

public class StartServer {
	
	public static void main(String[] args){
		ArrayList<VendingMachineServer> serverList = new ArrayList<>();
		serverList.add(new SmartCalsCustomerServer());
		serverList.add(new SmartCalsEmployeeServer());
		serverList.add(new SmartCalsMachineServer());
		for(int i = 0; i < serverList.size(); i++){
			(new Thread(serverList.get(i))).start();
		}
		
		Scanner scanner = new Scanner(System.in);
		while(true){
		    System.out.println("Which database would you like to use? (Enter 1 for SQLDatabase, "
		    		+ "2 for VolatileDatabase, 3 for NullDatabase, and 4 to quit)");
		    String line = scanner.nextLine();
		    try{
		    	int choice = Integer.parseInt(line);
		    	if (choice == 1){
			    	new BasicServer(new SQLDatabase());
			    } else if (choice == 2){
			    	new BasicServer(new VolatileDatabase());
			    } else if (choice == 3){
			    	new BasicServer(new NullDatabase());
			    } else if (choice == 4){
			    	scanner.close();
			    	for(int i = 0; i < serverList.size(); i++){
						serverList.get(i).setStop(true);
					}
			    	System.out.println("Buy!");
			    	System.exit(0);
			    } else {
			    	System.out.println("Wrong input!");
			    }
		    } catch (NumberFormatException e){
		    	System.out.println("Wrong input!");
		    }
		}
	}
}
