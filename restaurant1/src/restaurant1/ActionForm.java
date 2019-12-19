package restaurant1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import restaurant1.Employee.ListItem;

public class ActionForm extends JFrame {

	ResultSet result, name, name1, selectName, id, sql;

	static class ListItem {
		public String name, surnam, dat, but,tim;
		public int id;

		@Override
		public String toString() {
			return name;
		}
	}

	public ActionForm() throws SQLException {
		setTitle("emplyees");
		setSize(570, 310);
		setLayout(null);

		DefaultListModel dfm = new DefaultListModel();
		JList mylist = new JList(dfm);
		mylist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		mylist.setVisibleRowCount(0);
		JScrollPane msp = new JScrollPane(mylist);
		msp.setSize(520, 240);
		msp.setLocation(10, 10);

		List<ListItem> items = new ArrayList<>();
		ResultSet query = Registrate.statement.executeQuery("SELECT name, surname, data, time, button  FROM actions join registration on registration.idRegistration=actions.idRegistration");
		while (query.next()) {
			ListItem listItem = new ListItem();
			listItem.name = query.getString("name");
			dfm.addElement(listItem.name);
			listItem.surnam = query.getString("surname");
			dfm.addElement(listItem.surnam);
			listItem.dat = query.getString("data");
			dfm.addElement(listItem.dat);
			listItem.tim = query.getString("time");
			dfm.addElement(listItem.tim);
			listItem.but = query.getString("button");
			dfm.addElement(listItem.but);
		}
		add(msp);
	}
}
