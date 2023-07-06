package command;


import java.awt.event.ActionListener;

import view.StokKartiFrame;

public class StokKartiFrameController {
	private StokKartiFrame iFrame;
	private StokKartiArrayListController iStokKartiArrayListController;
	public StokKartiFrameController() {
		Init();
	}

	private void Init() {
		iFrame = new StokKartiFrame();
		iStokKartiArrayListController = new StokKartiArrayListController(iFrame);
		setListeners();
		iStokKartiArrayListController.populateTable();
		iFrame.setVisible(true);		
	}
	
	private void setListeners() {
		iFrame.btnSave.addActionListener(new StokKartiSaveController(iFrame));
		iFrame.btnDelete.addActionListener(new StokKartiDeleteController(iFrame));
		iFrame.btnUpdate.addActionListener(new StokKartiUpdateController(iFrame));
		iFrame.btnCopy.addActionListener(new StokKartiCopyController(iFrame));
		iFrame.tblStokKart.addMouseListener(new StokKartiShowItemController(iFrame));
		iFrame.txtSelectedId.addKeyListener(new StokKartiSelectedIdController(iFrame));
		iFrame.txtSearch.addKeyListener(new StokKartiSearchController(iFrame));
	}
	
}
