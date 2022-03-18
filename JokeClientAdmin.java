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
public class JokeAdminClient {

	public static void main(String[] args) throws IOException {
	
		String unique2;
		if (args.length<1) unique2="localhost";
		else unique2=args[0];
		System.out.println("Huseyn's Admin Client \n");
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		try{
			String newtemp; 
			do{
				
				System.out.println("Type your mode: 1 for proverb, 2 for joke, or quit");
				System.out.flush();
				newtemp=in.readLine();
				if (newtemp.indexOf("quit")<0) 
				switchs(unique2,newtemp);
			}while (newtemp.indexOf("quit")<0);
			System.out.println("Cancelled by user request");
		} catch (IOException x) {x.printStackTrace();}
	}
	
	public static void switchs(String unique2, String newtemp) {
		Socket mainSock;
		BufferedReader ServerAlong;
		PrintStream tillServer;
		String dataServer;
		try{
			mainSock =new Socket(unique2,4546);
			ServerAlong=new BufferedReader(new InputStreamReader(mainSock.getInputStream()));
			tillServer=new PrintStream(mainSock.getOutputStream());
			tillServer.println(newtemp);
				tillServer.flush();
				dataServer=ServerAlong.readLine();
			if(dataServer !=null) System.out.println(dataServer);
		
			mainSock.close();
		}catch (IOException x) {
			System.out.println("socket error");
			x.printStackTrace();
		}
	}
}