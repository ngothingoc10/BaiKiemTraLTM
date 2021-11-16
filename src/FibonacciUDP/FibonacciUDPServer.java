package FibonacciUDP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FibonacciUDPServer {
private int port;

	

	public FibonacciUDPServer(int port) {
		this.port = port;
	}

	private void execute() throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(this.port);
		System.out.println("Server is waiting for Client");

		while (true) {
			Socket socket = serverSocket.accept();
			DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
			String sendedString = dInputStream.readUTF();
			System.out.println(sendedString);
			System.out.println("Processing");
			
			System.out.println();
			String mess ="";
			if(isFibobacci(Integer.parseInt(sendedString))) {
				 mess = "Số thuộc dãy Fibonacci.Ngừng nhập!! ";
				
			}
			else {
				 mess = "Tiếp tục nhập!! ";
			}
			dOutputStream.writeUTF(mess);
			dOutputStream.flush();
			socket.shutdownOutput();
		}
	}
	
	int Fibobacci(int n)
	{   if(n==0 || n==1) return 1;
	    else return Fibobacci(n-2) +Fibobacci(n-1);
	}
	boolean isFibobacci(int m)
	{ 
		
		for(int i = 0; i<= m; i++) {
			if(Fibobacci(i) == m) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FibonacciUDPServer server = new FibonacciUDPServer(6997);
		server.execute();
	}
}
