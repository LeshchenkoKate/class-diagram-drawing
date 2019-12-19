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

public class DishesProducts extends JFrame {

	ResultSet result, name, name1, selectName, id, sql;

	private JTextField dishName = new JTextField("Dishes");
	private JTextField cost = new JTextField("Cost");
	private JTextField grammar = new JTextField("Grammar");
	private JTextField category = new JTextField("Category");
	private JTextField comment = new JTextField("Comment");
	private JTextField prodName = new JTextField("Product");
	private JTextField ost = new JTextField("Stock balance");
	private JTextField count = new JTextField("Count");
	private JButton change = new JButton("Change");
	private JButton insertAdd = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private JButton back = new JButton("Back");
	
	static class ListItem {
		public String name, cost, gramm, catigory, coment;
		public int id;

		@Override
		public String toString() {
			return name;
		}
	}

	public DishesProducts(int idUser) throws SQLException {
		setTitle("Dishes");
		setSize(600, 510);
		setLayout(null);
		

		/**/

		DefaultListModel dfm = new DefaultListModel();
		JList mylist = new JList(dfm);
		JScrollPane msp = new JScrollPane(mylist);
		msp.setSize(110, 310);
		msp.setLocation(10, 10);

		mylist.setLayoutOrientation(JList.VERTICAL);

		dishName.setEditable(false);
		dishName.setLocation(150, 20);
		dishName.setSize(120, 40);
		add(dishName);

		cost.setEditable(false);
		cost.setLocation(150, 70);
		cost.setSize(120, 40);
		add(cost);

		grammar.setEditable(false);
		grammar.setLocation(150, 120);
		grammar.setSize(120, 40);
		add(grammar);

		category.setEditable(false);
		category.setLocation(150, 170);
		category.setSize(120, 40);
		add(category);

		comment.setEditable(false);
		comment.setLocation(150, 220);
		comment.setSize(120, 40);
		add(comment);
		
		prodName.setEditable(false);
		prodName.setLocation(150, 270);
		prodName.setSize(120, 40);
		add(prodName);
		
		count.setEditable(false);
		count.setLocation(150, 320);
		count.setSize(120, 40);
		add(count);

		ost.setEditable(false);
		ost.setLocation(150, 370);
		ost.setSize(120, 40);
		add(ost);
		
		back.setLocation(470, 420);
		back.setSize(100, 40);
		add(back);

		change.setLocation(140, 420);
		change.setSize(100, 40);
		add(change);

		insertAdd.setLocation(250, 420);
		insertAdd.setSize(100, 40);
		add(insertAdd);

		delete.setLocation(360, 420);
		delete.setSize(100, 40);
		add(delete);
		
		String addishNamest = "";
		String adcostst = "";
		String adgrammarst = "";
		String adcategoryst = "";
		String adcommentst = "";
		String adprodNamest = "";
		String adostst = "";
		String adcountst ="";
		
		JTextField addishName = new JTextField(addishNamest);
		JTextField adcost = new JTextField(adcostst);
		JTextField adgrammar = new JTextField(adgrammarst);
		JTextField adcategory = new JTextField(adcategoryst);
		JTextField adcomment = new JTextField(adcommentst);
		JTextField adprodName = new JTextField(adprodNamest);
		JTextField adost = new JTextField(adostst);
		JTextField adcount = new JTextField(adcountst);

		addishName.setLocation(300, 20);
		addishName.setSize(220, 40);
		add(addishName);

		adcost.setLocation(300, 70);
		adcost.setSize(220, 40);
		add(adcost);

		adgrammar.setLocation(300, 120);
		adgrammar.setSize(220, 40);
		add(adgrammar);

		adcategory.setLocation(300, 170);
		adcategory.setSize(220, 40);
		add(adcategory);

		adcomment.setLocation(300, 220);
		adcomment.setSize(220, 40);
		add(adcomment);
		
		adprodName.setLocation(300, 270);
		adprodName.setSize(220, 40);
		add(adprodName);
		
		adcount.setLocation(300, 320);
		adcount.setSize(220, 40);
		add(adcount);

		adost.setLocation(300, 370);
		adost.setSize(220, 40);
		add(adost);

		List<ListItem> items = new ArrayList<>();
		
		ResultSet query = Registrate.statement.executeQuery("SELECT id, название FROM блюдо");
		while(query.next()) {
			ListItem listItem = new ListItem();
			listItem.id = query.getInt("id");
			listItem.name = query.getString("название");
			dfm.addElement(listItem);
		}
		
		
		
		add(msp);
//		
		mylist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
	
				ListItem selectedItem = (ListItem) list.getSelectedValuesList().get(0);
				
				try {
					sql = Registrate.statement.executeQuery(
							"SELECT * FROM блюдо join блюдоПродукты join продукты join категория on блюдо.idКатегории=категория.id and блюдоПродукты.idБлюда=блюдо.id and блюдоПродукты.idПродуктов=продукты.id WHERE блюдо.id=" + 
							 selectedItem.id);
					if (sql.next()) {
						
						String addishNamest = sql.getString("блюдо.название");
						String adcostst = sql.getString("стоимость");
						String adgrammarst = sql.getString("граммовка");
						String adcategoryst = sql.getString("категория.название");
						String adcommentst = sql.getString("coment");
						String adprodNamest = sql.getString("coment");
						String adostst = sql.getString("продукты.название");
						String adcountst = sql.getString("остатокНаСкладе");
						
						addishName.setText(addishNamest);
						adcost.setText(adcostst);
						adgrammar.setText(adgrammarst);
						adcategory.setText(adcategoryst);
						adcomment.setText(adcommentst);
						adprodName.setText(adprodNamest);
						adost.setText(adostst);
						adcount.setText(adcountst);

						
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
					
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "delete 1 dishe"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					

					Registrate.statement.executeUpdate("DELETE FROM блюдо WHERE id=" + id);
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

						Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "change 1 dishe"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
	                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
						
					Registrate.statement.executeUpdate("UPDATE блюдо join категория join блюдоПродукты join продукты on блюдо.idКатегории=категория.id and блюдоПродукты.idБлюда=блюдо.id and блюдоПродукты.idПродуктов=продукты.id SET блюдо.название='" 
							+ addishName.getText() + "\',стоимость= \'" + adcost.getText() + "\',граммовка= \'" + adgrammar.getText()
							+  "\',категория.название= \'" + adcategory.getText() + "\',coment= \'" + adcomment.getText() 
							+  "\',сколькоНадо= \'" + adcount.getText() + "\',продукты.название= \'" + adprodName.getText() 
							+ "\',продукты.остатокНаСкладе= \'" + adost.getText() +	"\' WHERE блюдо.id=" + id);
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
					
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "add 1 product to dish"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					
					Registrate.statement.executeUpdate("INSERT INTO блюдо VALUES( null, \'" + addishName.getText()
							+ "\', \'" + adcost.getText() + "\', \'" + adgrammar.getText() + "\', 2, NULL)");
					JOptionPane.showMessageDialog(null, "Updated!");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		

	}

}
