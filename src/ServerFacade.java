import java.util.ArrayList;

public class ServerFacade {
	
	public final static int SQL_DATABASE = 1;
	public final static int VOLATILE_DATABASE = 2;
	public final static int NULL_DATABASE = 3;
	
	ArrayList<VendingMachineServer> serverList;

	public ServerFacade(){
		serverList = new ArrayList<>();
	}
	
	public void startSmartCalsServers(){
		CentralServer server = CentralServer.instance();
		serverList.add(new SmartCalsCustomerServer(server));
		serverList.add(new SmartCalsEmployeeServer(server));
		serverList.add(new SmartCalsMachineServer(server));
		for(int i = 0; i < serverList.size(); i++){
			(new Thread(serverList.get(i))).start();
		}
		server.setDatabase(new SQLDatabase());
		System.out.println("MySQL database is used.");
	}
	
	public void switchDatabase(int choice){
		CentralServer server = CentralServer.instance();
		switch (choice) {
		case SQL_DATABASE:
			server.setDatabase(new SQLDatabase());
			System.out.println("MySQL database is used.");
			break;
		case VOLATILE_DATABASE:
			server.setDatabase(new VolatileDatabase());
			System.out.println("Volatile database is used.");
			break;
		case NULL_DATABASE:
			server.setDatabase(new NullDatabase());
			System.out.println("Null database is used.");
			break;
		default:
			System.out.println("Failed! No database matches.");
			break;
		}
	}
	
	public void stopSmartCalsServers(){
		for(int i = 0; i < serverList.size(); i++){
			serverList.get(i).setStop(true);
		}
	}
}
