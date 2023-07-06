package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DbHelper;
import view.StokKartiFrame;

public class StokKartiUpdateController implements ActionListener {

	private StokKartiFrame iFrame;
	public StokKartiUpdateController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public void updateModel() {
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preparedStatement = null;
		
		try {
			connection = helper.getConnection();
			String sql = "select * from stok_kart where Stok_Kodu= '"+iFrame.txtStokkodu.getText()+"'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==true) 
			{
				
				String stokKodu = resultSet.getString(1);
				String stokAdi = resultSet.getString(2);
				String stokTipi = resultSet.getString(3);
				String birimi = resultSet.getString(4);
				String barkodu = resultSet.getString(5);
				String kdvTipi = resultSet.getString(6);
				String aciklama = resultSet.getString(7);
				String olusturmaTarihi = resultSet.getString(8);
				
				String sql2 = "update stok_kart set Stok_Kodu=? , Stok_Adı=? , Stok_Tipi=? , Birimi=?, Barkodu=?, KDV_Tipi=?, Açıklama=?, Oluşturma_Tarihi=? where Stok_Kodu= '"+iFrame.txtStokkodu.getText()+"'";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setString(1, iFrame.txtStokkodu.getText());
				preparedStatement.setString(2, iFrame.txtStokAdi.getText());
				preparedStatement.setObject(3, iFrame.cmbBxStokTipi.getSelectedItem());
				preparedStatement.setObject(4, iFrame.cmbBxBirimi.getSelectedItem());
				preparedStatement.setString(5, iFrame.txtBarkodu.getText());
				preparedStatement.setObject(6, iFrame.cmbBxKDVTipi.getSelectedItem());
				preparedStatement.setString(7, iFrame.txtAciklama.getText());
				preparedStatement.setObject(8, iFrame.dateChooser.getDate());
				int result = preparedStatement.executeUpdate();
				iFrame.populateTable();
				

						
			}
			}
				
			catch(SQLException e3){
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
		
		updateModel();
	}

}
