package restaurant1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;

import restaurant1.Products.ListItem;

public class ReqReports extends JFrame {

	private JTextField fam = new JTextField("List of orders served by employee X");
	private JButton change = new JButton("Make report");
	JComboBox comboBox = new JComboBox();
	private JTextField request2 = new JTextField("List of dishes listed in Order X");
	private JButton requestb2 = new JButton("Make report");
	JComboBox requestcb2 = new JComboBox();
	private JTextField request3 = new JTextField("Find all employees whose surname begins with a letter ..");
	private JButton requestb3 = new JButton("Make report");
	JComboBox requestcb3 = new JComboBox();
	private JTextField request4 = new JTextField("Find all customers whose surname begins with the letter ..");
	private JButton requestb4 = new JButton("Make report");
	JComboBox requestcb4 = new JComboBox();
	private JTextField request5 = new JTextField("Find a list of orders which must be completed within the specified period");
	private JButton requestb5 = new JButton("Make report");
	//JComboBox requestcb5 = new JComboBox();
	private JTextField request6 = new JTextField("How many orders have arrived in the last week?");
	private JButton requestb6 = new JButton("Make report");
	//JComboBox requestcb6 = new JComboBox();
	private JTextField request7 = new JTextField("How many orders have each employee performed?");
	private JButton requestb7 = new JButton("Make report");
	//JComboBox requestcb7 = new JComboBox();
	private JTextField request8 = new JTextField("Which of the employees performed the largest number of orders?");
	private JButton requestb8 = new JButton("Make report");
	private JTextField request9 = new JTextField("For each specialty, identify the employee who completed the largest number of orders");
	private JButton requestb9 = new JButton("Make report");
	private JTextField request10 = new JTextField("Which of the employees did not complete any order in the last week?");
	private JButton requestb10 = new JButton("Make report");

	public ReqReports() throws SQLException {

		setTitle("Making report");
		setSize(680, 570);
		setLayout(null);

		fam.setEditable(false);
		fam.setLocation(20, 20);
		fam.setSize(290, 40);
		add(fam);

		change.setLocation(550, 20);
		change.setSize(100, 40);
		add(change);
		
		comboBox.setEditable(true);
		comboBox.setLocation(370, 20);
		comboBox.setSize(120, 40);
		add(comboBox);
		
		request2.setEditable(false);
		request2.setLocation(20, 70);
		request2.setSize(290, 40);
		add(request2);

		requestb2.setLocation(550, 70);
		requestb2.setSize(100, 40);
		add(requestb2);
		
		requestcb2.setEditable(true);
		requestcb2.setLocation(370, 70);
		requestcb2.setSize(120, 40);
		add(requestcb2);
		
		request3.setEditable(false);
		request3.setLocation(20, 120);
		request3.setSize(290, 40);
		add(request3);

		requestb3.setLocation(550, 120);
		requestb3.setSize(100, 40);
		add(requestb3);
		
		requestcb3.setEditable(true);
		requestcb3.setLocation(370, 120);
		requestcb3.setSize(120, 40);
		add(requestcb3);
		
		request4.setEditable(false);
		request4.setLocation(20, 170);
		request4.setSize(290, 40);
		add(request4);

		requestb4.setLocation(550, 170);
		requestb4.setSize(100, 40);
		add(requestb4);
		
		requestcb4.setEditable(true);
		requestcb4.setLocation(370, 170);
		requestcb4.setSize(120, 40);
		add(requestcb4);
		
		request5.setEditable(false);
		request5.setLocation(20, 220);
		request5.setSize(290, 40);
		add(request5);

		requestb5.setLocation(550, 220);
		requestb5.setSize(100, 40);
		add(requestb5);
		
		request6.setEditable(false);
		request6.setLocation(20, 270);
		request6.setSize(290, 40);
		add(request6);

		requestb6.setLocation(550, 270);
		requestb6.setSize(100, 40);
		add(requestb6);
		
		request7.setEditable(false);
		request7.setLocation(20, 320);
		request7.setSize(290, 40);
		add(request7);

		requestb7.setLocation(550, 320);
		requestb7.setSize(100, 40);
		add(requestb7);
		
		request8.setEditable(false);
		request8.setLocation(20, 370);
		request8.setSize(290, 40);
		add(request8);

		requestb8.setLocation(550, 370);
		requestb8.setSize(100, 40);
		add(requestb8);
		
		request9.setEditable(false);
		request9.setLocation(20, 420);
		request9.setSize(290, 40);
		add(request9);

		requestb9.setLocation(550, 420);
		requestb9.setSize(100, 40);
		add(requestb9);
		
		request10.setEditable(false);
		request10.setLocation(20, 470);
		request10.setSize(290, 40);
		add(request10);

		requestb10.setLocation(550, 470);
		requestb10.setSize(100, 40);
		add(requestb10);
		
		ResultSet query = Registrate.statement.executeQuery("SELECT id, ФИО FROM сотрудник");
		while (query.next()) {
			ListItem listItem = new ListItem();
			listItem.id = query.getInt("id");
			listItem.name = query.getString("ФИО");
			comboBox.addItem(listItem);
		}
		
		ResultSet query2 = Registrate.statement.executeQuery("SELECT id, номер FROM заказ");
		while (query2.next()) {
			ListItem listItem2 = new ListItem();
			listItem2.id = query2.getInt("id");
			listItem2.name = query2.getString("номер");
			requestcb2.addItem(listItem2);
		}

		ResultSet query3 = Registrate.statement.executeQuery("SELECT distinct left(ФИО, 1) as name from сотрудник");
		while (query3.next()) {
			ListItem listItem3 = new ListItem();
			listItem3.name = query3.getString("name");
			requestcb3.addItem(listItem3);
		}
		
		ResultSet query4 = Registrate.statement.executeQuery("SELECT distinct left(ФИО, 1) as name from заказчик");
		while (query4.next()) {
			ListItem listItem4 = new ListItem();
			listItem4.name = query4.getString("name");
			requestcb4.addItem(listItem4);
		}
		
		/*ResultSet query5 = Registrate.statement.executeQuery("select заказ.номер, заказ.датаПроведения, заказчик.ФИО from заказ join заказчик on заказ.idЗазказчика=заказчик.id where датаПроведения between '2012-10-10' and '2030-10-10'");
		while (query5.next()) {
			ListItem listItem5 = new ListItem();
			listItem5.name = query5.getString("name");
			requestcb4.addItem(listItem5);
		}*/
		
		change.addActionListener(new ExcelListener(ORDERS_BY_EMPLOYEE));
		requestb2.addActionListener(new ExcelListener(DISHES_BY_ORDER));
		requestb3.addActionListener(new ExcelListener(EMPLOYEE_BY_LETER));
		requestb4.addActionListener(new ExcelListener(CUSTOMER_BY_LETER));
		requestb5.addActionListener(new ExcelListener(CUSTOMER_BY_LETER2));
		requestb6.addActionListener(new ExcelListener(CUSTOMER_BY_LETER3));
		requestb7.addActionListener(new ExcelListener(CUSTOMER_BY_LETER4));
		requestb8.addActionListener(new ExcelListener(CUSTOMER_BY_LETER5));
		requestb9.addActionListener(new ExcelListener(CUSTOMER_BY_LETER6));
		requestb10.addActionListener(new ExcelListener(CUSTOMER_BY_LETER7));
		
		
	}

	static class ExcelListener implements ActionListener {
		private Grid grid;

		public ExcelListener(Grid grid) {
			this.grid = grid;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				new File("D:/Ekaterina/result.docx").delete();
				FileInputStream fis = new FileInputStream("D:/Ekaterina/template.docx");
				XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
				fis.close();
//				System.out.println(document.getDocument().getBody().getPArray().length);
				List<IBodyElement> elements = document.getBodyElements();
				for (int n = 0; n < elements.size(); n++) {
					IBodyElement element = elements.get(n);
					if (element instanceof XWPFParagraph) {
						XWPFParagraph p1 = (XWPFParagraph) element;
						List<XWPFRun> runList = p1.getRuns();
						for (XWPFRun run : runList)
							if (run.getText(0).toString().contains("????")) {
								run.setText("", 0);
//							n++;
//							element = elements.get(n-1);

								XmlCursor cursor = p1.getCTP().newCursor();
								XWPFTable table = p1.getBody().insertNewTbl(cursor);
////--------------------------------------------------------------------------------
								ResultSet query2 = Registrate.statement.executeQuery(grid.query.call());

								for (int i = 0; i < grid.columns.size(); i++) {
									Column column = grid.columns.get(i);
									XWPFTableRow headerRow = table.getRow(0);
									if (i == 0) {
										headerRow.getCell(0).setText(column.header);
									} else {
										headerRow.addNewTableCell().setText(column.header);
									}
								}
								while (query2.next()) {
//					add row
									XWPFTableRow row = table.createRow();
									//

									for (int i = 0; i < grid.columns.size(); i++) {
										Column column = grid.columns.get(i);
										String value;
										switch (column.type) {
										case NUMERIC:
											value = String.valueOf(query2.getInt(i + 1));
											break;
										case STRING:
											value = query2.getString(i + 1);
											break;
										case DATE:
											value = new SimpleDateFormat("yyyy.MM.dd").format(query2.getDate(i + 1));
											break;
										case BIG_DECIMAL:
											value = query2.getBigDecimal(i + 1).toString();
											break;
										default:
											value = "-";
										}
										row.getCell(i).setText(value);
									}
								}

								FileOutputStream outStream = new FileOutputStream("D:/Ekaterina/result.docx");
								document.write(outStream);
								outStream.close();
								return;
							}
					}
				}
				// save file
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private Grid ORDERS_BY_EMPLOYEE = new Grid(
			
			() -> "select заказ.номер, датаПроведения, сотрудник.ФИО, зп from заказ join сотрудник "
					+ "join сотрудникЗаказ on заказ.id=сотрудникЗаказ.idЗазказа "
					+ "and сотрудник.id=сотрудникЗаказ.idСотрудника"
					+ " where сотрудник.id="
					+ ((ListItem) comboBox.getSelectedItem()).id,
			new Column("Номер сотрудника", ColumnType.NUMERIC), new Column("Дата проведения", ColumnType.DATE),
			new Column("Фио", ColumnType.STRING), new Column("ЗП", ColumnType.BIG_DECIMAL));

	private Grid DISHES_BY_ORDER = new Grid(
			() -> "select блюдо.название, блюдоЗаказ.количество, блюдо.стоимость, заказ.номер, "
					+ "заказ.датаПроведения from блюдо join заказ join блюдоЗаказ on блюдо.id=блюдоЗаказ.idБлюда"
					+ " and блюдоЗаказ.idЗазказа=заказ.id where заказ.id="
					+ ((ListItem) requestcb2.getSelectedItem()).id,
			new Column("Name of dish", ColumnType.STRING), new Column("Count of Dishes", ColumnType.NUMERIC),
			new Column("Cost of dish", ColumnType.BIG_DECIMAL), new Column("Number of order", ColumnType.BIG_DECIMAL),
			new Column("Order date", ColumnType.DATE));
	

	private Grid EMPLOYEE_BY_LETER = new Grid(
			() -> "select сотрудник.ФИО, должность.название, должность.обязанности from должность "
					+ "join сотрудник on должность.id=сотрудник.idДолжности where сотрудник.ФИО like '"
					+ ((ListItem) requestcb3.getSelectedItem())
					+ "%'",
			new Column("Employee", ColumnType.STRING), new Column("Position", ColumnType.STRING),
			new Column("Duties", ColumnType.STRING));
	
	private Grid CUSTOMER_BY_LETER = new Grid(
			() -> "select заказчик.ФИО, заказ.номер, заказ.датаПроведения from заказ join заказчик"
					+ " on заказчик.id=заказ.idЗазказчика where заказчик.ФИО like '"
					+ ((ListItem) requestcb4.getSelectedItem())
					+ "%'",
			new Column("Customer", ColumnType.STRING), new Column("Number of order", ColumnType.NUMERIC),
			new Column("Order date", ColumnType.DATE));
	
	private Grid CUSTOMER_BY_LETER2 = new Grid(
			() -> "select заказ.номер, заказ.датаПроведения, заказчик.ФИО from заказ join заказчик on"
					+ " заказ.idЗазказчика=заказчик.id where датаПроведения between '2012-10-10' and '2030-10-10'",
			new Column("Number of order", ColumnType.NUMERIC), new Column("Order date", ColumnType.DATE),
			new Column("Customer", ColumnType.STRING));
	
	private Grid CUSTOMER_BY_LETER3 = new Grid(
			() -> "select заказ.номер, count(*) from заказ where датаПоступленияЗаказа between (now()-interval 7 day) and now();'",
			new Column("Number of order", ColumnType.STRING), new Column("Count", ColumnType.NUMERIC));
	
	private Grid CUSTOMER_BY_LETER4 = new Grid(
			() -> "select сотрудник.ФИО, count(*) from заказ join сотрудникЗаказ join сотрудник on заказ.id=сотрудникЗаказ.idЗазказа and сотрудникЗаказ.idСотрудника=сотрудник.id group by сотрудник.ФИО",
			new Column("Employee", ColumnType.STRING), new Column("Count", ColumnType.NUMERIC));
	
	private Grid CUSTOMER_BY_LETER5 = new Grid(
			() -> "select сотрудник.ФИО, count(*) from заказ join сотрудникЗаказ join сотрудник on заказ.id=сотрудникЗаказ.idЗазказа and сотрудникЗаказ.idСотрудника=сотрудник.id group by сотрудник.ФИО having count(*) >=all(select count(*) from заказ join сотрудникЗаказ join сотрудник on заказ.id=сотрудникЗаказ.idЗазказа and сотрудникЗаказ.idСотрудника=сотрудник.id group by сотрудник.ФИО)",
			new Column("Employee", ColumnType.STRING), new Column("Count", ColumnType.NUMERIC));
	
	private Grid CUSTOMER_BY_LETER6 = new Grid(
			() -> "select сотрудник.ФИО, должность.название from сотрудник join сотрудникЗаказ join должность on сотрудник.idДолжности=должность.id and сотрудник.id=сотрудникЗаказ.idСотрудника group by сотрудник.ФИО having count(*)>=all(select count(*) from сотрудник join сотрудникЗаказ on сотрудник.id=сотрудникЗаказ.idСотрудника group by сотрудникЗаказ.idЗазказа)",
			new Column("Employee", ColumnType.STRING), new Column("Post", ColumnType.STRING));
	
	private Grid CUSTOMER_BY_LETER7 = new Grid(
			() -> "select distinct сотрудник.ФИО from сотрудник where сотрудник.id not in (select сотрудник.id from сотрудник join заказ join сотрудникЗаказ on сотрудник.id = сотрудникЗаказ.idСотрудника and сотрудникЗаказ.idЗазказа=заказ.id where датаПроведения between (now()-interval 7 day) and now())",
			new Column("Employee", ColumnType.STRING));
	
	static class Grid {
		public Grid(Callable<String> query, Column... columns) {
			this.query = query;
			this.columns = Arrays.asList(columns);
		}

		List<Column> columns;
		Callable<String> query;
	}

	static class Column {
		String header;
		ColumnType type;

		public Column(String header, ColumnType type) {
			this.header = header;
			this.type = type;
		}
	}

	static enum ColumnType {
		NUMERIC, STRING, DATE, BIG_DECIMAL
	}
}
