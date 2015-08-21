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
		
		BasicServer server = null;
		DBManager sqlDatabase = new SQLDatabase();
		DBManager volatileDatabase = new VolatileDatabase();
		DBManager nullDatabase = new NullDatabase();
		
		Scanner scanner = new Scanner(System.in);
		while(true){
		    System.out.println("Which database would you like to use? (Enter 1 for SQLDatabase, "
		    		+ "2 for VolatileDatabase, 3 for NullDatabase)");
			String line = scanner.nextLine();

			if (line.equals("1")) {
				if (server == null) {
					server = new BasicServer(sqlDatabase);
				} else {
					server.setDatabase(sqlDatabase);
				}
				System.out.println("MySQL database is used.");
			} else if (line.equals("2")) {
				if (server == null) {
					server = new BasicServer(volatileDatabase);
				} else {
					server.setDatabase(volatileDatabase);
				}
				System.out.println("Volatile database is used.");
			} else if (line.equals("3")) {
				if (server == null) {
					server = new BasicServer(nullDatabase);
				} else {
					server.setDatabase(nullDatabase);
				}
				System.out.println("Null database is used.");
			} else {
				System.out.println("Wrong input!");
				continue;
			}
			System.out.println("Enter C/c to change database or Q/q to quit.");
			while (true) {
				line = scanner.nextLine();
				if (line.charAt(0) == 'C' || line.charAt(0) == 'c') {
					break;
				} else if (line.charAt(0) == 'Q' || line.charAt(0) == 'q') {
					scanner.close();
			    	for(int i = 0; i < serverList.size(); i++){
						serverList.get(i).setStop(true);
					}
			    	System.out.println("Bye!");
			    	System.exit(0);
			    }
		    }
		}
	}
}
