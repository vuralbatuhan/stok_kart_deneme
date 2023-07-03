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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.PublicKey;

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
	private JComboBox cmbBxStokTipi;
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
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JTextField textField;
	
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
	
	public void  ShowItem(int index) 
	{
		try {
			txtStokkodu.setText(getStokKarts().get(index).getStokKodu());
			txtStokAdi.setText(getStokKarts().get(index).getStokAdi());
			txtAciklama.setText(getStokKarts().get(index).getAciklama());
			txtBarkodu.setText(getStokKarts().get(index).getBarkodu());
			cmbBxBirimi.setSelectedItem(getStokKarts().get(index).getBirimi());
			cmbBxKDVTipi.setSelectedItem(Double.toString(getStokKarts().get(index).getKdvTipi()));
			cmbBxStokTipi.setSelectedItem(getStokKarts().get(index).getStokTipi());
		} catch (SQLException e) {
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
		txtSearch.setBounds(157, 70, 418, 33);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search : ");
		lblSearch.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblSearch.setBounds(85, 68, 62, 33);
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
		
//		String[] arr = {"","1","2","3"};
//		JComboBox cmbBxStokTipi = new JComboBox(arr);
//		cmbBxStokTipi.setBounds(141, 155, 114, 27);
//		panel.add(cmbBxStokTipi);
//		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd ");
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		String[] arr = {"","1","2","3"};
		cmbBxStokTipi = new JComboBox(arr);
		cmbBxStokTipi.setBounds(141, 155, 114, 27);
		panel.add(cmbBxStokTipi);
		
		
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
					String id = textField.getText();
					connection = helper.getConnection();
					String sql = "UPDATE stok_kart set Stok_Kodu=? , Stok_Adı=? , Stok_Tipi=? , Birimi=?, Barkodu=?, KDV_Tipi=?, Açıklama=?, Oluşturma_Tarihi=? where Stok_Kodu='"+id+"'";
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
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {
					String id = textField.getText();
					
					Connection connection = null;
					DbHelper helper = new DbHelper();
					PreparedStatement preparedStatement = null;
					connection = helper.getConnection();
					preparedStatement = connection.prepareStatement("select Stok_Kodu, Stok_Adı, Stok_Tipi, Birimi, Barkodu, KDV_Tipi, Açıklama, Oluşturma_tarihi  from stok_kart where Stok_Kodu = ?");
					preparedStatement.setString(1, id);
					ResultSet resultSet = preparedStatement.executeQuery();
					
					if(resultSet.next()==true) {
						String stokKodu = resultSet.getString(1);
						String stokAdi = resultSet.getString(2);
						String stokTipi = resultSet.getString(3);
						String birimi = resultSet.getString(4);
						String barkodu = resultSet.getString(5);
						String kdvTipi = resultSet.getString(6);
						String aciklama = resultSet.getString(7);
						String olusturmaTarihi = resultSet.getString(8);
						
						
						txtStokkodu.setText(stokKodu);
						txtStokAdi.setText(stokAdi);
						cmbBxStokTipi.setSelectedItem(stokTipi);
						cmbBxBirimi.setSelectedItem(birimi);
						txtBarkodu.setText(barkodu);
						cmbBxKDVTipi.setSelectedItem(kdvTipi);
						txtAciklama.setText(aciklama);
						((JTextField)dateChooser.getDateEditor().getUiComponent()).setText(olusturmaTarihi);
						
					}
					else {
						txtStokkodu.getText();
					}
				}
				catch(SQLException e2) {
					
				}	
			}
		});
		textField.setColumns(10);
		textField.setBounds(157, 28, 114, 27);
		frame.getContentPane().add(textField);
		
		btnDelete_1 = new JButton("DELETE");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				DbHelper helper = new DbHelper();
				PreparedStatement preparedStatement = null;
				try {
					String id = textField.getText();
					connection = helper.getConnection();
					String sql = "DELETE FROM stok_kart where Stok_Kodu = '"+id+"' ";
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
		
		JButton btnCopyy = new JButton("COPY");
		btnCopyy.setBackground(new Color(192, 192, 192));
		btnCopyy.setForeground(new Color(0, 0, 255));
		btnCopyy.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCopyy.setBounds(219, 386, 123, 38);
		panel.add(btnCopyy);
		btnCopyy.addActionListener(new ActionListener() {
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
		scrollPane.setBounds(18, 130, 541, 358);
		frame.getContentPane().add(scrollPane);
		
		tblStokKart = new JTable();
		tblStokKart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblStokKart.getSelectedRow();
				ShowItem(index);
			}
		});

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
		
		JLabel lblNewLabel = new JLabel("Selected Id : ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(54, 33, 93, 13);
		frame.getContentPane().add(lblNewLabel);
		

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
