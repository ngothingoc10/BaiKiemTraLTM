package TinhTCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TinhTCPServer {
	private int port;

	

	public TinhTCPServer(int port) {
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
			dOutputStream.writeUTF(String.valueOf(TinhToan(sendedString)));
			dOutputStream.flush();
			socket.shutdownOutput();
		}
	}

	public float TinhToan(String str) {
		String tmp1 = "";
		String tmp2 = "";
		String tmp3 = "";
		float t = 0;
		
		

		String[] splited = str.trim().split(" ");

		tmp1 = splited[0];
		System.out.println(tmp1);
		tmp2 = splited[1];
		System.out.println(tmp2);
		tmp3 = splited[2];
		System.out.println(tmp3);

		float a = Float.parseFloat(tmp1);
		float b = Float.parseFloat(tmp3);
		if (tmp2.equals("+"))
			t= a+b;
		if (tmp2.equals("-"))
			t=  a-b;
		if (tmp2.equals("*"))
			t=  a*b;
		if (tmp2.equals("/"))
			t=  a/b;
		return t;

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TinhTCPServer server = new TinhTCPServer(6997);
		server.execute();
	}

}
