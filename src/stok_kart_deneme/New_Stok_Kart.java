package stok_kart_deneme;

import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;

public class New_Stok_Kart {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	DefaultTableModel model;
	private JTable tblStokKart;
	private JTextField txtSearch;
	private JTextField txtStokkodu;
	private JButton btnSave;
	private JLabel lblStokKodu;
	private JLabel lblStokAdi;
	private JLabel lblStokTipi;
	private JLabel lblBirimi;
	private JTextField txtStokAdi;
	private JComboBox cmbBxBirimi;
	private JLabel lblBarkodu;
	private JLabel lblKDVTipi;
	private JLabel lblAciklama;
	private JLabel lblOlusturmaTarihi;
	private JTextField txtBarkodu;
	private JComboBox cmbBxKDVTipi;
	private JTextArea txtAciklama;
	private JButton btnDelete;
	private JButton btnSave_1;
	private JButton btnDelete_1;
	private final JScrollPane scrollPane = new JScrollPane();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblKopyalananDeger;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private JTextField txtCopyStokKodu;
	private JTextField txtCopyStokAdi;
	private JTextField txtCopyStokTipi;
	private JTextField txtCopyBirimi;
	private JTextField txtCopyBarkodu;
	private JTextField txtCopyKDVTipi;
	private JTextField txtCopyAciklama;
	private JTextField txtCopyOlusturmaTarihi;
	private JButton btnNewButton_2;
	
	public ArrayList<StokKart> getStokKarts() throws SQLException{
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement = null;
		ResultSet resultSet;
		ArrayList<StokKart> stokKarts=null;
		try {
			connection = helper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from stok_kart");
			stokKarts = new ArrayList<StokKart>();
			while(resultSet.next()) {
				stokKarts.add(new StokKart(resultSet.getString("Stok_Kodu"), resultSet.getString("Stok_Adı"), resultSet.getString("Stok_Tipi"), resultSet.getString("Birimi"), resultSet.getString("Barkodu"), resultSet.getDouble("KDV_Tipi"), resultSet.getString("Açıklama"), resultSet.getString("Oluşturma_tarihi")));
			}
		}catch(SQLException e){
			helper.showErrorMessage(e);
			
		}
		finally{
			connection.close();
			statement.close();
		}
		return stokKarts;
	}
	
	
	public ArrayList<Copy> getStokKarts2() throws SQLException{
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement = null;
		ResultSet resultSet;
		ArrayList<Copy> stokKarts2=null;
		try {
			connection = helper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select Stok_Kodu,Stok_Adı,Stok_Tipi,Birimi,Barkodu,KDV_Tipi,Açıklama,Oluşturma_Tarihi from stok_kart where Stok_kodu ='"+txtStokkodu.getText()+"' ");
			stokKarts2 = new ArrayList<Copy>();
			while(resultSet.next()) {
				stokKarts2.add(new Copy(resultSet.getString("Stok_Kodu"),resultSet.getString("Stok_Adı"),resultSet.getString("Stok_Tipi"), resultSet.getString("Birimi"), resultSet.getString("Barkodu"), resultSet.getString("KDV_Tipi"), resultSet.getString("Açıklama"), resultSet.getString("Oluşturma_tarihi")));
			}
		}catch(SQLException e){
			helper.showErrorMessage(e);
			
		}
		finally{
			connection.close();
			statement.close();
		}
		return stokKarts2;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_Stok_Kart window = new New_Stok_Kart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	
	public New_Stok_Kart() {
		initialize();
		populateTable();
		//deneme();
		
	}
	public void populateTable() {
		model = (DefaultTableModel)tblStokKart.getModel();
		model.setRowCount(0);
		try {
			ArrayList<StokKart> stokKarts = getStokKarts();
			for(StokKart stokKart : stokKarts) {
				Object[] rowObjects = {stokKart.getStokKodu(), stokKart.getStokAdi(), stokKart.getStokTipi(), stokKart.getBirimi(), stokKart.getBarkodu(), stokKart.getKdvTipi(), stokKart.getAciklama(), stokKart.getOlusturmaTarihi()};
			    model.addRow(rowObjects);
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public void deneme() {
		try {
			ArrayList<Copy> stokKarts2 =getStokKarts2();
			for(Copy stokKarts22 : stokKarts2) {
				lblNewLabel.setText(stokKarts22.getStokKodu());
				lblNewLabel_1.setText(stokKarts22.getStokAdi());
				lblNewLabel_2.setText(stokKarts22.getStokTipi());
				lblNewLabel_3.setText(stokKarts22.getBirimi());
				lblNewLabel_4.setText(stokKarts22.getBarkodu());
				lblNewLabel_5.setText(stokKarts22.getKdvTipi());
				lblNewLabel_6.setText(stokKarts22.getAciklama());
				lblNewLabel_7.setText(stokKarts22.getOlusturmaTarihi());
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1303, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchkeyString = txtSearch.getText();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(model);
				tblStokKart.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(searchkeyString));
			}
		});
		txtSearch.setBounds(141, 68, 418, 33);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblSearch.setBounds(54, 68, 77, 33);
		frame.getContentPane().add(lblSearch);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setForeground(SystemColor.controlDkShadow);
		panel.setBounds(597, 76, 541, 458);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtStokkodu = new JTextField();
		txtStokkodu.setBounds(141, 68, 114, 27);
		panel.add(txtStokkodu);
		txtStokkodu.setColumns(10);
		
		String[] arr = {"","1","2","3"};
		JComboBox cmbBxStokTipi = new JComboBox(arr);
		cmbBxStokTipi.setBounds(141, 155, 114, 27);
		panel.add(cmbBxStokTipi);
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(405, 199, 114, 19);
		panel.add(dateChooser);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				DbHelper helper = new DbHelper();
				PreparedStatement preparedStatement = null;
				try {
					connection = helper.getConnection();
					String sql = "insert into stok_kart () values(?,?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, txtStokkodu.getText());
					preparedStatement.setString(2, txtStokAdi.getText());
					preparedStatement.setObject(3, cmbBxStokTipi.getSelectedItem());
					preparedStatement.setObject(4, cmbBxBirimi.getSelectedItem());
					preparedStatement.setString(5, txtBarkodu.getText());
					preparedStatement.setObject(6, cmbBxKDVTipi.getSelectedItem());
					preparedStatement.setString(7, txtAciklama.getText());
					preparedStatement.setObject(8, dateChooser.getDate());
					int result = preparedStatement.executeUpdate();
					populateTable();
					JOptionPane.showMessageDialog(btnSave, "KART EKLENDİ");
				}catch(SQLException e3){
					helper.showErrorMessage(e3);
				}finally {
					
					try {
						preparedStatement.close();
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			}
		}); 
		btnSave.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSave.setForeground(new Color(0, 0, 205));
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setBounds(219, 265, 123, 38);
		panel.add(btnSave);
		
		lblStokKodu = new JLabel("Stok Kodu");
		lblStokKodu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStokKodu.setBounds(21, 61, 110, 38);
		panel.add(lblStokKodu);
		
		lblStokAdi = new JLabel("Stok Adı");
		lblStokAdi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStokAdi.setBounds(21, 104, 110, 38);
		panel.add(lblStokAdi);
		
		lblStokTipi = new JLabel("Stok Tipi");
		lblStokTipi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStokTipi.setBounds(21, 154, 110, 38);
		panel.add(lblStokTipi);
		
		lblBirimi = new JLabel("Birimi");
		lblBirimi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBirimi.setBounds(21, 192, 110, 38);
		panel.add(lblBirimi);
		
		txtStokAdi = new JTextField();
		txtStokAdi.setColumns(10);
		txtStokAdi.setBounds(141, 107, 114, 27);
		panel.add(txtStokAdi);
		
		String[] arr2= {"","Yazilim","Donanim"};
		cmbBxBirimi = new JComboBox(arr2);
		cmbBxBirimi.setBounds(141, 199, 114, 27);
		panel.add(cmbBxBirimi);
		
		lblBarkodu = new JLabel("Barkodu");
		lblBarkodu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBarkodu.setBounds(285, 61, 110, 38);
		panel.add(lblBarkodu);
		
		lblKDVTipi = new JLabel("KDV Tipi");
		lblKDVTipi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKDVTipi.setBounds(285, 104, 110, 38);
		panel.add(lblKDVTipi);
		
		lblAciklama = new JLabel("Açıklama");
		lblAciklama.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAciklama.setBounds(285, 148, 110, 38);
		panel.add(lblAciklama);
		
		lblOlusturmaTarihi = new JLabel("Oluşturma Tarihi");
		lblOlusturmaTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOlusturmaTarihi.setBounds(285, 192, 110, 38);
		panel.add(lblOlusturmaTarihi);
		
		txtBarkodu = new JTextField();
		txtBarkodu.setColumns(10);
		txtBarkodu.setBounds(405, 68, 114, 27);
		panel.add(txtBarkodu);
		
		String[] arr3 = {"","1.8","0.8"};
 		cmbBxKDVTipi = new JComboBox(arr3);
		cmbBxKDVTipi.setBounds(405, 110, 114, 27);
		panel.add(cmbBxKDVTipi);
		
		txtAciklama = new JTextArea();
		txtAciklama.setBounds(405, 155, 114, 27);
		panel.add(txtAciklama);
		
		btnSave_1 = new JButton("UPDATE");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				DbHelper helper = new DbHelper();
				PreparedStatement preparedStatement = null;
				try {
					connection = helper.getConnection();
					String sql = "UPDATE stok_kart set Stok_Kodu=? , Stok_Adı=? , Stok_Tipi=? , Birimi=?, Barkodu=?, KDV_Tipi=?, Açıklama=?, Oluşturma_Tarihi=? where Stok_Kodu='"+txtStokkodu.getText()+"'";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, txtStokkodu.getText());
					preparedStatement.setString(2, txtStokAdi.getText());
					preparedStatement.setObject(3, cmbBxStokTipi.getSelectedItem());
					preparedStatement.setObject(4, cmbBxBirimi.getSelectedItem());
					preparedStatement.setString(5, txtBarkodu.getText());
					preparedStatement.setObject(6, cmbBxKDVTipi.getSelectedItem());
					preparedStatement.setString(7, txtAciklama.getText());
					preparedStatement.setObject(8, dateChooser.getDate());
					int result = preparedStatement.executeUpdate();
					populateTable();
				}catch(SQLException e3){
					helper.showErrorMessage(e3);
				}

			}
		});
		btnSave_1.setForeground(new Color(0, 0, 205));
		btnSave_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSave_1.setBackground(Color.LIGHT_GRAY);
		btnSave_1.setBounds(141, 338, 123, 38);
		panel.add(btnSave_1);
		
		btnDelete_1 = new JButton("DELETE");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				DbHelper helper = new DbHelper();
				PreparedStatement preparedStatement = null;
				try {
					connection = helper.getConnection();
					String sql = "DELETE FROM stok_kart where Stok_Kodu = '"+txtStokkodu.getText()+"' ";
					preparedStatement = connection.prepareStatement(sql);
					int result = preparedStatement.executeUpdate();
					populateTable();
					JOptionPane.showMessageDialog(btnSave, "KART SİLİNDİ");
				}catch(SQLException e3){
					helper.showErrorMessage(e3);
				}
				   txtStokkodu.setText("");
				   txtStokAdi.setText("");
				   cmbBxStokTipi.setSelectedIndex(0);
				   cmbBxBirimi.setSelectedIndex(0);
	               txtBarkodu.setText("");
				   cmbBxKDVTipi.setSelectedIndex(0);
				   txtAciklama.setText("");
				   dateChooser.setDate(null);
			}
		});
		btnDelete_1.setForeground(new Color(0, 0, 205));
		btnDelete_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnDelete_1.setBackground(Color.LIGHT_GRAY);
		btnDelete_1.setBounds(319, 338, 123, 38);
		panel.add(btnDelete_1);
		
		btnNewButton_1 = new JButton("COPY");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(new Color(0, 0, 255));
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   deneme();	
			   txtCopyStokKodu.setText(lblNewLabel.getText());
			   txtCopyStokAdi.setText(lblNewLabel_1.getText());
			   txtCopyStokTipi.setText(lblNewLabel_2.getText());
			   txtCopyBirimi.setText(lblNewLabel_3.getText());
			   txtCopyBarkodu.setText(lblNewLabel_4.getText());
			   txtCopyKDVTipi.setText(lblNewLabel_5.getText());
			   txtCopyAciklama.setText(lblNewLabel_6.getText());
			   txtCopyOlusturmaTarihi.setText(lblNewLabel_7.getText());
			   
			   txtStokkodu.setText("");
			   txtStokAdi.setText("");
			   cmbBxStokTipi.setSelectedIndex(0);
			   cmbBxBirimi.setSelectedIndex(0);
               txtBarkodu.setText("");
			   cmbBxKDVTipi.setSelectedIndex(0);
			   txtAciklama.setText("");
			   dateChooser.setDate(null);
			}
		});
		btnNewButton_1.setBounds(219, 407, 123, 38);
		panel.add(btnNewButton_1);
		scrollPane.setBounds(18, 130, 541, 358);
		frame.getContentPane().add(scrollPane);
		
		tblStokKart = new JTable();
		tblStokKart.setForeground(new Color(102, 102, 102));
		tblStokKart.setBackground(new Color(152, 251, 152));
		scrollPane.setViewportView(tblStokKart);
		tblStokKart.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Stok Kodu", "Stok Ad\u0131", "Stok Tipi", "Birimi", "Barkodu", "KDV Tipi", "A\u00E7\u0131klama", "Olu\u015FturmaTarihi"
			}
		));
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(-12, -31, 67, 21);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(-12, -21, 67, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(-2, -21, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(-2, -21, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(-2, -31, 45, 21);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(-2, -31, 45, 21);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(-2, -19, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(-2, -20, 45, 21);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblKopyalananDeger = new JLabel("KOPYALANAN SATIR : ");
		lblKopyalananDeger.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKopyalananDeger.setBounds(10, 528, 170, 21);
		frame.getContentPane().add(lblKopyalananDeger);
		
		lblNewLabel_8 = new JLabel("Stok_Kodu");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBackground(new Color(240, 255, 240));
		lblNewLabel_8.setBounds(10, 559, 77, 21);
		frame.getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Stok_Adı");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBackground(new Color(240, 255, 240));
		lblNewLabel_9.setBounds(141, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Stok_Tipi");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10.setBackground(new Color(240, 255, 240));
		lblNewLabel_10.setBounds(262, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Birimi");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_11.setBackground(new Color(240, 255, 240));
		lblNewLabel_11.setBounds(394, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Barkodu");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBackground(new Color(240, 255, 240));
		lblNewLabel_12.setBounds(517, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("KDV Tipi");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13.setBackground(new Color(240, 255, 240));
		lblNewLabel_13.setBounds(639, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("Açıklama");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_14.setBackground(new Color(240, 255, 240));
		lblNewLabel_14.setBounds(764, 559, 67, 21);
		frame.getContentPane().add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("Oluşturma tarihi");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.setBackground(new Color(240, 255, 240));
		lblNewLabel_15.setBounds(882, 559, 118, 21);
		frame.getContentPane().add(lblNewLabel_15);
		
		txtCopyStokKodu = new JTextField();
		txtCopyStokKodu.setBounds(10, 590, 96, 25);
		frame.getContentPane().add(txtCopyStokKodu);
		txtCopyStokKodu.setColumns(10);
		
		txtCopyStokAdi = new JTextField();
		txtCopyStokAdi.setBounds(137, 590, 96, 25);
		frame.getContentPane().add(txtCopyStokAdi);
		txtCopyStokAdi.setColumns(10);
		
		txtCopyStokTipi = new JTextField();
		txtCopyStokTipi.setBounds(262, 590, 96, 25);
		frame.getContentPane().add(txtCopyStokTipi);
		txtCopyStokTipi.setColumns(10);
		
		txtCopyBirimi = new JTextField();
		txtCopyBirimi.setBounds(393, 590, 96, 25);
		frame.getContentPane().add(txtCopyBirimi);
		txtCopyBirimi.setColumns(10);
		
		txtCopyBarkodu = new JTextField();
		txtCopyBarkodu.setBounds(514, 590, 96, 25);
		frame.getContentPane().add(txtCopyBarkodu);
		txtCopyBarkodu.setColumns(10);
		
		txtCopyKDVTipi = new JTextField();
		txtCopyKDVTipi.setBounds(639, 590, 96, 25);
		frame.getContentPane().add(txtCopyKDVTipi);
		txtCopyKDVTipi.setColumns(10);
		
		txtCopyAciklama = new JTextField();
		txtCopyAciklama.setBounds(764, 590, 96, 25);
		frame.getContentPane().add(txtCopyAciklama);
		txtCopyAciklama.setColumns(10);
		
		txtCopyOlusturmaTarihi = new JTextField();
		txtCopyOlusturmaTarihi.setBounds(882, 590, 96, 25);
		frame.getContentPane().add(txtCopyOlusturmaTarihi);
		txtCopyOlusturmaTarihi.setColumns(10);
		
		btnNewButton_2 = new JButton("SIFIRLA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCopyStokKodu.setText("");
				txtCopyStokAdi.setText("");
				txtCopyStokTipi.setText("");
				txtCopyBirimi.setText("");
				txtCopyBarkodu.setText("");
				txtCopyKDVTipi.setText("");
				txtCopyAciklama.setText("");
				txtCopyOlusturmaTarihi.setText("");
				
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				lblNewLabel_2.setText("");
				lblNewLabel_3.setText("");
				lblNewLabel_4.setText("");
				lblNewLabel_5.setText("");
				lblNewLabel_6.setText("");
				lblNewLabel_7.setText("");
			}
		});
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setBounds(1015, 577, 123, 38);
		frame.getContentPane().add(btnNewButton_2);


		tblStokKart.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(1).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(2).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(3).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(4).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(5).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(6).setPreferredWidth(85);
		tblStokKart.getColumnModel().getColumn(7).setPreferredWidth(85);

}
}
