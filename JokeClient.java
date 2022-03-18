/*--------------------------------------------------------

1. Huseyn Mammadov / 06.26.2021:

2. Java version used (java -version), if not the official version for the class:

e.g. build 1.9.0_06-b05

3. Precise command-line compilation examples / instructions:

e.g.:

> javac JokeServer.java


4. Precise examples / instructions to run this program:

e.g.:

In separate shell windows:

> java JokeServer
> java JokeClient
> java JokeClientAdmin


5. List of files needed for running the program.

e.g.:


 JokeServer.java
 JokeClient.java
 JokeClientAdmin.java

5. Notes:



----------------------------------------------------------*/
import java.io.*; 
import java.net.*;

public class JokeClient { 
	public static void main (String args[]) throws IOException {
	
		String ServerCalled; 		
		if (args.length < 1) ServerCalled = "localhost"; 
		else ServerCalled = args[0];
		
		System.out.println("Huseyn Mammadov's Joke client \n");
		System.out.println("Server in the progress "+ServerCalled+", port number: 4546");  
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
		System.out.println("Type your nickname or name"); 
		System.out.flush(); 
		String unique;  
		unique=in.readLine();
		
		try{
			String temp;
		
			do{ 
			System.out.println("Press Enter or type quit for exit");
			System.out.flush();
			temp=in.readLine();
			if(!(temp.equals("quit")))
			forprogress(unique,ServerCalled);
			}while (!(temp.equals("quit")));
			System.out.println("Cancelled by user request.");
		}catch(IOException x) {x.printStackTrace();}
	}

	
	static void forprogress( String unique, String ServerCalled) {
		Socket mainSock;		
		BufferedReader ServerAlong;	
		PrintStream tillServer;
		String dataServer;
		

		try{ 			
			mainSock = new Socket(ServerCalled, 4545);
            ServerAlong =
                new BufferedReader(new InputStreamReader(mainSock.getInputStream()));
				tillServer = new PrintStream(mainSock.getOutputStream());
				tillServer.println(unique); tillServer.flush();  
            for (int i = 1; i <=4; i++){ 
                dataServer = ServerAlong.readLine(); 
                if (dataServer != null) System.out.println(dataServer);
            }
			
			mainSock.close();
			}catch(IOException x){
			System.out.println("Socket error");
			x.printStackTrace();
		}
	}
}