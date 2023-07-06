package command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import utility.DbHelper;
import view.StokKartiFrame;

public class StokKartiSelectedIdController implements KeyListener {

	private StokKartiFrame iFrame;
	public StokKartiSelectedIdController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			String id = iFrame.txtSelectedId.getText();
			
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
				
				
				iFrame.txtStokkodu.setText(stokKodu);
				iFrame.txtStokAdi.setText(stokAdi);
				iFrame.cmbBxStokTipi.setSelectedItem(stokTipi);
				iFrame.cmbBxBirimi.setSelectedItem(birimi);
				iFrame.txtBarkodu.setText(barkodu);
				iFrame.cmbBxKDVTipi.setSelectedItem(kdvTipi);
				iFrame.txtAciklama.setText(aciklama);
				//((JTextField)iFrame.dateChooser.getDateEditor().getUiComponent()).setText(olusturmaTarihi);
				
			}
			else {
				iFrame.txtStokkodu.getText();
			}
		}
		catch(SQLException e2) {
			
		}	
		
	}
	
}
