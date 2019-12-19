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

public class Employee extends JFrame {

	ResultSet result, name, name1, selectName, id, sql;

	private JTextField fam = new JTextField("Name");
	private JTextField experience = new JTextField("Experience");
	private JTextField rate = new JTextField("Rate");
	private JTextField job = new JTextField("Job");
	private JTextField duties = new JTextField("Position duties");
	private JTextField room = new JTextField("Banquet room");
	private JTextField spaciousness = new JTextField("Spaciousness");
	private JTextField tables = new JTextField("Number of tables");
	private JTextField music = new JTextField("Music");
	private JTextField smoking = new JTextField("Smoking");
	private JButton change = new JButton("Change");
	private JButton insertAdd = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private JButton back = new JButton("Back");
	
	static class ListItem {
		public String name, exp, rate, job, duties, room, spaciousness, tables, music, smoking;
		public int id;
		
		@Override
		public String toString() {
			return name;
		}
	}

	public Employee(int idUser) throws SQLException {
		setTitle("emplyees");
		setSize(640, 620);
		setLayout(null);

		/**/

		DefaultListModel dfm = new DefaultListModel();
		JList mylist = new JList(dfm);
		JScrollPane msp = new JScrollPane(mylist);
		msp.setSize(90, 520);
		msp.setLocation(10, 10);

		mylist.setLayoutOrientation(JList.VERTICAL);

		fam.setEditable(false);
		fam.setLocation(150, 20);
		fam.setSize(120, 40);
		add(fam);

		experience.setEditable(false);
		experience.setLocation(150, 70);
		experience.setSize(120, 40);
		add(experience);

		rate.setEditable(false);
		rate.setLocation(150, 120);
		rate.setSize(120, 40);
		add(rate);

		job.setEditable(false);
		job.setLocation(150, 170);
		job.setSize(120, 40);
		add(job);

		duties.setEditable(false);
		duties.setLocation(150, 220);
		duties.setSize(120, 40);
		add(duties);

		room.setEditable(false);
		room.setLocation(150, 270);
		room.setSize(120, 40);
		add(room);

		spaciousness.setEditable(false);
		spaciousness.setLocation(150, 320);
		spaciousness.setSize(120, 40);
		add(spaciousness);

		tables.setEditable(false);
		tables.setLocation(150, 370);
		tables.setSize(120, 40);
		add(tables);

		music.setEditable(false);
		music.setLocation(150, 420);
		music.setSize(120, 40);
		add(music);

		smoking.setEditable(false);
		smoking.setLocation(150, 470);
		smoking.setSize(120, 40);
		add(smoking);
		
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

	
		String familiya = "";
		String ctaz = "";
		String ctavka = "";
		String dolz = "";
		String ob = "";
		String bankzal = "";
		String vmest = "";
		String tables = "";
		String music = "";
		String smoke = "";

		JTextField adfam = new JTextField(familiya);
		JTextField adexperience = new JTextField(ctaz);
		JTextField adrate = new JTextField(ctavka);
		JTextField adjob = new JTextField(dolz);
		JTextField adduties = new JTextField(ob);
		JTextField adroom = new JTextField(bankzal);
		JTextField adspaciousness = new JTextField(vmest);
		JTextField adtables = new JTextField(tables);
		JTextField admusic = new JTextField(music);
		JTextField adsmoking = new JTextField(smoke);

		adfam.setLocation(300, 20);
		adfam.setSize(220, 40);
		add(adfam);

		adexperience.setLocation(300, 70);
		adexperience.setSize(220, 40);
		add(adexperience);

		adrate.setLocation(300, 120);
		adrate.setSize(220, 40);
		add(adrate);

		adjob.setLocation(300, 170);
		adjob.setSize(220, 40);
		add(adjob);

		adduties.setLocation(300, 220);
		adduties.setSize(220, 40);
		add(adduties);

		adroom.setLocation(300, 270);
		adroom.setSize(220, 40);
		add(adroom);

		adspaciousness.setLocation(300, 320);
		adspaciousness.setSize(220, 40);
		add(adspaciousness);

		adtables.setLocation(300, 370);
		adtables.setSize(220, 40);
		add(adtables);

		admusic.setLocation(300, 420);
		admusic.setSize(220, 40);
		add(admusic);

		adsmoking.setLocation(300, 470);
		adsmoking.setSize(220, 40);
		add(adsmoking);
		List<ListItem> items = new ArrayList<>();
		
		ResultSet query = Registrate.statement.executeQuery("SELECT id, ФИО FROM сотрудник");
		while(query.next()) {
			ListItem listItem = new ListItem();
			listItem.id = query.getInt("id");
			listItem.name = query.getString("ФИО");
			dfm.addElement(listItem);
		}
		
		add(msp);	
		mylist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					JList list = (JList) e.getSource();
		
					ListItem selectedItem = (ListItem) list.getSelectedValuesList().get(0);
					
					try {
						sql = Registrate.statement.executeQuery(
								"select * from сотрудник join должность join банкетныйЗал on сотрудник.idДолжности = должность.id and сотрудник.idБанкетногоЗала = банкетныйЗал.id where сотрудник.id="
										+ selectedItem.id);
						if (sql.next()) {
							// Retrieve by column name
							String familiya = sql.getString("сотрудник.ФИО");
							String ctaz = sql.getString("сотрудник.стаж");
							String ctavka = sql.getString("сотрудник.ставка");
							String dolz = sql.getString("должность.название");
							String ob = sql.getString("должность.обязанности");
							String bankzal = sql.getString("банкетныйЗал.номер");
							String vmest = sql.getString("банкетныйЗал.вместительность");
							String tables = sql.getString("банкетныйЗал.количествоСтолов");
							String music = sql.getString("банкетныйЗал.музыка");
							String smoke = sql.getString("банкетныйЗал.курение");

							adfam.setText(familiya);
							adexperience.setText(ctaz);
							adrate.setText(ctavka);
							adjob.setText(dolz);
							adduties.setText(ob);
							adroom.setText(bankzal);
							adspaciousness.setText(vmest);
							adtables.setText(tables);
							admusic.setText(music);
							adsmoking.setText(smoke);
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "delete 1 employee"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					Registrate.statement.executeUpdate("DELETE FROM сотрудник WHERE id=" + id);
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
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "change 1 employee"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");
					
					Registrate.statement.executeUpdate("UPDATE сотрудник join банкетныйЗал join должность "
							+ "on сотрудник.idДолжности = должность.id and сотрудник.idБанкетногоЗала = банкетныйЗал.id SET ,сотрудник.ФИО='" 
							+ adfam.getText() + "\',стаж= \'" + adexperience.getText() + "\',ставка= \'" + adrate.getText()
							+  "\',название= \'" + adjob.getText() + "\',обязанности= \'" + adduties.getText() 
							+ "\',номер= \'" + adroom.getText() + "\',вместительность= \'" + adspaciousness.getText()
							+ "\',количествоСтолов= \'" + adtables.getText() +"\',музыка= \'" + admusic.getText() 
							+ "\',курение= \'" + admusic.getText() +	"\' WHERE сотрудник.id=" + id);
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
					
					Date date = new Date();
					Registrate.statement.executeUpdate("Insert into actions values (null,\'" + "add 1 employee"+"\',\'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\',\'"+
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))+"\'," + idUser+")");

					Registrate.statement.executeUpdate("INSERT INTO сотрудник VALUES(null, \'" + adfam.getText()
							+ "\', \'" + adexperience.getText() + "\', \'" + adrate.getText() + "\', 6, 6, Null)");
					JOptionPane.showMessageDialog(null, "Updated!");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		
	}

}
