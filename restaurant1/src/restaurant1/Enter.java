package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


public class Enter extends JFrame {
	
	private JTextField pole1 = new JTextField("Login");
	private JTextField email = new JTextField("test");
	private JTextComponent pole3 = new JTextField("Password");
	private JPasswordField pass = new JPasswordField("test");
	private JButton registrate = new JButton("Sign up");
	private JButton enter = new JButton("Enter");
	private ResultSet result, result2;
	static int idUser;
	//JPanel buttonPanel = new JPanel();
	
	public Enter(){
		
		setTitle("Lon in");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(310, 210);
		setLayout(null);
		setVisible(true);
		
		//Registrate reg = new Registrate();
		//setTitle("sign up");
		//setSize(800, 600);
		//reg.setLayout(null);	
		//reg.setVisible(true);

		pole1.setEditable(false);
		pole1.setLocation(10, 10);
		pole1.setSize(120, 40);
		add(pole1);
		
		email.setLocation(150, 10);
		email.setSize(120, 40);
		add(email);
		
		pole3.setEditable(false);
		pole3.setLocation(10, 60);
		pole3.setSize(120, 40);
		add(pole3);
		
		pass.setLocation(150, 60);
		pass.setSize(120, 40);
		add(pass);
		
		registrate.setLocation(10, 110);
		registrate.setSize(120, 40);
		add(registrate);
		
		enter.setLocation(150, 110);
		enter.setSize(120,40);
		add(enter);
		
		
		
		registrate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false); 
				Login log = new Login();
				log.setVisible(true);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					result2 = Registrate.statement.executeQuery("Select idRegistration FROM registration where email =\'" + email.getText() + "\' AND pass=\'" + pass.getText() +"\'");
					result2.next();
					idUser = result2.getInt("idRegistration");
					//Update update =
					result = Registrate.statement.executeQuery("Select idRegistration, status FROM registration where email =\'" + email.getText() + "\' AND pass=\'" + pass.getText() +"\'");
					
					result.next();
						int status = result.getInt("status");
				if(status == 1){
						new MenuAdmin(idUser).setVisible(true);
						return;
					}else if(status==2){
						new MenuDevelopmentManager(idUser).setVisible(true);
						return;
					}else if(status==3){
						new ManuStaffManager(idUser).setVisible(true);
						return;
					}else if(status==4){
						new ManuKitchenManager(idUser).setVisible(true);
						return;
					}else{
						
						JOptionPane.showMessageDialog(null, "Ñontact the admin to change your status!" + result);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		//setVisible(true);
		
	}
	
}
