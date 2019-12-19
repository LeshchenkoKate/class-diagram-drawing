package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MenuDevelopmentManager extends JFrame {
	
	private JButton employee = new JButton("Employee records");
	private JButton orders = new JButton("Order accounting");
	private JButton dishes = new JButton("List of dishes");
	private JButton products = new JButton("Product accounting");
	private JButton dishesProducts = new JButton("Accounting of products spent for dishes");
	private JButton iform = new JButton("Infomation about actions");
	private JButton back = new JButton("Back");
	
	public MenuDevelopmentManager(int idUser){
				
		setTitle("Manu");
		setSize(330, 420);
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
		
		back.setLocation(75, 320);
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