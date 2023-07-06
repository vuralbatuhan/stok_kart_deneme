package command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.StokKart;
import utility.DbHelper;
import view.StokKartiFrame;

public class StokKartiArrayListController {
	
	StokKartiFrame iFrame;
	public StokKartiArrayListController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public ArrayList<StokKart> getStokKarts() {
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
			while(resultSet.next()==true) {
				stokKarts.add(new StokKart(resultSet.getString("Stok_Kodu"), resultSet.getString("Stok_Adı"), resultSet.getString("Stok_Tipi"), resultSet.getString("Birimi"), resultSet.getString("Barkodu"), resultSet.getDouble("KDV_Tipi"), resultSet.getString("Açıklama"), resultSet.getDate("Oluşturma_Tarihi")));
			}
		}catch(SQLException e){
			helper.showErrorMessage(e);		
		}
		finally{
		}
		return stokKarts;
	}
	
	public void populateTable() {
		iFrame.model = (DefaultTableModel)iFrame.tblStokKart.getModel();
		iFrame.model.setRowCount(0);
		try {
			ArrayList<StokKart> stokKarts = getStokKarts();
			for(StokKart stokKart : stokKarts) {
				Object[] rowObjects = {stokKart.getStokKodu(), stokKart.getStokAdi(), stokKart.getStokTipi(), stokKart.getBirimi(), stokKart.getBarkodu(), stokKart.getKdvTipi(), stokKart.getAciklama(), stokKart.getOlusturmaTarihi()};
			    iFrame.model.addRow(rowObjects);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
