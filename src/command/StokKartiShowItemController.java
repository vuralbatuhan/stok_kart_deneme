package command;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import view.StokKartiFrame;

public class StokKartiShowItemController implements MouseListener {

	private StokKartiArrayListController iStokKartiArrayListController;
	private StokKartiFrame iFrame;
	public StokKartiShowItemController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public void  ShowItem(int index) 
	{
		iStokKartiArrayListController = new StokKartiArrayListController(iFrame);
		try {
			iFrame.txtStokkodu.setText(iStokKartiArrayListController.getStokKarts().get(index).getStokKodu());
			iFrame.txtStokAdi.setText(iStokKartiArrayListController.getStokKarts().get(index).getStokAdi());
			iFrame.txtAciklama.setText(iStokKartiArrayListController.getStokKarts().get(index).getAciklama());
			iFrame.txtBarkodu.setText(iStokKartiArrayListController.getStokKarts().get(index).getBarkodu());
			iFrame.cmbBxBirimi.setSelectedItem(iStokKartiArrayListController.getStokKarts().get(index).getBirimi());
			iFrame.cmbBxKDVTipi.setSelectedItem(Double.toString(iStokKartiArrayListController.getStokKarts().get(index).getKdvTipi()));
			iFrame.cmbBxStokTipi.setSelectedItem(iStokKartiArrayListController.getStokKarts().get(index).getStokTipi());
			iFrame.dateChooser.setDate(iStokKartiArrayListController.getStokKarts().get(index).getOlusturmaTarihi());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int index = iFrame.tblStokKart.getSelectedRow();
		ShowItem(index);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	;
}
