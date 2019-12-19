package restaurant1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import restaurant1.ReqReports.ExcelListener;

public class Req extends JFrame {

	private JTextField fam = new JTextField("List of orders served by employee X");
	private JButton change = new JButton("Make report");
	JComboBox comboBox = new JComboBox();

	public Req() throws SQLException {

		setTitle("Making requests");
		setSize(680, 420);
		setLayout(null);
		
		change.setLocation(550, 20);
		change.setSize(100, 40);
		add(change);
		
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Table model = new Table();
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				//centerRenderer.setHorizontalAlignment(jlabel.CENTER);
				

				
				try {
					ResultSet rs = Registrate.statement.executeQuery(
							"select заказ.номер, заказ.датаПроведения, заказчик.ФИО from заказ join заказчик on заказ.idЗазказчика=заказчик.id where датаПроведения between '2012-10-10' and '2030-10-10'");
					model.setDataSource(rs);
					
					JTable table = new JTable(model);
					table.setBackground(UIManager.getColor("Button.background"));
					table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
					table.setDefaultRenderer(String.class, centerRenderer);
					JLabel label = new JLabel();
					label.add(table);
					label.setSize(100, 100);
					label.setLocation(10,10);
					add (label);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		
		
		
	}
}