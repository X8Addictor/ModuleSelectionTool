package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleSelectionToolRootPane;
import view.OverviewStudentSelectionPane;
import view.ReserveStudentModulePane;
import view.SelectStudentModulePane;
import view.CreateStudentProfilePane;
import view.ModuleSelectionToolMenuBar;

public class ModuleSelectionToolController {

	//fields to be used throughout class
	private ModuleSelectionToolRootPane view;
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private SelectStudentModulePane ssmp;
	private ReserveStudentModulePane rsmp;
	private OverviewStudentSelectionPane ossp;
	private ModuleSelectionToolMenuBar mstmb;

	public ModuleSelectionToolController(ModuleSelectionToolRootPane view, StudentProfile model) {
		
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		ssmp = view.getSelectStudentModulePane();
		rsmp = view.getReserveStudentModulePane();
		ossp = view.getOverviewStudentSelectionPane();
		

		//add courses to combobox in create student profile pane using the generateAndReturnCourses helper method below
		cspp.addCoursesToComboBox(generateAndReturnCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}
	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addAboutHandler(e -> this.alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Final year module selection tool app v1.0"));
		mstmb.addLoadHandler(new LoadMenuHandler());
		mstmb.addSaveHandler(new SaveMenuHandler());
		
		//attach event handlers to the select student module pane
		ssmp.addAddHandler(new AddHandler());
		ssmp.addRemoveHandler(new RemoveHandler());
		ssmp.addResetHandler(new ResetHandler());
		ssmp.addSubmitHandler(new SubmitHandler());
		
		//attach event handlers to the reserve student module pane
		rsmp.addAddHandler(new AddHandler());
		rsmp.addRemoveHandler(new RemoveHandler());
		rsmp.addConfirmHandler(new ConfirmHandler());
		
		//attach an event handler to save overview of student
		ossp.addSaveOverviewHandler(new SaveOverviewHandler());
	}
	
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Course[] c = generateAndReturnCourses();
			
			model.setStudentCourse(cspp.getSelectedCourse());
			model.setStudentName(cspp.getStudentName());
			model.setSubmissionDate(cspp.getStudentDate());
			
			if(!cspp.getStudentEmail().endsWith("@dmu.ac.uk")) {
				
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not a valid university email!", "Please enter a valid student university email.");
				
			} else if(!cspp.getStudentPnumber().toUpperCase().startsWith("P")) {
				
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not a vaild P number", "Please put P with number.");
				
			} else {
					
				model.setStudentPnumber(cspp.getStudentPnumber());
				model.setStudentEmail(cspp.getStudentEmail());
					
				if(model.getStudentCourse().getCourseName() == "Computer Science") {
				
					ssmp.addDefaultCourses(c[0]);
						
				} else if(model.getStudentCourse().getCourseName() == "Software Engineering") {
						
					ssmp.addDefaultCourses(c[1]);
				}
					
				view.changeTab(1);
			}
		}
	}
	
	private class AddHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			
			Button b = (Button) e.getSource();
			ObservableList<Module> temp = FXCollections.observableArrayList();
			
			if(b.equals(ssmp.getAdd1())) {
				
				temp = ssmp.getUnselectedModuleTerm1();
				ssmp.addTerm1Modules(temp);
				
			} else if(b.equals(ssmp.getAdd2())) {
				
				temp = ssmp.getUnselectedModuleTerm2();
				ssmp.addTerm2Modules(temp);
				
			} else if(b.equals(rsmp.getAdd1())) {
				
				temp = rsmp.getUnselectedReserveModuleTerm1();
				rsmp.addReserveTerm1Modules(temp);		
				
			} else if(b.equals(rsmp.getAdd2())) {
				
				temp = rsmp.getUnselectedReserveModuleTerm2();
				rsmp.addReserveTerm2Modules(temp);
				
			}
		}
	}
	
	private class RemoveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Button b = (Button) e.getSource();
			ObservableList<Module> temp = FXCollections.observableArrayList();
			
			if(b.equals(ssmp.getRemove1())) {
				
				temp = ssmp.getSelectedModuleTerm1();
				ssmp.removeTerm1Modules(temp);
				
			} else if(b.equals(ssmp.getRemove2())) {
				
				temp = ssmp.getSelectedModuleTerm2();
				ssmp.removeTerm2Modules(temp);
				
			} else if(b.equals(rsmp.getRemove1())) {
				
				temp = rsmp.getSelectedReserveModuleTerm1();
				rsmp.removeReserveTerm1Modules(temp);
				
			} else if(b.equals(rsmp.getRemove2())) {
				
				temp = rsmp.getSelectedReserveModuleTerm2();
				rsmp.removeReserveTerm2Modules(temp);
			} 
		}
	}

	private class ResetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Course[] c = generateAndReturnCourses(); 
			
			if(model.getStudentCourse().getCourseName() == "Computer Science") {
				
				ssmp.addDefaultCourses(c[0]);
				
			} else if(model.getStudentCourse().getCourseName() == "Software Engineering") {
				
				ssmp.addDefaultCourses(c[1]);
			}
		}
	}
	
	private class SubmitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			ObservableList<Module> temp1 = FXCollections.observableArrayList();
			ObservableList<Module> temp2 = FXCollections.observableArrayList();
			
			if(ssmp.getCredits() < 120) {
				
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not enough credits", "Please select more modules.");
				
			} else {
				ssmp.getAllSelectedModuleTerm1().forEach(m -> model.addSelectedModule(m));
				ssmp.getAllSelectedModuleTerm2().forEach(m -> model.addSelectedModule(m));
				ssmp.getAllSelectedYearLong().forEach(m -> model.addSelectedModule(m));
				
				temp1.addAll(ssmp.getAllUnselectedModuleTerm1());
				temp2.addAll(ssmp.getAllUnselectedModuleTerm2());
				
				rsmp.addTerm1Modules(temp1);
				rsmp.addTerm2Modules(temp2);
				
				view.changeTab(2);
			}
		}
	}
	
	private class ConfirmHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Button b = (Button) e.getSource();
			String profile = "";
			String selected = "";
			String reserved = "";
			int credits = 0;
			
			if(b.equals(rsmp.getConfirm1())) {
				
				for(Module m : rsmp.getAllSelectedReserveModuleTerm1()) {
					
					credits += m.getModuleCredits();
				}
				
				if(credits != 30) {
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not enough credits", "Please select more modules.");
					
				} else {
					
					rsmp.changePane(1);
				}
				
			} else if(b.equals(rsmp.getConfirm2())) {
				
				for(Module m : rsmp.getAllSelectedReserveModuleTerm1()) {
					
					credits += m.getModuleCredits();
				}
				
				if(credits != 30) {
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not enough credits", "Please select more modules.");
					
				} else {
					
					rsmp.getAllSelectedReserveModuleTerm1().forEach(m -> model.addReservedModule(m));
					rsmp.getAllSelectedReserveModuleTerm2().forEach(m -> model.addReservedModule(m));
					
					profile = "Name: " + model.getStudentName().getFullName() + "\nPNo: " + model.getStudentPnumber() + "\nEmail: " + model.getStudentEmail();
					profile += "\nDate: "  + model.getSubmissionDate() + "\nCourse: " + model.getStudentCourse().toString();
					ossp.setProfile(profile);
					
					for(Module m : model.getAllSelectedModules()) {
						selected += "\nModule Code: " + m.getModuleCode() + ", Module Name: " + m.getModuleName() 
						+ "\nCredits: " + m.getModuleCredits() + ", Mandatory on your course? " + String.valueOf(m.isMandatory()).replace("true", "yes").replace("false", "no") + ", Delivary: " + m.getDelivery().toString().toLowerCase().replace("_", " ") + "\n";
					}
					ossp.setSelected(selected);
					
					for(Module m : model.getAllReservedModules()) {
						reserved += "\nModule Code: " + m.getModuleCode() + ", Module Name: " + m.getModuleName() 
						+ "\nCredits: " + m.getModuleCredits() + ", Delivary: " + m.getDelivery().toString().toLowerCase().replace("_", " ") + "\n";
					}
					ossp.setReserved(reserved);
					
					view.changeTab(3);
				}
			}
		}
	}
	
	private class LoadMenuHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			//load in the data model
			Course[] course = generateAndReturnCourses(); 
			ObservableList<Module> temp = FXCollections.observableArrayList();
			String profile = "";
			String selected = "";
			String reserved = "";
			
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(null);
				
			if(file != null) {
				
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()));) {

					model = (StudentProfile) ois.readObject();	

					alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Load success", "Student profile loaded from " + file.getPath());
				}
				catch (IOException ioExcep){
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Error loading", "Unexpected error while loading " + file.getPath());
				}
				catch (ClassNotFoundException c) {
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Error loading", "Class not found error ");
				}

				cspp.setProfileDetails(model);
				
				if(model.getStudentCourse().getCourseName().compareTo("Computer Science") == 0) {
					
					ssmp.setSelectedModules(course[0], model.getAllSelectedModules());
					
				} else if(model.getStudentCourse().getCourseName().compareTo("Software Engineering") == 0) {
					
					ssmp.setSelectedModules(course[1], model.getAllSelectedModules());
				}
				
				temp.addAll(ssmp.getAllUnselectedModuleTerm1());
				temp.addAll(ssmp.getAllUnselectedModuleTerm2());
				
				rsmp.setReservedModules(model.getAllReservedModules(), temp);
				
				profile = "Name: " + model.getStudentName().getFullName() + "\nPNo: " + model.getStudentPnumber() + "\nEmail: " + model.getStudentEmail();
				profile += "\nDate: "  + model.getSubmissionDate() + "\nCourse: " + model.getStudentCourse().toString();
				ossp.setProfile(profile);
				
				for(Module m : model.getAllSelectedModules()) {
					selected += "\nModule Code: " + m.getModuleCode() + ", Module Name: " + m.getModuleName() 
					+ "\nCredits: " + m.getModuleCredits() + ", Mandatory on your course? " + String.valueOf(m.isMandatory()).replace("true", "yes").replace("false", "no") + ", Delivary: " + m.getDelivery().toString().toLowerCase().replace("_", " ") + "\n";
				}
				ossp.setSelected(selected);
				
				for(Module m : model.getAllReservedModules()) {
					reserved += "\nModule Code: " + m.getModuleCode() + ", Module Name: " + m.getModuleName() 
					+ "\nCredits: " + m.getModuleCredits() + ", Delivary: " + m.getDelivery().toString().toLowerCase().replace("_", " ") + "\n";
				}
				ossp.setReserved(reserved);
			}
			           	
		}
	}
	
	private class SaveMenuHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			//save the data model
			
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(null);
				 
			if(file != null) {
				
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getPath()));) {

					oos.writeObject(model);
					oos.flush();

					alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Student profile saved to " + file.getPath());
				}
				catch (IOException ioExcep){
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Error saving", "Unexpected error while saving to " + file.getPath());
				}
			}
		}
	}
	
	private class SaveOverviewHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(null);
			
			if (file != null) {
				
				try {
					
					Files.write(Paths.get(file.getPath()), ossp.getOverview().getBytes());
					
					alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Student overview saved to " + file.getPath());
					
				} catch (IOException e1) {
					
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Error saving", "Unexpected error while saving to " + file.getPath());
				}            	
			}
		}
	}
	
	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndReturnCourses() {
		
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
