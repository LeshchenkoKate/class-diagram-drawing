package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuAdmin extends JFrame {
	
	private JButton employee = new JButton("Employee records");
	private JButton orders = new JButton("Order accounting");
	private JButton dishes = new JButton("List of dishes");
	private JButton products = new JButton("Product accounting");
	private JButton dishesProducts = new JButton("Accounting of products spent for dishes");
	private JButton iform = new JButton("Infomation about actions");
	private JButton req = new JButton("Requests");
	private JButton back = new JButton("Back");
	private JButton requestsAndReports = new JButton("Requests and reports");
	
	
	public MenuAdmin(int idUser){
				
		setTitle("Manu");
		setSize(330, 520);
		setLayout(null);	
		setVisible(true);
		
		employee.setLocation(75, 20);
		employee.setSize(180, 40);
		add(employee);
		
		orders.setLocation(75, 70);
		orders.setSize(180, 40);
		add(orders);

		dishes.setLocation(75, 120);
		dishes.setSize(180, 40);
		add(dishes);
		
		products.setLocation(75, 170);
		products.setSize(180, 40);
		add(products);
		
		dishesProducts.setLocation(75, 220);
		dishesProducts.setSize(180, 40);
		add(dishesProducts);
		
		iform.setLocation(75, 270);
		iform.setSize(180, 40);
		add(iform);
		
		requestsAndReports.setLocation(75, 320);
		requestsAndReports.setSize(180, 40);
		add(requestsAndReports);
		
		req.setLocation(75, 370);
		req.setSize(180, 40);
		add(req);
		
		back.setLocation(75, 420);
		back.setSize(180, 40);
		add(back);
		
		iform.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new ActionForm().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new Enter().setVisible(true);
				return;
				
			}
		});
		
		orders.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new Orders(idUser).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}
		});
		
		products.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new Products(idUser).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}
		});
		
		dishes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new Dishes(idUser).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}
		});
		
		dishesProducts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new DishesProducts(idUser).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}
		});
		
		req.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new Req().setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
				
				
			}
		});
		
		requestsAndReports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new ReqReports().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				//Employee empl = new Employee();
				//empl.setVisible(true);
				
			}
		});
		
		employee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new Employee(idUser).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				//Employee empl = new Employee();
				//empl.setVisible(true);
				
			}
		});
		
	}

	

}
