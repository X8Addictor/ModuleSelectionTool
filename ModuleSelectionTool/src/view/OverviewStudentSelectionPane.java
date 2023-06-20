package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OverviewStudentSelectionPane extends BorderPane{
	
	private TextArea txtStudentProfileDetail, txtSelectedModulesDetail, txtReservedModulesDetail;
	private Button btnSaveProfile;
	
	public OverviewStudentSelectionPane() {
		
		//initializing text area
		txtStudentProfileDetail = new TextArea("Profile will appear here");
		txtStudentProfileDetail.setEditable(false);
		txtStudentProfileDetail.setPrefSize(620, 100);
		
		txtSelectedModulesDetail = new TextArea("Selected modules will appear here");
		txtSelectedModulesDetail.setEditable(false);
		txtSelectedModulesDetail.setPrefSize(300, 200);
		    
		txtReservedModulesDetail = new TextArea("Reserved modules will appear here");
		txtReservedModulesDetail.setEditable(false);
	    txtReservedModulesDetail.setPrefSize(300, 200);
		
	    //initializing buttons
		btnSaveProfile = new Button("Save Overview");
		btnSaveProfile.setPrefSize(100, 20);
		
		//HBox for student profile detail
		HBox txtStudentProfile = new HBox();
		txtStudentProfile.setAlignment(Pos.CENTER);
		txtStudentProfile.setPadding(new Insets(20));
		txtStudentProfile.getChildren().add(txtStudentProfileDetail);
		HBox.setHgrow(txtStudentProfileDetail, Priority.ALWAYS);
		
		//HBox for selected modules and reserved modules detail
		HBox txtSelectedReserved = new HBox(20);
		txtSelectedReserved.setAlignment(Pos.CENTER);
		txtSelectedReserved.setPadding(new Insets(20));
		txtSelectedReserved.getChildren().addAll(txtSelectedModulesDetail, txtReservedModulesDetail);
		HBox.setHgrow(txtSelectedModulesDetail, Priority.ALWAYS);
		HBox.setHgrow(txtReservedModulesDetail, Priority.ALWAYS);
		
		//adding the container to the layout
		VBox txt3 = new VBox();
		txt3.setAlignment(Pos.CENTER);
		txt3.setPadding(new Insets(20));
		txt3.setSpacing(20);
		txt3.getChildren().addAll(txtStudentProfile, txtSelectedReserved, btnSaveProfile);
		VBox.setVgrow(txtSelectedReserved, Priority.ALWAYS);
		
		this.setCenter(txt3);
	}	
	
	//method to attach the save overview button handler
	public void addSaveOverviewHandler(EventHandler<ActionEvent> handler) {
		
		btnSaveProfile.setOnAction(handler);
	}
	
	//methods to set and get text area
	public void setProfile(String profile) {
		
		txtStudentProfileDetail.setText(profile);
	}
	
	public void setSelected(String selected) {
		
		txtSelectedModulesDetail.setText(selected);
	}

	public void setReserved(String reserved) {
		
		txtReservedModulesDetail.setText(reserved);
	}
	
	public String getProfile() {
		
		return txtStudentProfileDetail.getText();
	}
	
	public String getSelected() {
		
		return txtSelectedModulesDetail.getText();
	}

	public String getReserved() {
		
		return txtReservedModulesDetail.getText();
	}	
	
	//method that returns the complete details of the student
	public String getOverview() {
		
		return "Student Profile:\n" + "==========\n" + getProfile() + "\n" + "\nSelected Modules:\n" + "==========\n" + getSelected() + "\nReserve Modules:\n" + "==========" + getReserved();
	}
}
