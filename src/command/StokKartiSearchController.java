package command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.StokKartiFrame;

public class StokKartiSearchController implements KeyListener {

	private StokKartiFrame iFrame;
	public StokKartiSearchController(StokKartiFrame iFrame) {
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
		String searchkeyString = iFrame.txtSearch.getText();
		TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(iFrame.model);
		iFrame.tblStokKart.setRowSorter(tableRowSorter);
		tableRowSorter.setRowFilter(RowFilter.regexFilter(searchkeyString));
	}

}
