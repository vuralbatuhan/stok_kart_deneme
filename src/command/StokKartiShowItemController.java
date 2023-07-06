package command;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.StokKartiFrame;

public class StokKartiShowItemController implements MouseListener {

	private StokKartiFrame iFrame;
	public StokKartiShowItemController(StokKartiFrame iFrame) {
		this.iFrame = iFrame;
	}
	
	public void  ShowItem(int index) 
	{
		try {
			iFrame.txtStokkodu.setText(iFrame.getStokKarts().get(index).getStokKodu());
			iFrame.txtStokAdi.setText(iFrame.getStokKarts().get(index).getStokAdi());
			iFrame.txtAciklama.setText(iFrame.getStokKarts().get(index).getAciklama());
			iFrame.txtBarkodu.setText(iFrame.getStokKarts().get(index).getBarkodu());
			iFrame.cmbBxBirimi.setSelectedItem(iFrame.getStokKarts().get(index).getBirimi());
			iFrame.cmbBxKDVTipi.setSelectedItem(Double.toString(iFrame.getStokKarts().get(index).getKdvTipi()));
			iFrame.cmbBxStokTipi.setSelectedItem(iFrame.getStokKarts().get(index).getStokTipi());
		} catch (Exception e) {
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
