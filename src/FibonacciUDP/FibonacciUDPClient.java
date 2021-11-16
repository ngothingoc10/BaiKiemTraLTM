package FibonacciUDP;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class FibonacciUDPClient extends JFrame implements ActionListener{
	JTextArea txta, txtnhan;
	JButton send, reset, exit;
	JPanel pn, pn1, pn2, pn3;
	PrintWriter pw = null;
	BufferedReader br = null;
	Socket socket;

	public void GUI() {

		txta = new JTextArea(2, 20);
//		txta = new JTextArea(2, 5);
//		txtpheptinh = new JTextArea(2, 5);
//		
		txtnhan = new JTextArea(5, 20);
		txtnhan.setEditable(false);

		send = new JButton("Send");
		reset = new JButton("Reset");
		exit = new JButton("Exit");

		send.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);

		pn = new JPanel(new GridLayout(3, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn3 = new JPanel(new FlowLayout());
		pn1.add(txta);
//		pn1.add(txtb);
//		pn1.add(txtpheptinh);
		pn2.add(send);
		pn2.add(reset);
		pn2.add(exit);

		pn3.add(txtnhan);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);// add vao frame
		add(pn);
		setSize(400, 400);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == send) {
			try {
				socket = new Socket("localhost", 6997);
				DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
				while (true) {
					dOutputStream.writeUTF(txta.getText());
					dOutputStream.flush();
					// nhan ket qua tra ve tu server sau khi xu ly
					String result = dInputStream.readUTF();
					txtnhan.setText(result);
				}

			} catch (Exception e2) {
			}
		}
		if (e.getSource() == exit) {
			System.exit(0);
		}
		if (e.getSource() == reset) {
			txta.setText(" ");
			txtnhan.setText(" ");
		}

	}

	public FibonacciUDPClient(String st) {
		super(st);
		GUI();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FibonacciUDPClient("KT Fibonacci");

	}
}
