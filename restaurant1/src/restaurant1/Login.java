package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


public class Login extends JFrame {
	
	private JTextField name1 = new JTextField("Name");
	private JTextField name = new JTextField("Your name");
	private JTextField surname1 = new JTextField("Surname");
	private JTextField surname = new JTextField("Your surname");
	private JTextField pole1 = new JTextField("Login");
	private JTextField email = new JTextField("example@gmail.com");
	private JTextComponent pole3 = new JTextField("Password");
	private JPasswordField pass = new JPasswordField("Your pass");
	private JTextComponent pole4 = new JTextField("Repeat password");
	private JPasswordField pass2 = new JPasswordField("Your pass");
	private JButton registrate = new JButton("Sign up");
	ResultSet result, resultpass;
	
	
	public Login(){
		
		setTitle("Sign up");
		setSize(320, 370);
		setLayout(null);
		//getContentPane().setLayout((LayoutManager) new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		
		name1.setEditable(false);
		name1.setLocation(10, 20);
		name1.setSize(120, 40);
		add(name1);
		
		name.setLocation(150, 20);
		name.setSize(120, 40);
		add(name);
		
		surname1.setEditable(false);
		surname1.setLocation(10, 70);
		surname1.setSize(120, 40);
		add(surname1);
		
		surname.setLocation(150, 70);
		surname.setSize(120, 40);
		add(surname);

		pole1.setEditable(false);
		pole1.setLocation(10, 120);
		pole1.setSize(120, 40);
		add(pole1);
		
		email.setLocation(150, 120);
		email.setSize(120, 40);
		add(email);
		
		pole3.setEditable(false);
		pole3.setLocation(10, 170);
		pole3.setSize(120, 40);
		add(pole3);
		
		pass.setLocation(150, 170);
		pass.setSize(120, 40);
		add(pass);
		
		pole4.setEditable(false);
		pole4.setLocation(10, 220);
		pole4.setSize(120, 40);
		add(pole4);
		
		pass2.setLocation(150, 220);
		pass2.setSize(120, 40);
		add(pass2);
		
		registrate.setLocation(100, 270);
		registrate.setSize(120, 40);
		add(registrate);
		
		registrate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					//try {
						//Update update =
						result = Registrate.statement.executeQuery("Select idRegistration FROM registration where email =\'" + email.getText()+"\'");
						resultpass = Registrate.statement.executeQuery("Select idRegistration FROM registration where pass=\'" + pass.getText() +"\'");
						if (result.equals(null) && resultpass.equals(null)) {
							Registrate.statement.executeUpdate("INSERT INTO registration VALUES( null, \'" + email.getText() + "\', \'" + pass.getText() + "\', \'" + name.getText() + "\', \'" + surname.getText() + "\', 0)");
							setVisible(false);
							Enter ent = new Enter();
							ent.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Email or password exists!");
								}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
			}
		});
		
	}

	

}
