package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ManuStaffManager extends JFrame {
	
	private JButton employee = new JButton("Employee records");
	private JButton iform = new JButton("Infomation about actions");
	private JButton back = new JButton("Back");
	private JButton requestsAndReports = new JButton("Requests and reports");
	
	
	public ManuStaffManager(int idUser){
				
		setTitle("Manu");
		setSize(330, 270);
		setLayout(null);	
		setVisible(true);
		
		employee.setLocation(75, 20);
		employee.setSize(180, 40);
		add(employee);

		iform.setLocation(75, 70);
		iform.setSize(180, 40);
		add(iform);
		
		requestsAndReports.setLocation(75, 120);
		requestsAndReports.setSize(180, 40);
		add(requestsAndReports);		
		
		back.setLocation(75, 170);
		back.setSize(180, 40);
		add(back);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new Enter().setVisible(true);
				return;
				
			}
		});
		

		employee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					setVisible(false);
					new Employee(idUser).setVisible(true);
					return;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

	

}