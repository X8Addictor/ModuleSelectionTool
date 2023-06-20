package view;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.Schedule;

public class SelectStudentModulePane extends BorderPane {
	
	
	private ListView<Module> lvUnselectedTerm1, lvUnselectedTerm2, lvSelectedYearLong, lvSelectedTerm1, lvSelectedTerm2;
	private ObservableList<Module> olModules, olUnselectedTerm1, olUnselectedTerm2, olSelectedYearLong, olSelectedTerm1, olSelectedTerm2;
	private Button addTerm1, removeTerm1, addTerm2, removeTerm2, reset, submit;
	private TextField tfTerm1, tfTerm2;
	private int creditsTerm1, creditsTerm2;
	
	public SelectStudentModulePane() {
		
		//styling
		this.setPadding(new Insets(20));
		
		//initializing labels
		Label lblUnselectedTerm1 = new Label("Unselected Term 1 modules");
		Label lblTerm1 = new Label("Term 1");
		Label lblSelectedTerm1 = new Label("Selected Term 1 modules");
		Label lblTerm1Credits = new Label("Current term 1 credits: ");
		Label lblUnselectedTerm2 = new Label("Unselected Term 2 modules");
		Label lblTerm2 = new Label("Term 2");
		Label lblSelectedTerm2 = new Label("Selected Term 2 modules");
		Label lblTerm2Credits = new Label("Current term 2 credits: ");
		Label lblSelectedYearLong = new Label("Selected Year Long modules");
		
		//initializing buttons
		addTerm1 = new Button("Add");
		addTerm1.setPrefSize(70, 20);
		
		removeTerm1 = new Button("Remove");
		removeTerm1.setPrefSize(70, 20);
		
		addTerm2 = new Button("Add");
		addTerm2.setPrefSize(70, 20);
		
		removeTerm2 = new Button("Remove");
		removeTerm2.setPrefSize(70, 20);
		
		reset = new Button("Reset");
		reset.setPrefSize(70, 20);
		
		submit = new Button("Submit");
		submit.setPrefSize(70, 20);
		
		//initializing observable lists
		olUnselectedTerm1 = FXCollections.observableArrayList();
		olUnselectedTerm2 = FXCollections.observableArrayList();
		olSelectedYearLong = FXCollections.observableArrayList();
		olSelectedTerm1 = FXCollections.observableArrayList();
		olSelectedTerm2 = FXCollections.observableArrayList();
		
		//Initializing list views
		lvUnselectedTerm1 = new ListView<>(olUnselectedTerm1);
		lvUnselectedTerm2 = new ListView<>(olUnselectedTerm2);
		lvSelectedYearLong = new ListView<>(olSelectedYearLong);
		lvSelectedTerm1 = new ListView<>(olSelectedTerm1);
		lvSelectedTerm2 = new ListView<>(olSelectedTerm2);
		
		//VBox for unselected term 1 modules list view with label
		lvUnselectedTerm1.setMinSize(350, 80);
		lvUnselectedTerm1.setPrefSize(350, 80);
		lvUnselectedTerm1.getSelectionModel().select(0);
		lvUnselectedTerm1.scrollTo(0);
				
		VBox unselectedTerm1 = new VBox();
		unselectedTerm1.getChildren().addAll(lblUnselectedTerm1, lvUnselectedTerm1);
		VBox.setVgrow(lvUnselectedTerm1, Priority.ALWAYS);
		
		//HBox for list view and buttons
		HBox term1 = new HBox(20);
		term1.getChildren().addAll(lblTerm1, addTerm1, removeTerm1);
		term1.setAlignment(Pos.CENTER);
		
		//VBox for unselected term 2 modules list view with label
		lvUnselectedTerm2.setPrefSize(350, 80);
		lvUnselectedTerm2.setMinSize(350, 80);
		lvUnselectedTerm2.getSelectionModel().select(0);
		lvUnselectedTerm2.scrollTo(0);
		
		VBox unselectedTerm2 = new VBox();
		unselectedTerm2.getChildren().addAll(lblUnselectedTerm2, lvUnselectedTerm2);
		VBox.setVgrow(lvUnselectedTerm2, Priority.ALWAYS);
		
		//HBox for list view and buttons
		HBox term2 = new HBox(20);
		term2.getChildren().addAll(lblTerm2, addTerm2, removeTerm2);
		term2.setAlignment(Pos.CENTER);
		
		//VBox for all unselected list views with buttons
		VBox containerLeft = new VBox(20);
		containerLeft.getChildren().addAll(unselectedTerm1, term1, unselectedTerm2, term2);
		VBox.setVgrow(unselectedTerm1, Priority.ALWAYS);
		VBox.setVgrow(unselectedTerm2, Priority.ALWAYS);
		
		//VBox for selected year long modules with label		
		lvSelectedYearLong.setPrefSize(350, 60);
		lvSelectedYearLong.setMaxHeight(40);
		lvSelectedYearLong.getSelectionModel().select(0);
		lvSelectedYearLong.scrollTo(0);
		
		VBox selectedYearLong = new VBox();
		selectedYearLong.getChildren().addAll(lblSelectedYearLong, lvSelectedYearLong);
		selectedYearLong.setMaxHeight(70);
		
		//VBox for selected term 1 modules with label
		lvSelectedTerm1.setPrefSize(350, 70);
		lvSelectedTerm1.getSelectionModel().select(0);
		lvSelectedTerm1.scrollTo(0);
		
		VBox selectedTerm1 = new VBox();
		selectedTerm1.getChildren().addAll(lblSelectedTerm1, lvSelectedTerm1);
		selectedTerm1.setAlignment(Pos.TOP_LEFT);
		VBox.setVgrow(lvSelectedTerm1, Priority.ALWAYS);
		
		//VBox for selected term 2 modules with label
		lvSelectedTerm2.setPrefSize(200, 70);
		lvSelectedTerm2.getSelectionModel().select(0);
		lvSelectedTerm2.scrollTo(0);
		
		VBox selectedTerm2 = new VBox();
		selectedTerm2.getChildren().addAll(lblSelectedTerm2, lvSelectedTerm2);
		selectedTerm2.setAlignment(Pos.TOP_LEFT);
		VBox.setVgrow(lvSelectedTerm2, Priority.ALWAYS);
		
		//VBox for all selected list views with labels
		VBox containerRight = new VBox(20);
		containerRight.getChildren().addAll(selectedYearLong, selectedTerm1, selectedTerm2);
		containerRight.setAlignment(Pos.TOP_LEFT);
		VBox.setVgrow(selectedTerm1, Priority.ALWAYS);
		VBox.setVgrow(selectedTerm2, Priority.ALWAYS);
		VBox.setVgrow(selectedYearLong, Priority.ALWAYS);
		
		//HBox for unselected and selected list views
		HBox selection = new HBox(20);
		selection.setAlignment(Pos.CENTER);
		selection.getChildren().addAll(containerLeft, containerRight);
		HBox.setHgrow(containerLeft, Priority.ALWAYS);
		HBox.setHgrow(containerRight, Priority.ALWAYS);
		
		//initializing text fields
		tfTerm1 = new TextField();
		tfTerm1.setEditable(false); 
		tfTerm1.setText("0");
		tfTerm1.setAlignment(Pos.CENTER_RIGHT);
		tfTerm1.setMaxSize(60, 20);
		
		tfTerm2 = new TextField();
		tfTerm2.setEditable(false); 
		tfTerm2.setText("0");
		tfTerm2.setAlignment(Pos.CENTER_RIGHT);
		tfTerm2.setMaxSize(60, 20);
		
		//HBox for current term 1 credits text field with label
		HBox currentTerm1 = new HBox(10);
		currentTerm1.setAlignment(Pos.CENTER_RIGHT);
		currentTerm1.getChildren().addAll(lblTerm1Credits, tfTerm1);
		
		//HBox for current term 2 credits text field with label
		HBox currentTerm2 = new HBox(10);
		currentTerm2.setAlignment(Pos.CENTER_LEFT);
		currentTerm2.getChildren().addAll(lblTerm2Credits, tfTerm2);
		
		//HBox for both the current term credits
		HBox currentTermCredits = new HBox(70);
		currentTermCredits.getChildren().addAll(currentTerm1, currentTerm2);
		currentTermCredits.setAlignment(Pos.CENTER);
		
		//HBox for reset and submit
		HBox rs = new HBox(20);
		rs.setAlignment(Pos.CENTER);
		rs.getChildren().addAll(reset, submit);
		
		//adding the container to the layout
		VBox container = new VBox(20);
		container.getChildren().addAll(selection, currentTermCredits, rs);
		container.setAlignment(Pos.CENTER);
		container.setMinSize(200, 150);
		VBox.setVgrow(selection, Priority.ALWAYS);
		
		this.setCenter(container);
	}
	
	//method to attach the add buttons event handler
	public void addAddHandler(EventHandler<ActionEvent> handler) {
		
		addTerm1.setOnAction(handler);
		addTerm2.setOnAction(handler);
	}
	
	//method to attach the remove buttons event handler
	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		
		removeTerm1.setOnAction(handler);
		removeTerm2.setOnAction(handler);
	}
	
	//method to attach the reset button2 event handler
	public void addResetHandler(EventHandler<ActionEvent> handler) {
		
		reset.setOnAction(handler);
	}
	
	//method to attach the submit button event handler
	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		
		submit.setOnAction(handler);
	}
	
	//methods to get the selection from the list views
	public ObservableList<Module> getUnselectedModuleTerm1() {
		
		return lvUnselectedTerm1.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getUnselectedModuleTerm2() {
		
		return lvUnselectedTerm2.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getSelectedModuleTerm1() {
		
		return lvSelectedTerm1.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getSelectedModuleTerm2() {
		
		return lvSelectedTerm2.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getAllUnselectedModuleTerm1() {
		
		return olUnselectedTerm1;
	}
	
	public ObservableList<Module> getAllUnselectedModuleTerm2() {
		
		return olUnselectedTerm2;
	}
	
	public ObservableList<Module> getAllSelectedModuleTerm1() {
		
		return olSelectedTerm1;
	}
	
	public ObservableList<Module> getAllSelectedModuleTerm2() {
		
		return olSelectedTerm2;
	}
	
	public ObservableList<Module> getAllSelectedYearLong() {
		return olSelectedYearLong;
	}
	
	//method to add selected term 1 modules
	public void addTerm1Modules(ObservableList<Module> ol) {
		
		creditsTerm1 = Integer.parseInt(tfTerm1.getText());
		
		if(getCredits() >= 120) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Maximum credit limit reached.");
			alert.showAndWait();
			
		} else if(creditsTerm1 >= 60) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Please select modules from term 2.");
			alert.showAndWait();
			
		} else {
			
			olSelectedTerm1.addAll(ol);
			olUnselectedTerm1.removeAll(ol);
			lvSelectedTerm1.setItems(olSelectedTerm1);
			lvUnselectedTerm1.setItems(olUnselectedTerm1);
			
			creditsTerm1 += 15;
		}
		
		tfTerm1.setText(Integer.toString(creditsTerm1));
	}
	
	//method to add selected term 2 modules
	public void addTerm2Modules(ObservableList<Module> ol) {
		
		creditsTerm2 = Integer.parseInt(tfTerm2.getText());
		
		if(getCredits() >= 120) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Maximum credit limit reached.");
			alert.showAndWait();
			
		} else if(creditsTerm2 >= 60) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Credit limit reached!");
			alert.setContentText("Please select modules from term 1.");
			alert.showAndWait();
			
		} else {
			
			olSelectedTerm2.addAll(ol);
			olUnselectedTerm2.removeAll(ol);
			lvSelectedTerm2.setItems(olSelectedTerm2);
			lvUnselectedTerm2.setItems(olUnselectedTerm2);
			
			creditsTerm2 += 15;
		}
		
		tfTerm2.setText(Integer.toString(creditsTerm2));
	}
	
	//method to remove selected term 1 modules
	public void removeTerm1Modules(ObservableList<Module> ol) {
		
		ObservableList<Module> temp = FXCollections.observableArrayList();
		creditsTerm1 = 0;
			
		temp.addAll(ol);
			
		for(Module m : ol) {
			if(m.isMandatory() == true) {
				temp.remove(m);
			}
		}
			
		olUnselectedTerm1.addAll(temp);
		olSelectedTerm1.removeAll(temp);
		lvUnselectedTerm1.setItems(olUnselectedTerm1);
		lvSelectedTerm1.setItems(olSelectedTerm1);
		
		for(Module m : olSelectedYearLong) {
			
			creditsTerm1 += m.getModuleCredits()/2;
		}
		
		for(Module m : olSelectedTerm1) {
			
			creditsTerm1 += m.getModuleCredits();
		}
	
		tfTerm1.setText(Integer.toString(creditsTerm1));
	}
	
	//method to remove selected term 2 modules
	public void removeTerm2Modules(ObservableList<Module> ol) {
		
		ObservableList<Module> temp = FXCollections.observableArrayList(ol);
		creditsTerm2 = 0;
			
		temp.addAll(ol);
			
		for(Module m : ol) {
			if(m.isMandatory() == true) {
				temp.remove(m);
			}
		}
			
		olUnselectedTerm2.addAll(temp);
		olSelectedTerm2.removeAll(temp);
		lvUnselectedTerm2.setItems(olUnselectedTerm2);
		lvSelectedTerm2.setItems(olSelectedTerm2);
		
		for(Module m : olSelectedYearLong) {
			
			creditsTerm2 += m.getModuleCredits()/2;
		}
		
		for(Module m : olSelectedTerm2) {
			
			creditsTerm2 += m.getModuleCredits();
		}
		
		tfTerm2.setText(Integer.toString(creditsTerm2));
	}
	
	//method to populate the list views and text fields
	public void addDefaultCourses(Course c) {
		
		olModules = FXCollections.observableArrayList();
		creditsTerm1 = 0;
		creditsTerm2 = 0;
		
		olModules.addAll(c.getAllModulesOnCourse());
		
		olUnselectedTerm1.clear();
		olUnselectedTerm2.clear();
		olSelectedYearLong.clear();
		olSelectedTerm1.clear();
		olSelectedTerm2.clear();

		olModules.forEach((module) -> {
			if(module.getDelivery().equals(Schedule.TERM_1) && module.isMandatory() == false) {
	
				olUnselectedTerm1.add(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2) && module.isMandatory() == false) {
				
				olUnselectedTerm2.add(module);
				
			} else if(module.getDelivery().equals(Schedule.YEAR_LONG) && module.isMandatory() == true) {
				
				olSelectedYearLong.add(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_1) && module.isMandatory() == true) {
				
				olSelectedTerm1.add(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2) && module.isMandatory() == true) {
				
				olSelectedTerm2.add(module);
			}
		});	
		
		lvUnselectedTerm1.scrollTo(0);
		lvUnselectedTerm2.scrollTo(0);
		lvSelectedTerm1.scrollTo(0);
		lvSelectedTerm2.scrollTo(0);
		lvSelectedYearLong.scrollTo(0);
		
		for(Module m : olSelectedTerm1) {
			
			creditsTerm1 += m.getModuleCredits();
		}
		
		for(Module m : olSelectedTerm2) {
			
			creditsTerm2 += m.getModuleCredits();
		}
		
		for(Module m : olSelectedYearLong) {
			
			creditsTerm1 += m.getModuleCredits()/2;
			creditsTerm2 += m.getModuleCredits()/2;
		}
		
		
		tfTerm1.setText(Integer.toString(creditsTerm1));
		tfTerm2.setText(Integer.toString(creditsTerm2));
	}
	
	//method to load the GUI when using load profile
	public void setSelectedModules(Course c, Set<Module> selected) {
		
		olModules = FXCollections.observableArrayList();
		 
		olSelectedTerm1.clear();
		olSelectedTerm2.clear();
		olSelectedYearLong.clear();
		olUnselectedTerm1.clear();
		olUnselectedTerm2.clear();
		olModules.clear();
		
		olModules.addAll(c.getAllModulesOnCourse());
		
		selected.forEach((module) -> {
			
			if(module.getDelivery().equals(Schedule.TERM_1)) {
				
				olSelectedTerm1.add(module);
				olModules.remove(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2)) {
				
				olSelectedTerm2.add(module);
				olModules.remove(module);
				
			} else if(module.getDelivery().equals(Schedule.YEAR_LONG)) {
				
				olSelectedYearLong.add(module);
				olModules.remove(module);
			}
		});
		
		olModules.forEach((module) -> {
			if(module.getDelivery().equals(Schedule.TERM_1) && module.isMandatory() == false) {
	
				olUnselectedTerm1.add(module);
				
			} else if(module.getDelivery().equals(Schedule.TERM_2) && module.isMandatory() == false) {
				
				olUnselectedTerm2.add(module);
				
			}
		});
		
		lvSelectedTerm1.setItems(olSelectedTerm1);
		lvSelectedTerm2.setItems(olSelectedTerm2);
		lvSelectedYearLong.setItems(olSelectedYearLong);
		lvUnselectedTerm1.setItems(olUnselectedTerm1);
		lvUnselectedTerm2.setItems(olUnselectedTerm2);
	}
	
	//method to get button objects
	public Button getAdd1() {
		return addTerm1;
	}
	
	public Button getAdd2() {
		return addTerm2;
	}
	
	public Button getRemove1() {
		return removeTerm1;
	}
	
	public Button getRemove2() {
		return removeTerm2;
	}
	
	//method that returns the total credits
	public int getCredits() {
		
		creditsTerm1 = Integer.parseInt(tfTerm1.getText());
		creditsTerm2 = Integer.parseInt(tfTerm2.getText());
		
		return creditsTerm1 + creditsTerm2;
	}
}















