package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;



public class ManuKitchenManager extends JFrame {
	
	private JButton orders = new JButton("Order accounting");
	private JButton dishes = new JButton("List of dishes");
	private JButton products = new JButton("Product accounting");
	private JButton dishesProducts = new JButton("Accounting of products spent for dishes");
	private JButton requestsAndReports = new JButton("Requests and reports");
	private JButton back = new JButton("Back");
	
	public ManuKitchenManager(int idUser){
				
		setTitle("Manu");
		setSize(330, 370);
		setLayout(null);	
		setVisible(true);
		
		orders.setLocation(75, 20);
		orders.setSize(180, 40);
		add(orders);

		dishes.setLocation(75, 70);
		dishes.setSize(180, 40);
		add(dishes);
		
		products.setLocation(75, 120);
		products.setSize(180, 40);
		add(products);
		
		dishesProducts.setLocation(75, 170);
		dishesProducts.setSize(180, 40);
		add(dishesProducts);
		
		back.setLocation(75, 270);
		back.setSize(180, 40);
		add(back);
		
		requestsAndReports.setLocation(75, 220);
		requestsAndReports.setSize(180, 40);
		add(requestsAndReports);	
		
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
		
	}

	

}