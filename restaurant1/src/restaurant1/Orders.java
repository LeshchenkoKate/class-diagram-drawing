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

import restaurant1.DishesProducts.ListItem;

public class Orders extends JFrame {

	ResultSet result, name, name1, selectName, id, sql;

	private JTextField number = new JTextField("Number of order");
	private JTextField amount = new JTextField("Amount man");
	private JTextField date1 = new JTextField("Date of receipt of order");
	private JTextField date2 = new JTextField("Banquet date");
	private JTextField nameCustomer = new JTextField("Name of the customer");
	private JTextField passport = new JTextField("Customer passport");
	private JTextField banquetRoom = new JTextField("Banquet room");
	private JTextField spaciousness = new JTextField("Spaciousness");
	private JTextField NumberTables = new JTextField("Number of tables");
	private JTextField music = new JTextField("Music");
	private JTextField smoking = new JTextField("Smoking");
	private JTextField dishName = new JTextField("Dish name");
	private JTextField quantity = new JTextField("Quantity");
	private JTextField cost = new JTextField("Cost");
	private JTextField grammar = new JTextField("Grammar");
	private JTextField surname = new JTextField("Name of of the employee");
	private JTextField experience = new JTextField("Experience");
	private JTextField rate = new JTextField("Rate");
	private JTextField salary = new JTextField("Salary");
	
	private JButton change = new JButton("Change");
	private JButton insertAdd = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private JButton back = new JButton("Back");

	/*номер
	коичествоЧеловек 
	датаПоступленияЗаказа
	датаПроведения
	ФИО
	паспорт
	номер
	вместительность
	количествоСтолов
	музыка
	курение
	количество
	стоимость
	зп
	название
	стоимость
	граммовка
	ФИО
	стаж
	ставка
	*/

	
	static class ListItem {
		public String  name, exp, rate, job, duties, room, spaciousness, tables, music, smoking;
		public int id, number;
		
		@Override
		public String toString() {
			return name;
		}
	}

	public Orders(int idUser) throws SQLException {
		setTitle("Orders");
		setSize(970, 620);
		setLayout(null);

		/**/

		DefaultListModel dfm = new DefaultListModel();
		JList mylist = new JList(dfm);
		JScrollPane msp = new JScrollPane(mylist);
		msp.setSize(120, 500);
		msp.setLocation(10, 10);

		mylist.setLayoutOrientation(JList.VERTICAL);

		number.setEditable(false);
		number.setLocation(150, 20);
		number.setSize(120, 40);
		add(number);

		amount.setEditable(false);
		amount.setLocation(150, 70);
		amount.setSize(120, 40);
		add(amount);

		date1.setEditable(false);
		date1.setLocation(150, 120);
		date1.setSize(120, 40);
		add(date1);

		date2.setEditable(false);
		date2.setLocation(150, 170);
		date2.setSize(120, 40);
		add(date2);

		nameCustomer.setEditable(false);
		nameCustomer.setLocation(150, 220);
		nameCustomer.setSize(120, 40);
		add(nameCustomer);

		passport.setEditable(false);
		passport.setLocation(150, 270);
		passport.setSize(120, 40);
		add(passport);

		banquetRoom.setEditable(false);
		banquetRoom.setLocation(150, 320);
		banquetRoom.setSize(120, 40);
		add(banquetRoom);

		spaciousness.setEditable(false);
		spaciousness.setLocation(150, 370);
		spaciousness.setSize(120, 40);
		add(spaciousness);

		NumberTables.setEditable(false);
		NumberTables.setLocation(150, 420);
		NumberTables.setSize(120, 40);
		add(NumberTables);

		music.setEditable(false);
		music.setLocation(550, 20);
		music.setSize(120, 40);
		add(music);
		
		
		smoking.setEditable(false);
		smoking.setLocation(550, 70);
		smoking.setSize(120, 40);
		add(smoking);

		dishName.setEditable(false);
		dishName.setLocation(550, 120);
		dishName.setSize(120, 40);
		add(dishName);

		quantity.setEditable(false);
		quantity.setLocation(550, 170);
		quantity.setSize(120, 40);
		add(quantity);
		
		cost.setEditable(false);
		cost.setLocation(550, 220);
		cost.setSize(120, 40);
		add(cost);

		grammar.setEditable(false);
		grammar.setLocation(550, 270);
		grammar.setSize(120, 40);
		add(grammar);

		surname.setEditable(false);
		surname.setLocation(550, 320);
		surname.setSize(120, 40);
		add(surname);
		
		experience.setEditable(false);
		experience.setLocation(550, 370);
		experience.setSize(120, 40);
		add(experience);

		rate.setEditable(false);
		rate.setLocation(550, 420);
		rate.setSize(120, 40);
		add(rate);

		salary.setEditable(false);
		salary.setLocation(550, 470);
		salary.setSize(120, 40);
		add(salary);
		
		back.setLocation(470, 520);
		back.setSize(100, 40);
		add(back);

		change.setLocation(140, 520);
		change.setSize(100, 40);
		add(change);

		insertAdd.setLocation(250, 520);
		insertAdd.setSize(100, 40);
		add(insertAdd);

		delete.setLocation(360, 520);
		delete.setSize(100, 40);
		add(delete);
		
		String adnumberst = "";
		String adamountst = "";
		String addate1st = "";
		String addate2st = "";
		String adnameCustomerst = "";
		String adpassportst = "";
		String adbanquetRoomst = "";
		String adspaciousnessst = "";
		String adNumberTablesst = "";
		String admusicst = "";
		String adsmokingst = "";
		String addishNamest = "";
		String adquantityst = "";
		String adcostst = "";
		String adgrammarst = "";
		String adsurnamest = "";
		String adexperiencest = "";
		String adratest = "";
		String adsalaryst = "";
	
		JTextField adnumber = new JTextField(adnumberst);
		JTextField adamount = new JTextField(adamountst);
		JTextField addate1 = new JTextField(addate1st);
		JTextField addate2 = new JTextField(addate2st);
		JTextField adnameCustomer = new JTextField(adnameCustomerst);
		JTextField adpassport = new JTextField(adpassportst);
		JTextField adbanquetRoom = new JTextField(adbanquetRoomst);
		JTextField adspaciousness = new JTextField(adspaciousnessst);
		JTextField adNumberTables = new JTextField(adNumberTablesst);
		JTextField admusic = new JTextField(admusicst);
		JTextField adsmoking = new JTextField(adsmokingst);
		JTextField addishName = new JTextField(addishNamest);
		JTextField adquantity = new JTextField(adquantityst);
		JTextField adcost = new JTextField(adcostst);
		JTextField adgrammar = new JTextField(adgrammarst);
		JTextField adsurname = new JTextField(adsurnamest);
		JTextField adexperience = new JTextField(adexperiencest);
		JTextField adrate = new JTextField(adratest);
		JTextField adsalary = new JTextField(adsalaryst);


		adnumber.setLocation(300, 20);
		adnumber.setSize(220, 40);
		add(adnumber);

		adamount.setLocation(300, 70);
		adamount.setSize(220, 40);
		add(adamount);

		addate1.setLocation(300, 120);
		addate1.setSize(220, 40);
		add(addate1);

		addate2.setLocation(300, 170);
		addate2.setSize(220, 40);
		add(addate2);

		adnameCustomer.setLocation(300, 220);
		adnameCustomer.setSize(220, 40);
		add(adnameCustomer);

		adpassport.setLocation(300, 270);
		adpassport.setSize(220, 40);
		add(adpassport);

		adbanquetRoom.setLocation(300, 320);
		adbanquetRoom.setSize(220, 40);
		add(adbanquetRoom);

		adspaciousness.setLocation(300, 370);
		adspaciousness.setSize(220, 40);
		add(adspaciousness);

		adNumberTables.setLocation(300, 420);
		adNumberTables.setSize(220, 40);
		add(adNumberTables);

		admusic.setLocation(700, 20);
		admusic.setSize(220, 40);
		add(admusic);
		
		adsmoking.setLocation(700, 70);
		adsmoking.setSize(220, 40);
		add(adsmoking);

		addishName.setLocation(700, 120);
		addishName.setSize(220, 40);
		add(addishName);

		adquantity.setLocation(700, 170);
		adquantity.setSize(220, 40);
		add(adquantity);

		adcost.setLocation(700, 220);
		adcost.setSize(220, 40);
		add(adcost);

		adgrammar.setLocation(700, 270);
		adgrammar.setSize(220, 40);
		add(adgrammar);

		adsurname.setLocation(700, 320);
		adsurname.setSize(220, 40);
		add(adsurname);

		adexperience.setLocation(700, 370);
		adexperience.setSize(220, 40);
		add(adexperience);

		adrate.setLocation(700, 420);
		adrate.setSize(220, 40);
		add(adrate);
		
		adsalary.setLocation(700, 470);
		adsalary.setSize(220, 40);
		add(adsalary);
		
		List<ListItem> items = new ArrayList<>();
		
		ResultSet query = Registrate.statement.executeQuery("SELECT id, номер FROM заказ");
		while(query.next()) {
			ListItem listItem = new ListItem();
			listItem.id = query.getInt("id");
			listItem.number = query.getInt("номер");
			dfm.addElement(listItem.number);
		}
		
	
		add(msp);

		mylist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
	
				ListItem selectedItem = (ListItem) list.getSelectedValuesList().get(0);
				
				try {
					sql = Registrate.statement.executeQuery(
							"SELECT заказ.id, заказ.номер, заказ.коичествоЧеловек, датаПоступленияЗаказа, датаПроведения, заказчик.ФИО,"
							+ " заказчик.паспорт, банкетныйЗал.номер,	вместительность, количествоСтолов, музыка, курение,	блюдоЗаказ.количество,"
							+ " блюдоЗаказ.стоимость, зп, блюдо.название, блюдо.стоимость, граммовка, сотрудник.ФИО, стаж, ставка FROM заказ join заказчик join банкетныйЗал join блюдоЗаказ join сотрудникЗаказ join блюдо join сотрудник on заказ.idЗазказчика=заказчик.id and банкетныйЗал.id=заказ.idБанкетногоЗала and блюдоЗаказ.idЗазказа=заказ.id and блюдоЗаказ.idБлюда=блюдо.id and сотрудникЗаказ.idСотрудника=сотрудник.id and сотрудникЗаказ.idЗазказа=заказ.id where заказ.id = "
							+ selectedItem.id);
					if (sql.next()) {

						String adnumberst = sql.getString("заказ.номер");
						String adamountst = sql.getString("заказ.коичествоЧеловек");
						String addate1st = sql.getString("датаПоступленияЗаказа");
						String addate2st = sql.getString("датаПроведения");
						String adnameCustomerst = sql.getString("заказчик.ФИО");;
						String adpassportst = sql.getString("заказчик.паспорт");
						String adbanquetRoomst = sql.getString("банкетныйЗал.номер");
						String adspaciousnessst = sql.getString("вместительность");
						String adNumberTablesst = sql.getString("количествоСтолов");
						String admusicst = sql.getString("музыка");
						String adsmokingst = sql.getString("курение");
						String addishNamest = sql.getString("блюдо.название");
						String adquantityst = sql.getString("блюдоЗаказ.количество");
						String adcostst = sql.getString("блюдо.стоимость");
						String adgrammarst = sql.getString("граммовка");
						String adsurnamest = sql.getString("сотрудник.ФИО");
						String adexperiencest = sql.getString("стаж");
						String adratest = sql.getString("ставка");
						String adsalaryst = sql.getString("зп");
					
						adnumber.setText(adnumberst);
						adamount.setText(adamountst);
						addate1.setText(addate1st);
						addate2.setText(addate2st);
						adnameCustomer.setText(adnameCustomerst);
						adpassport.setText(adpassportst);
						adbanquetRoom.setText(adbanquetRoomst);
						adspaciousness.setText(adspaciousnessst);
						adNumberTables.setText(adNumberTablesst);
						admusic.setText(admusicst);
						adsmoking.setText(adsmokingst);
						addishName.setText(addishNamest);
						adquantity.setText(adquantityst);
						adcost.setText(adcostst);
						adgrammar.setText(adgrammarst);
						adsurname.setText(adsurnamest);
						adexperience.setText(adexperiencest);
						adrate.setText(adratest);
						adsalary.setText(adsalaryst);

						
						
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

					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "delete 1 order"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					
					Registrate.statement.executeUpdate("DELETE FROM заказ WHERE id=" + id);
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
					
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "chage 1 order"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					
					Registrate.statement.executeUpdate("UPDATE заказ join заказчик join банкетныйЗал join блюдоЗаказ join сотрудникЗаказ join блюдо join сотрудник "
							+ "on заказ.idЗазказчика=заказчик.id and банкетныйЗал.id=заказ.idБанкетногоЗала and "
							+ "блюдоЗаказ.idЗазказа=заказ.id and блюдоЗаказ.idБлюда=блюдо.id and "
							+ "сотрудникЗаказ.idСотрудника=сотрудник.id and сотрудникЗаказ.idЗазказа=заказ.id SET заказ.номер='" 
							+ adnumber.getText() + "\',заказ.коичествоЧеловек= \'" + adamount.getText() + "\',датаПоступленияЗаказа= \'" + addate1.getText()
							+  "\',датаПроведения= \'" + addate2.getText() + "\',заказчик.ФИО= \'" + adnameCustomer.getText() 
							+ "\',заказчик.паспорт= \'" + adpassport.getText() + "\',банкетныйЗал.номер= \'" + adbanquetRoom.getText()
							+ "\',вместительность= \'" + adspaciousness.getText() +"\',количествоСтолов= \'" + adNumberTables.getText() 
							+ "\',музыка= \'" + admusic.getText() +	 "\',курение= \'" + adsmoking.getText()  +	 "\',блюдо.название= \'" + addishName.getText()
							+ "\',блюдоЗаказ.количество= \'" + adquantity.getText() + "\',блюдоЗаказ.стоимость= \'" + adcost.getText()
							+ "\',граммовка= \'" + adgrammar.getText() + "\',сотрудник.ФИО= \'" + adsurname.getText()
							+ "\',стаж= \'" + adexperience.getText() + "\',ставка= \'" + adrate.getText() +"\',зп= \'" + adsalary.getText()
							+"\' WHERE заказ.id=" + id);
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

					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "add 1 order"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					
					Registrate.statement.executeUpdate("INSERT INTO заказ VALUES( null, \'" + adnumber.getText()
							+ "\', \'" + adamount.getText() + "\', \'" + addate1.getText()  + "\', \'" + addate2.getText() + "\', 6, 6)");
					JOptionPane.showMessageDialog(null, "Updated!");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		
	}
	}