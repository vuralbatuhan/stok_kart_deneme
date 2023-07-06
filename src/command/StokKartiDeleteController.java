package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DbHelper;
import view.StokKartiFrame;

public class StokKartiDeleteController implements ActionListener {

	private StokKartiArrayListController iStokKartiArrayListController;
	private StokKartiFrame iFrame;
	public StokKartiDeleteController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public void deleteModel() {
		iStokKartiArrayListController = new StokKartiArrayListController(iFrame);
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
				
				String sql2 = "delete from stok_kart where Stok_Kodu= '"+iFrame.txtStokkodu.getText()+"'";
				preparedStatement = connection.prepareStatement(sql2);
				int result = preparedStatement.executeUpdate();
				iStokKartiArrayListController.populateTable();
				
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
		deleteModel();		
	}
	
}
