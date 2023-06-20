# JavaFX Module Selection GUI
This repository contains a JavaFX graphical user interface (GUI) application that allows second-year undergraduate computing students to select their final year module options. The application provides an interactive interface where modules can be dynamically selected based on the chosen course of study. It also includes validation to ensure a legitimate selection of modules is made.

## Features
- Course Selection: The application allows students to choose their course of study from a pre-populated list of available courses, including Computer Science and Software Engineering.
- Module Selection: Students can select modules based on their chosen course. The available modules are dynamically updated according to the selected course.
- Compulsory Modules: The application indicates compulsory modules for each course, ensuring they are automatically included in the selection.
- Term-based Modules: Modules are categorized into term 1, term 2, or year-long, and students can select modules for each term individually.
- Credit Validation: The application validates the selected modules to ensure the total credits do not exceed the maximum allowed per term and in total.
- Reserve Modules: Students can choose reserve modules for each term from the remaining unselected modules. These reserve modules serve as alternatives in case of module unavailability or capacity constraints.
- Overview Selection: An overview tab provides a summary of the student's details, selected modules, and reserved modules. The information is presented clearly and organized in separate sections.
- Save and Load: The application allows saving and loading student profiles, including their selected and reserved modules, for future reference.

## Getting Started

To run the JavaFX Module Selection GUI application, follow these steps:

- Clone the repository to your local machine.
- Import the Java project into Eclipse or your preferred Java IDE.
- Build and run the project using the JavaFX runtime environment.
- Use the application to create a student profile, select modules, and manage reserves.
- Save and load student profiles as needed.

## Application Structure
The application follows the Model-View-Controller (MVC) design pattern for a decoupled and maintainable solution. The main components of the application are:

- Model: Contains classes representing the data model, including StudentProfile, Course, Module, and Name. These classes encapsulate the necessary attributes and relationships for managing student profiles and module selection.
- View: Provides the user interface components and layout. The view is divided into multiple forms displayed on separate tabs, including the "Create Profile," "Select Modules," "Reserve Modules," and "Overview Selection" forms.
- Controller: Manages the interactions between the model and view components. The controller contains event handlers and general behavior for the application, ensuring proper functionality and data flow.

## Usage
- Launch the application and navigate to the "Create Profile" tab.
- Select the desired course of study from the provided options.
- Enter the student's P number, first name, last name, email, and submission date.
- Click the "Create Profile" button to create the student profile.
- Switch to the "Select Modules" tab to choose modules for each term.
- Use the provided list views to add or remove modules from the selection.
- Keep an eye on the displayed term 1 and term 2 credits to ensure compliance with the maximum credit limits.
- To reset the module selection, click the "Reset" button.
- Once the module selection is complete, click the "Submit" button.
- Proceed to the "Reserve Modules" tab to choose reserve modules for each term.
- Use the add and remove buttons to manage the reserve module selection.
- Confirm the selected reserve modules for each term by clicking the corresponding "Confirm" button.
