package command;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.toedter.calendar.JDateChooser;

import utility.DbHelper;
import view.StokKartiFrame;


public class StokKartiSaveController implements ActionListener{
	
	private StokKartiArrayListController iStokKartiArrayListController;
	private StokKartiFrame iFrame;
	public StokKartiSaveController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public void saveModel() {
		    iStokKartiArrayListController = new StokKartiArrayListController(iFrame);
			Connection connection = null;
			DbHelper helper = new DbHelper();
			PreparedStatement preparedStatement = null;
			try {
				connection = helper.getConnection();
				String sql = "insert into stok_kart () values(?,?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setObject(1, iFrame.txtStokkodu.getText());
				preparedStatement.setObject(2, iFrame.txtStokAdi.getText());
				preparedStatement.setObject(3, iFrame.cmbBxStokTipi.getSelectedItem());
				preparedStatement.setObject(4, iFrame.cmbBxBirimi.getSelectedItem());
				preparedStatement.setObject(5, iFrame.txtBarkodu.getText());
				preparedStatement.setObject(6, iFrame.cmbBxKDVTipi.getSelectedItem());
				preparedStatement.setObject(7, iFrame.txtAciklama.getText());
				preparedStatement.setObject(8, iFrame.dateChooser.getDate());
				int result = preparedStatement.executeUpdate();
				iStokKartiArrayListController.populateTable();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		saveModel();		
	}
}


