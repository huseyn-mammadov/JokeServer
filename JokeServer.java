/*--------------------------------------------------------

1. Huseyn Mammadov / 06.26.2021:

2. Java version used (java -version), if not the official version for the class:

e.g. build 1.9.0_06-b05

3. Precise command-line compilation examples / instructions:

e.g.:

> java JokeServer.java      //macbook termianl


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
 okeClientAdmin.java

5. Notes:



----------------------------------------------------------*/
import java.io.*;  
import java.net.*; 
import java.util.*;
import java.lang.Math; 
import java.util.ArrayList; 
import java.util.Random; 

public class JokeServer {  
	
	public static boolean forboolean=false;  
	public static String newtemp="listofAllproverb";
	public static boolean valuable = true;
	public static void main(String[] args) throws IOException {
	
		int q_len=6;
		int port =4545;
		Socket mainSock;	
		
		AdminLooper AL= new AdminLooper();	
		Thread t=new Thread(AL);
		t.start();	
		
		
		ServerSocket serversock =new ServerSocket(port, q_len);
		
		System.out.println("Huseyn's Joke Server starting up, listening for client port at "+port+"\n"); 
		while(true){
			mainSock=serversock.accept();	
			if(!forboolean)   
				new Worker(mainSock).start();	
			}
		
		}
	}

class AdminLooper implements Runnable{
	public static boolean manage=false;
	public void run(){ 
		System.out.println("Hi it is related with port 4546");
		int q_len=6;
		int port=4546;
		Socket mainSock;
		try{
			ServerSocket servsock= new ServerSocket(port,q_len);
			while(!manage){
			
				mainSock=servsock.accept();
				new ModeWorker(mainSock).start();
			}servsock.close();
		}catch(IOException ioe) {System.out.println(ioe);}
	}
}
class Worker extends Thread {
	Socket mainSock;
	private static String nick;
	Worker (Socket s) {mainSock =s;}
	

	private static ArrayList<String> modejoke = new ArrayList<String>();
	private static ArrayList<String> joketransfer = new ArrayList<String>();
	private static ArrayList<String> modeproverbs = new ArrayList<String>();
	private static ArrayList<String> proverbstransfer = new ArrayList<String>();
	
	
	

	public void run(){/// 
		try{
			PrintStream out = null;
			BufferedReader in = null;
			
			in=new BufferedReader (new InputStreamReader(mainSock.getInputStream()));
			out=new PrintStream(mainSock.getOutputStream());
			
			try {
				String insertingnick=in.readLine();
				
				toproverb(insertingnick, out);
							
			}catch (IOException x){
				System.out.println("Socker is down sorry");
				x.printStackTrace();
			}
			mainSock.close();
		}catch(IOException ioe) {System.out.println(ioe);}
	}
	

	static void toproverb(String insertingnick, PrintStream out) {
		nick="";
		nick= insertingnick;
		
		if (JokeServer.newtemp.equals(("listofAllproverb"))) {
				
			String timetoproverb = randomproverbs(); 
			out.println(timetoproverb);
				

	}
		else if (JokeServer.newtemp.equals(("listofAlljoke"))){

				String timetojoke = randomjokes();
				out.println(timetojoke);
		}
		

		
		 

	}




	static String randomjokes(){
	
		modejoke.clear();
		
		modejoke.add("JA "+nick+", I hate Russian dolls… they are so full of themselves!");
		modejoke.add("JB "+nick+", Talk is cheap? Have you ever talked to a lawyer?");
		modejoke.add("JC "+nick+", Why did the gym close down? It just didn't work out!");
		modejoke.add("JD "+nick+", Two artists had an art contest. It ended in a draw!.");
		
		
		//Collections.shuffle(modejoke);
	
		
	
	
	int rand = (int)(Math.random() * modejoke.size());
	String listofAlljoke = modejoke.get(rand);
	joketransfer.add(listofAlljoke);
			
		
		
		if (joketransfer.size()==4) {  
			System.out.println(modejoke);
			Collections.shuffle(joketransfer);
			

		}

		return listofAlljoke;
	}


	static String randomproverbs(){
		modeproverbs.clear();
		
		modeproverbs.add("PA " +nick+ " A stitch in time saves nine..");   
		modeproverbs.add("PB "+nick+"  A throne is only a bench covered in purple velvet.");
		modeproverbs.add("PC "+nick+" A wise man shall hold his tongue till he sees his opportunity.");
		modeproverbs.add("PD "+nick+" Fools rush in where Angels fear to tread.");
		
		
		
		int rand = (int)(Math.random() * modeproverbs.size());  
		String listofAllproverb = modeproverbs.get(rand);
		proverbstransfer.add(listofAllproverb);
		
		if (proverbstransfer.size()==4) {
			System.out.println(modeproverbs);
			Collections.shuffle(proverbstransfer);
	
		}

		return listofAllproverb;
	}
}




class ModeWorker extends Thread{
	Socket mainSock;

	ModeWorker(Socket s) {mainSock =s;}
	
	public void run(){
		
 
		try{
			PrintStream exit =null;
			BufferedReader enter =null;
			enter =new BufferedReader(new InputStreamReader(mainSock.getInputStream()));
			exit=new PrintStream(mainSock.getOutputStream());
			
			if(JokeServer.valuable != true ) {
				System.out.println("Request is completed");
				exit.println("See you Next time");
			}
			else try {
				//int value = Integer.parseInt(reader.readLine());
				String value;
				value =enter.readLine();

				if(value.equals("1")){
					JokeServer.newtemp="listofAllproverb"; 
					System.out.println("Mode switched to Proverb");
					exit.println("Mode switched to Proverb");
										

				}
				if(value.equals("2")){
					JokeServer.newtemp="listofAlljoke";
					System.out.println("Mode switched to Joke");
					exit.println("Mode switched to Joke");
										
				}
				if(value.equals("quit")){  
					JokeServer.newtemp="quit";
					System.out.println("Mode turn off, See you next time");
					exit.println("Mode turn off, See you next time");}
				else{
					exit.println("Sorry you just type wrong statement");
				}
			}catch(IOException x){
				System.out.println("Server is down");
				x.printStackTrace();
			}
			mainSock.close(); /// turn off server with all data
		}catch (IOException ioe){System.out.println(ioe);}
	}
}