package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import restaurant1.Dishes.ListItem;

public class Products extends JFrame {

	ResultSet result, namde, name1, selectName, id, sql;

	private JTextField name = new JTextField("Product");
	private JTextField ost = new JTextField("stock balance");

	private JButton change = new JButton("Change");
	private JButton insertAdd = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private JButton back = new JButton("Back");
	
	static class ListItem {
		public String name, ost;
		public int id;

		@Override
		public String toString() {
			return name;
		}
	}

	public Products(int idUser) throws SQLException {
		setTitle("Products");
		setSize(640, 220);
		setLayout(null);

		/**/

		DefaultListModel dfm = new DefaultListModel();
		JList mylist = new JList(dfm);
		JScrollPane msp = new JScrollPane(mylist);
		msp.setSize(90, 160);
		msp.setLocation(10, 10);

		mylist.setLayoutOrientation(JList.VERTICAL);

		name.setEditable(false);
		name.setLocation(150, 20);
		name.setSize(120, 40);
		add(name);

		ost.setEditable(false);
		ost.setLocation(150, 70);
		ost.setSize(120, 40);
		add(ost);
		
		back.setLocation(470, 120);
		back.setSize(100, 40);
		add(back);

		change.setLocation(140, 120);
		change.setSize(100, 40);
		add(change);

		insertAdd.setLocation(250, 120);
		insertAdd.setSize(100, 40);
		add(insertAdd);

		delete.setLocation(360, 120);
		delete.setSize(100, 40);
		add(delete);

		String adnamest = "";
		String adostst = "";
		
		JTextField adname = new JTextField(adnamest);
		JTextField adost = new JTextField(adostst);
		

		adname.setLocation(300, 20);
		adname.setSize(220, 40);
		add(adname);

		adost.setLocation(300, 70);
		adost.setSize(220, 40);
		add(adost);


		List<ListItem> items = new ArrayList<>();
		
		ResultSet query = Registrate.statement.executeQuery("SELECT id, название FROM продукты");
		while(query.next()) {
			ListItem listItem = new ListItem();
			listItem.id = query.getInt("id");
			listItem.name = query.getString("название");
			//listItem.ost = query.getString("остатокЌа—кладе");
			dfm.addElement(listItem);
		}
		
		add(msp);
		
		mylist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
				ListItem selectedItem = (ListItem) list.getSelectedValuesList().get(0);
				
				try {
					sql = Registrate.statement.executeQuery(
							"SELECT * FROM продукты WHERE продукты.id=" + 
							 selectedItem.id);
					if (sql.next()) {
						
						String adnamest = "продукты.название";
						String adostst = "остатокЌа—кладе";
						
						adname.setText(adnamest);
						adost.setText(adostst);
						
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "delete 1 product"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					

					Registrate.statement.executeUpdate("DELETE FROM продукты WHERE id=" + id);
					JOptionPane.showMessageDialog(null, "Deleted!");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				try {
					result = Registrate.statement.executeQuery("Select status FROM registration where idRegistration =" +idUser);
				
				
				result.next();
					int status = result.getInt("status");
			if(status == 1){
					new MenuAdmin(idUser).setVisible(true);
					return;
				}else if(status==2){
					new MenuDevelopmentManager(idUser).setVisible(true);
					return;
				}else if(status==3){
					new MenuDevelopmentManager(idUser).setVisible(true);
					return;
				}else if(status==4){
					new MenuDevelopmentManager(idUser).setVisible(true);
					return;
				
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		});
		
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					try {		

						Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "change 1 product"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
	                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
						
						
					Registrate.statement.executeUpdate("UPDATE продукты SET продукты.название='" 
							+ adname.getText() + "\',остатокЌа—кладе= \'" + adost.getText() 
							 +	"\' WHERE продукты.id=" + id);
					JOptionPane.showMessageDialog(null, "Added!");

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		insertAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "add 1 product"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					
					Registrate.statement.executeUpdate("INSERT INTO продукты VALUES(null, \'" + adname.getText()
							+ "\', \'" + adost.getText() + "\'");
					JOptionPane.showMessageDialog(null, "Updated!");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		

	}

}
