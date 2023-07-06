package command;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPopupMenu;

import utility.DbHelper;
import view.StokKartiFrame;

public class StokKartiCopyController implements ActionListener {

	private StokKartiArrayListController iStokKartiArrayListController;
	private StokKartiFrame iFrame;
    public StokKartiCopyController(StokKartiFrame iFrame) {
    	this.iFrame = iFrame;
    }
    
    public void copyModel() {
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
				
				String stokKodu = resultSet.getString(1);
				String stokAdi = resultSet.getString(2);
				String stokTipi = resultSet.getString(3);
				String birimi = resultSet.getString(4);
				String barkodu = resultSet.getString(5);
				String kdvTipi = resultSet.getString(6);
				String aciklama = resultSet.getString(7);
				String olusturmaTarihi = resultSet.getString(8);
				
				String sql2 = "insert into stok_kart () values('"+iFrame.textField_1.getText()+"',?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql2);
				//preparedStatement.setString(1, id2);
				preparedStatement.setString(1, stokAdi);
				preparedStatement.setObject(2, stokTipi);
				preparedStatement.setObject(3, birimi);
				preparedStatement.setString(4, barkodu);
				preparedStatement.setObject(5, kdvTipi);
				preparedStatement.setString(6, aciklama);
				preparedStatement.setObject(7, olusturmaTarihi);
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
		// TODO Auto-generated method stub
	    copyModel();	
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}


