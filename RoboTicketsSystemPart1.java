import java.awt.Font;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/** RoboTicketsSystem.java
	An 8-bit website that sells tickets for sports, theater, events and concerts.
	@author		Marquis Lockhart
	@version	1.0
	@date		4/22/2018
*/


public class RoboTicketsSystem extends Application {
	/**
	 * 
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Button exitBut = new Button ("Exit");
		exitBut.setLayoutX(200);
		exitBut.setLayoutY(150);
		Button Enter = new Button("Enter");
		Enter.setLayoutX(50);
		Enter.setLayoutY(150);
		Text t = new Text(80, 50, "Welcome to RoboTickets!");
		Enter.setOnAction(e->{
			arg0.close();
			doMenu();
		});
		exitBut.setOnAction(e->{
			endGracefully();
			t.setText("Thank you for using RoboTickets. Goodbye!");
			System.exit(0);
		});
		Pane p = new Pane();
		Scene sc1 = new Scene(p,300,300);
		p.getChildren().addAll(exitBut,Enter, t);
		arg0.setScene(sc1);
		arg0.show();

	}
	public void saveToFile()
	{
		ObjectOutputStream objectout= null;
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream("User.ser");
		    objectout = new ObjectOutputStream(fout);
			for(User u :users)
			{
				objectout.writeObject(u);
			}
		} catch ( IOException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try {
				fout.close();
				objectout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void readFromFile()
	{
		ObjectInputStream obIn = null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("user.ser");
			obIn=new ObjectInputStream(fin);
			for(;;){
			User temp = (User)obIn.readObject();
			users.add(temp);
			}
			
		}
		catch(EOFException e)
		{
			return;
		}
		catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			saveToFile();
			doMenu();
		}
		
	}
	private void endGracefully() {
		// TODO Auto-generated method stub
		saveToFile();
		System.out.println("Thank you for using RoboTickets. Goodbye!");
		System.exit(0);
	}
	ArrayList<User> users = new ArrayList<User>();
	String login, name;
	User current;
	public void createUser()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your login name:");
		login = sc.nextLine();
		System.out.println("Please enter your full name:");
		name = sc.nextLine();
		User tmp = new User(login, name);
		users.add(tmp);
		doMenu();
	}
	public static void  main (String args[])
	{
		System.out.println("Hello, Welcome to RoboTickets!"
				+ " My name is Marquis Lockhart.");
		launch(args);
	}
	public void doMenu()
	{
		readFromFile();
		if(users.isEmpty()){
			createUser();
		}
		int choice;
		System.out.println("RoboTickets.com System\n\n Menu");
		System.out.println("t1. Log In\n");
		System.out.println("t2. Enter Users\n");
		System.out.println("t3. List Users\n");
		System.out.println("t4. Change User Data\n");
		System.out.println("t5. Close Account\n");
		System.out.println("t6. Buy Tickets\n");
		System.out.println("t7. Quit\n");

		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		switch (choice)
		{
		case 1: 
			doLogin();
			break;
		case 2:
			createUser();
			break;
		case 3:
			listUser();
			break;
		case 4:
			updateUser();
			break;
		case 5:
			closeAccount();
		case 6:
			buyTickets();
		case 7:
			endGracefully();
			break;
		}

	}
	private void listUser() {
		for(User Summary: users)
		{
			System.out.println(Summary);
		}
		doMenu();
	}
	private void doLogin(){
		System.out.println("Please enter a login name:");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		boolean temp = false;
		for(User u : users){
			if(u.getLogin().equals(name))
			{
				System.out.println("User login successful.");
				current=u;
				temp=true;
			}
		}
		if(!temp)
		{
			System.out.println("User login failed. Please try again.");
		}
		doMenu();
	}
	private void updateUser(){
		System.out.println("This feature is currently unavailable. "
				+ "Please check back later.");
		doMenu();
	}
	private void closeAccount(){
		System.out.println("This feature is currently unavailable. "
				+ "Please check back later.");
		doMenu();
	}
	private void buyTickets(){
		System.out.println("This feature is currently unavailable. "
				+ "Please check back later.");
		doMenu();
	}
}
