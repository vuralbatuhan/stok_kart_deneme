package view;

import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.MenuSelectionManager;
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
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;

import model.StokKart;
import utility.DbHelper;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.PublicKey;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuBar;

public class StokKartiFrame extends JFrame {
	public static final long serialVersionUID = 1L;
	public DefaultTableModel model;
	public JTable tblStokKart;
	public JTextField txtSearch;
	public JTextField txtStokkodu;
	public JButton btnSave;
	public JLabel lblStokKodu;
	public JLabel lblStokAdi;
	public JLabel lblStokTipi;
	public JLabel lblBirimi;
	public JTextField txtStokAdi;
	public JComboBox cmbBxBirimi;
	public JComboBox cmbBxStokTipi;
	public JLabel lblBarkodu;
	public JLabel lblKDVTipi;
	public JLabel lblAciklama;
	public JLabel lblOlusturmaTarihi;
	public JTextField txtBarkodu;
	public JComboBox cmbBxKDVTipi;
	public JTextArea txtAciklama;
	public JButton btnUpdate;
	public JButton btnDelete;
	public final JScrollPane scrollPane = new JScrollPane();
	public JButton btnCopy;
	public JTextField txtSelectedId;
	public JTextField textField_1;
	public JDateChooser dateChooser;
	public JPanel panel;


	public StokKartiFrame() {
		initialize();	
	}

	private void initialize(){
		getContentPane().setBackground(new Color(192, 192, 192));
		setBounds(100, 100, 1303, 726);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(157, 70, 418, 33);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search : ");
		lblSearch.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblSearch.setBounds(85, 68, 62, 33);
		getContentPane().add(lblSearch);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setForeground(SystemColor.controlDkShadow);
		panel.setBounds(597, 76, 541, 458);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtStokkodu = new JTextField();
		txtStokkodu.setBounds(141, 68, 114, 27);
		panel.add(txtStokkodu);
		txtStokkodu.setColumns(10);
	
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd ");
		dateChooser.setBounds(405, 199, 114, 19);
		panel.add(dateChooser);
		
		btnSave = new JButton("SAVE");
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
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(0, 0, 205));
		btnUpdate.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setBounds(141, 338, 123, 38);
		panel.add(btnUpdate);
		
		txtSelectedId = new JTextField();
		txtSelectedId.setColumns(10);
		txtSelectedId.setBounds(157, 28, 114, 27);
		getContentPane().add(txtSelectedId);
		
		btnDelete = new JButton("DELETE");	
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBounds(319, 338, 123, 38);
		panel.add(btnDelete);
		scrollPane.setBounds(18, 130, 541, 358);
		getContentPane().add(scrollPane);
		
		
		txtSelectedId.setText(txtStokkodu.getText());
		btnCopy = new JButton("Copy");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(120, 510, 339, 42);
		getContentPane().add(menuBar);
		
		JLabel lblNewLabel_1 = new JLabel("Yeni Stok Kodunu Giriniz");
		lblNewLabel_1.setBackground(new Color(192, 192, 192));
		menuBar.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		menuBar.add(textField_1);
		textField_1.setColumns(10);
		menuBar.add(btnCopy);
		
		
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
		
		JLabel lblNewLabel = new JLabel("Selected Id : ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(54, 33, 93, 13);
		getContentPane().add(lblNewLabel);
		
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