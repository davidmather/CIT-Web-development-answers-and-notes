
package javaProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.*;
public class Surgey extends JFrame implements ActionListener { 

	private int surgeryId ; 
	private String surgeryAdd; 
	private static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.

	public static String holder;
	public String password;

	public static void main(String[] args) { 
		new Surgey();
	}

	public Surgey() { 


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});

		JFrame frame = new JFrame();
		docList.add(new Doctor("rob", "sss"));
		docList.add(new Doctor("robert", "tyue"));
		docList.add(new Doctor("robert", "tyue"));
		//Doctor.addPatient(Patients);
		Doctor.addPatient();
		if (loginCheck(holder,password) == true){frame.setTitle("Welcome" + holder);
		}else{frame.setTitle("Welcome");}
		Color c = new Color(41, 128, 185);
		Container con = frame.getContentPane();
		con.setBackground(c);

		frame.setSize(700, 500);
		frame.setLocation(200, 200);

		//Menu for stuff
		JMenu menu = new JMenu("Doctor");
		menu.add(makeMenuItem("Login"));
		menu.add(makeMenuItem("Add Doctor"));
		menu.add(makeMenuItem("Save"));
		menu.add(makeMenuItem("Quit"));


		JMenu menu2 = new JMenu("Patent");
		menu2.add(makeMenuItem("Open"));
		menu2.add(makeMenuItem("Save"));
		menu2.add(makeMenuItem("Add"));
		menu2.add(makeMenuItem("Quit"));

		//Adding menu together.
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(menu2);


		JPanel westPanel = new JPanel(); 
		westPanel.setPreferredSize(new Dimension (200, 400) );
		westPanel.setBackground(new Color(231, 76, 60));
		westPanel.add(new JLabel("Doctors List"));


		for (int x = 0 ; x <=  docList.size()-1;x++){
			westPanel.add(new JLabel(docList.get(x).getDoctorName()));
		}	


		JPanel centerPanel = new JPanel(); 
		centerPanel.setPreferredSize(new Dimension (500, 400) );
		centerPanel.setBackground(new Color(52, 152, 219));

		JPanel southPanel = new JPanel(); 
		southPanel .setPreferredSize(new Dimension (500, 50) );
		southPanel .setBackground(new Color(211, 84, 0));

		frame.setLayout(new BorderLayout());
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.add( westPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);



	}


	private static void addDoctor() { 
		String name,password ;

		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.print("Please Enters Doctor's Name : ");
			name = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.


		do{// Start of Do while Loop
			System.out.print("Please Enter "+ name + "'s Password : ");
			password = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.
		docList.add(new Doctor(name, password));// Adds doctors to class
		saveSystemToFile() ;
	} 

	private static void updateDoctor() { 

	} 
	private static void saveSystemToFile() { 
		BufferedWriter bWriter = null;
		try{
			File file = new File("Database.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);
			// Goes through each lecturer and writes its details
			for(int i=0; i<docList.size(); i++){
				bWriter.write(docList.get(i).getDoctorName().toString());
				bWriter.write(";");
				bWriter.write(docList.get(i).getDocPasswd().toString());
				bWriter.write(";");
				bWriter.write(docList.get(i).getDoctorId());
				bWriter.newLine();

			}
			System.out.print("Saved");
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				// clears memory and closes writer
				if(bWriter != null){
					bWriter.flush();
					bWriter.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}    
	} 
	private static void restoreSystemFromFile() { 

	} 

	private static void login()
	{
		final 	JFrame frame1 = new 	JFrame();


		//Set Color of background




		frame1.setLayout(new BorderLayout());

		final JPanel center = new JPanel();
		final JPanel middle = new JPanel();
		middle.setBackground(new Color(52, 152, 219));
		middle.setPreferredSize(new Dimension (200, 150) );
		center.add(middle);

		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 


		final JLabel l = new JLabel();
		final JTextField txt=new JTextField();
		txt.setText("Doctor Name");
		final JPasswordField ps=new JPasswordField(10);
		JButton but=new JButton();
		but.setText("Submit");


		middle.add(l);
		middle.add(txt);
		middle.add(ps);
		middle.add(but);

		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String getTxt = txt.getText();
				String password = ps.getText();

				if(loginCheck(getTxt,password)== true){
					holder = txt.getText();
					password = ps.getText();
					listPatients();docList.add(new Doctor("robert", "tyue"));
					frame1.dispose();
				}
			}
		}
				);		
		frame1.add(center);
		frame1.setSize(300, 200);
		frame1.setTitle("Login");
		frame1.setVisible(true);
		frame1.setResizable(false);
	}





	public static boolean loginCheck(String name,String password) {

		System.out.print(name);


		int x =  docList.size();

		int i =0;
		for (i = 0 ; i <= x-1; i++){
			if (docList.get(i).getDoctorName().toString().equals(name) && (docList.get(i).getDocPasswd().toString().equals(password))){
				System.out.println("true");
				return true;
			}
		}
		System.out.println("wrong");
		return false;
	} 

	private static void listPatients(){

		int x =  docList.size();
		for (int i =0;i <= x-1;i++) // Start of For Loop
		{
			docList.get(i).print();// Gets Person Information
			System.out.print("\n----- patiets -----\n");
			
			
			for (Patient s : docList.get(i).getPatient()) {  
				System.out.print(s);  //Display Flims for actor
			}  
		}
	}







	// Labs 


	public void actionPerformed(ActionEvent e) {

		// Menu item actions
		String command = e.getActionCommand();

		if (command.equals("Quit")) {
			System.exit(0);
		} else if (command.equals("Login")) {
			// Open menu item action

			login();
		} else if (command.equals("Save")) {
			// Save menu item action
			System.out.println("Save menu item clicked");
		}
	}

	private JMenuItem makeMenuItem(String name) {
		JMenuItem m = new JMenuItem(name);
		m.addActionListener(this);
		return m;
	}
} 