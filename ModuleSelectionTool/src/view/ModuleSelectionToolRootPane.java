package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;


public class ModuleSelectionToolRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private SelectStudentModulePane ssmp;
	private ReserveStudentModulePane rsmp;
	private OverviewStudentSelectionPane ossp;
	private ModuleSelectionToolMenuBar mstmb;
	private TabPane tp;
	
	public ModuleSelectionToolRootPane() {
		
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		ssmp = new SelectStudentModulePane();
		rsmp = new ReserveStudentModulePane();
		ossp = new OverviewStudentSelectionPane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Module", ssmp);
		Tab t3 = new Tab("Reserve Module", rsmp);
		Tab t4 = new Tab("Overview Selection", ossp);
		
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);
		
		//create menu bar
		mstmb = new ModuleSelectionToolMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		
		return cspp;
	}
	
	public ModuleSelectionToolMenuBar getModuleSelectionToolMenuBar() {
	
		return mstmb;
	}
	
	public SelectStudentModulePane getSelectStudentModulePane() {
		
		return ssmp;
	}
	
	public ReserveStudentModulePane getReserveStudentModulePane() {
		
		return rsmp;
	}
	
	public OverviewStudentSelectionPane getOverviewStudentSelectionPane() {
		
		return ossp;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		
		tp.getSelectionModel().select(index);
	}
}
