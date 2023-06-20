package view;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;
import model.Schedule;

public class ReserveStudentModulePane extends Accordion {

	private ListView<Module> lvUnselectedReserveModuleTerm1, lvUnselectedReserveModuleTerm2, lvSelectedReserveModuleTerm1, lvSelectedReserveModuleTerm2;
	private ObservableList<Module> olUnselectedReserveModuleTerm1, olUnselectedReserveModuleTerm2, olSelectedReserveModuleTerm1, olSelectedReserveModuleTerm2;
	private Button btTerm1Add, btTerm2Add, btTerm1Remove, btTerm2Remove, btTerm1Confirm, btTerm2Confirm;
	
	public ReserveStudentModulePane() {
		
		//styling
		this.setPadding(new Insets(20));
		
		//initializing observable lists
		olUnselectedReserveModuleTerm1 = FXCollections.observableArrayList(); 
		olSelectedReserveModuleTerm1 = FXCollections.observableArrayList(); 
		olUnselectedReserveModuleTerm2 = FXCollections.observableArrayList();
		olSelectedReserveModuleTerm2 = FXCollections.observableArrayList();
		
		//initializing list views
		lvUnselectedReserveModuleTerm1 = new ListView<>(olUnselectedReserveModuleTerm1);
		lvUnselectedReserveModuleTerm2 = new ListView<>(olUnselectedReserveModuleTerm2);
		lvSelectedReserveModuleTerm1 = new ListView<>(olSelectedReserveModuleTerm1);
		lvSelectedReserveModuleTerm2 = new ListView<>(olSelectedReserveModuleTerm2);
		
		//styling list views
		lvUnselectedReserveModuleTerm1.setPrefSize(300, 300);
		lvSelectedReserveModuleTerm1.setPrefSize(300, 300);
		lvUnselectedReserveModuleTerm2.setPrefSize(300, 300);
		lvSelectedReserveModuleTerm2.setPrefSize(300, 300);
		
		//initializing labels
		Label rTerm1 = new Label("Reserve 30 credits worth of term 1 modules");
		Label rTerm2 = new Label("Reserve 30 credits worth of term 2 modules");
		Label unselectedTerm1 = new Label("Unselected Term 1 modules");
		Label unselectedTerm2 = new Label("Unselected Term 2 modules");
		Label selectedTerm1 = new Label("Selected Term 1 modules");
		Label selectedTerm2 = new Label("Selected Term 2 modules");
		
		//initializing buttons
		btTerm1Add = new Button("Add");
		btTerm1Add.setPrefSize(80, 10);
		
		btTerm2Add = new Button("Add");
		btTerm2Add.setPrefSize(80, 10);
		
		btTerm1Remove = new Button("Remove");
		btTerm1Remove.setPrefSize(80, 10);
		
		btTerm2Remove = new Button("Remove");
		btTerm2Remove.setPrefSize(80, 10);
		
		btTerm1Confirm = new Button("Confirm");
		btTerm1Confirm.setPrefSize(80, 10);
		
		btTerm2Confirm = new Button("Confirm");
		btTerm2Confirm.setPrefSize(80, 10);
		
		//VBox for unselected term 1 list view with label
		VBox ust1 = new VBox();
		ust1.getChildren().addAll(unselectedTerm1, lvUnselectedReserveModuleTerm1);
		VBox.setVgrow(lvUnselectedReserveModuleTerm1, Priority.ALWAYS);
		
		//VBox for selected term 1 list view with label
		VBox st1 = new VBox();
		st1.getChildren().addAll(selectedTerm1, lvSelectedReserveModuleTerm1);
		VBox.setVgrow(lvSelectedReserveModuleTerm1, Priority.ALWAYS);
		
		//HBox for term 1 list views with labels
		HBox lvTerm1 = new HBox(20);
		lvTerm1.setAlignment(Pos.CENTER);
		lvTerm1.getChildren().addAll(ust1, st1);
		HBox.setHgrow(ust1, Priority.ALWAYS);
		HBox.setHgrow(st1, Priority.ALWAYS);
		
		//HBox for term 1 label with buttons
		HBox term1 = new HBox(20);
		term1.setAlignment(Pos.CENTER);
		term1.getChildren().addAll(rTerm1, btTerm1Add, btTerm1Remove, btTerm1Confirm);
		
		//container for term 1
		VBox containerTerm1 = new VBox(20);
		containerTerm1.setAlignment(Pos.CENTER);
		containerTerm1.setPadding(new Insets(40));
		containerTerm1.getChildren().addAll(lvTerm1, term1);
		VBox.setVgrow(lvTerm1, Priority.ALWAYS);
		
		//VBox for unselected term 2 list view with label
		VBox ust2 = new VBox();
		ust2.getChildren().addAll(unselectedTerm2, lvUnselectedReserveModuleTerm2);
		VBox.setVgrow(lvUnselectedReserveModuleTerm2, Priority.ALWAYS);
		
		//VBox for selected term 2 list view with label
		VBox st2 = new VBox();
		st2.getChildren().addAll(selectedTerm2, lvSelectedReserveModuleTerm2);
		VBox.setVgrow(lvSelectedReserveModuleTerm2, Priority.ALWAYS);
		
		//HBox for term 2 label with buttons
		HBox term2 = new HBox(20);
		term2.setAlignment(Pos.CENTER);
		term2.getChildren().addAll(rTerm2, btTerm2Add, btTerm2Remove, btTerm2Confirm);
		
		//HBox for term 2 list views with labels
		HBox lvTerm2 = new HBox(20);
		lvTerm2.setAlignment(Pos.CENTER);
		lvTerm2.getChildren().addAll(ust2, st2);
		HBox.setHgrow(ust2, Priority.ALWAYS);
		HBox.setHgrow(st2, Priority.ALWAYS);
		
		//container for term 2
		VBox containerTerm2 = new VBox(20);
		containerTerm2.setAlignment(Pos.CENTER);
		containerTerm2.setPadding(new Insets(40));
		containerTerm2.getChildren().addAll(lvTerm2, term2);
		VBox.setVgrow(lvTerm2, Priority.ALWAYS);
		
		//initializing titled panes with respective containers
		TitledPane tpTerm1 = new TitledPane("Term 1", containerTerm1);
		TitledPane tpTerm2 = new TitledPane("Term 2", containerTerm2);
		
		this.setExpandedPane(tpTerm1);
		this.getPanes().addAll(tpTerm1, tpTerm2);
	}
	
	//method to attach the add buttons event handler 
	public void addAddHandler(EventHandler<ActionEvent> handler) {
		
		btTerm1Add.setOnAction(handler);
		btTerm2Add.setOnAction(handler);
	}
	
	//method to attach the remove buttons event handler
	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		
		btTerm1Remove.setOnAction(handler);
		btTerm2Remove.setOnAction(handler);
	}
	
	//method to attach the confirm buttons event handler
	public void addConfirmHandler(EventHandler<ActionEvent> handler) {
		
		btTerm1Confirm.setOnAction(handler);
		btTerm2Confirm.setOnAction(handler);
	}
	
	//methods to get the selection from the list views
	public ObservableList<Module> getUnselectedReserveModuleTerm1() {
		
		return lvUnselectedReserveModuleTerm1.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getUnselectedReserveModuleTerm2() {
		
		return lvUnselectedReserveModuleTerm2.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getSelectedReserveModuleTerm1() {
		
		return lvSelectedReserveModuleTerm1.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getSelectedReserveModuleTerm2() {
		
		return lvSelectedReserveModuleTerm2.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getAllSelectedReserveModuleTerm1() {
		
		return olSelectedReserveModuleTerm1;
	}
	
	public ObservableList<Module> getAllSelectedReserveModuleTerm2() {
		
		return olSelectedReserveModuleTerm2;
	}
	
	//methods to populate reserved modules pane
	public void addTerm1Modules(ObservableList<Module> ol) {
		
		olUnselectedReserveModuleTerm1.addAll(ol);
		lvUnselectedReserveModuleTerm1.setItems(olUnselectedReserveModuleTerm1);
	}
	
	public void addTerm2Modules(ObservableList<Module> ol) {
		
		olUnselectedReserveModuleTerm2.addAll(ol);
		lvUnselectedReserveModuleTerm2.setItems(olUnselectedReserveModuleTerm2);
	}
	
	//method to add reserved term 1 modules
	public void addReserveTerm1Modules(ObservableList<Module> ol) {
		
		int credits = 0;
	
		for(Module m : olSelectedReserveModuleTerm1) {
			credits += m.getModuleCredits();
		}
		
		if(credits >= 30) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Maximum credit limit reached.");
			alert.showAndWait();
			
		} else {
			
			olSelectedReserveModuleTerm1.addAll(ol);
			olUnselectedReserveModuleTerm1.removeAll(ol);
			lvSelectedReserveModuleTerm1.setItems(olSelectedReserveModuleTerm1);
			lvUnselectedReserveModuleTerm1.setItems(olUnselectedReserveModuleTerm1);	
		}
	}

	//method to add reserved term 2 modules
	public void addReserveTerm2Modules(ObservableList<Module> ol) {
	
		int credits = 0;
		
		for(Module m : olSelectedReserveModuleTerm2) {
			credits += m.getModuleCredits();
		}
		
		if(credits >= 30) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Maximum credit limit reached.");
			alert.showAndWait();
			
		} else {
		
			olSelectedReserveModuleTerm2.addAll(ol);
			olUnselectedReserveModuleTerm2.removeAll(ol);
			lvSelectedReserveModuleTerm2.setItems(olSelectedReserveModuleTerm2);
			lvUnselectedReserveModuleTerm2.setItems(olUnselectedReserveModuleTerm2);
		}
	}
	
	//method to remove reserved term 1 modules
	public void removeReserveTerm1Modules(ObservableList<Module> ol) {
		
		int credits = 0;
		
		for(Module m : olSelectedReserveModuleTerm1) {
			credits += m.getModuleCredits();
		}
		
		if(credits > 0) {
			olUnselectedReserveModuleTerm1.addAll(ol);
			olSelectedReserveModuleTerm1.removeAll(ol);
			lvUnselectedReserveModuleTerm1.setItems(olUnselectedReserveModuleTerm1);
			lvSelectedReserveModuleTerm1.setItems(olSelectedReserveModuleTerm1);
		}
	}

	//method to remove reserved term 2 modules
	public void removeReserveTerm2Modules(ObservableList<Module> ol) {
		
		int credits = 0;
		
		for(Module m : olSelectedReserveModuleTerm2) {
			credits += m.getModuleCredits();
		}
		
		if(credits > 0) {
			olUnselectedReserveModuleTerm2.addAll(ol);
			olSelectedReserveModuleTerm2.removeAll(ol);
			lvUnselectedReserveModuleTerm2.setItems(olUnselectedReserveModuleTerm2);
			lvSelectedReserveModuleTerm2.setItems(olSelectedReserveModuleTerm2);
		}
	}
	
	//method to load GUI using load profile
	public void setReservedModules(Set<Module> reserve, ObservableList<Module> unselected) {
		
		olSelectedReserveModuleTerm1.clear();
		olSelectedReserveModuleTerm2.clear();
		olUnselectedReserveModuleTerm1.clear();
		olUnselectedReserveModuleTerm2.clear();
		
		reserve.forEach((module) -> {
			
			if(module.getDelivery().equals(Schedule.TERM_1)) {
				
				olSelectedReserveModuleTerm1.add(module);
				unselected.remove(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2)) {
				
				olSelectedReserveModuleTerm2.add(module);
				unselected.remove(module);
			}
		});
		
		unselected.forEach((module) -> {
			
			if(module.getDelivery().equals(Schedule.TERM_1)) {
				
				olUnselectedReserveModuleTerm1.add(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2)) {
				
				olUnselectedReserveModuleTerm2.add(module);
			}
		});
		
		lvSelectedReserveModuleTerm1.setItems(olSelectedReserveModuleTerm1);
		lvSelectedReserveModuleTerm2.setItems(olSelectedReserveModuleTerm2);
		lvUnselectedReserveModuleTerm1.setItems(olUnselectedReserveModuleTerm1);
		lvUnselectedReserveModuleTerm2.setItems(olUnselectedReserveModuleTerm2);
	}
	
	//methods to get button objects
	public Button getAdd1() {
		
		return btTerm1Add;
	}
	
	public Button getAdd2() {
		
		return btTerm2Add;
	}
	
	public Button getRemove1() {
		
		return btTerm1Remove;
	}
	
	public Button getRemove2() {
		
		return btTerm2Remove;
	}
	
	public Button getConfirm1() {
		
		return btTerm1Confirm;
	}
	
	public Button getConfirm2() {
		
		return btTerm2Confirm;
	}
	
	//method to to allow the controller to change pane
	public void changePane(int index) {
		
		this.setExpandedPane(this.getPanes().get(index));
	}
}
