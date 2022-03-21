import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class patientSettingsController implements Initializable {

    private Patient currPatient;

    @FXML
    private BorderPane root5;

    @FXML
    private Button homeBtn;

    @FXML
    private Button calendarBtn;

    @FXML
    private Button mapBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField contactTextField;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initPatient(Patient currUser){
        System.out.println("In patient Settings \n");
        currPatient = currUser;
        System.out.println(currPatient.fName + currPatient.lName + currPatient.username);

        System.out.println(currPatient.appointmentVector);
    }

    public void homeBtnPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        patientDashboardController controller = loader.getController();
        controller.initPatient(currPatient);

        root5.getScene().setRoot(root);//update scene graph
    }

    public void calendarPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientCalendarScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientCalendarController controller = loader.getController();
        controller.initPatient(currPatient);
        root5.getScene().setRoot(root);//update scene graph
    }

//    public void mapBtnPressed(ActionEvent e) throws IOException {
//
//        //get instance of the loader class
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientMapScene.fxml"));
//        Parent root4 = loader.load(); //load view into parent
//        patientMapSceneController controller = loader.getController();
//        controller.initPatient(currPatient);
//        root5.getScene().setRoot(root4);//update scene graph
//    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        root5.getScene().setRoot(root);//update scene graph
    }

    public void submitNewEmail()
    {
        String newEmail = emailTextField.getText();
        System.out.println(currPatient.email);
        System.out.println(newEmail);
        ApplicationGUI.updateEmail(newEmail, currPatient.username);
        currPatient.email = newEmail;
    }

    public void submitNewUsername()
    {
        String newUsername = usernameTextField.getText();
        switch (AccountActions.isValidUsername(newUsername)) {
            case 440:
                //errorLabel.setText("Username must be between 8 and 15 chars");
                System.out.println("username must be between 8 and 15 chars");
                break;

            case 420:
                //errorLabel.setText("Special characters not allowed in username");
                System.out.println("Special characters not allowed in username");
                break;

                //Username is valid
            case 400:
                Boolean userNameExists = ApplicationGUI.checkIfUserExists(newUsername);
                if(!userNameExists){
                    System.out.println("Username already exists");
                }
                else{
                    System.out.println(currPatient.username);
                    System.out.println(newUsername);
                    ApplicationGUI.updateUsername(newUsername, currPatient.username);
                    currPatient.username = newUsername;
                }
                break;
        }

    }

    public void submitNewPassword()
    {
        //TODO: Implement valid password check
        String newPassword = passwordTextField.getText();
        switch (AccountActions.isValidPassword(newPassword)) {
            case 340:
                //errorLabel.setText("Password must be between 8 and 15 chars");
                System.out.println("Password must be between 8 and 15 chars");
                break;

            case 320:
                //errorLabel.setText("Special characters not allowed in password");
                System.out.println("Special characters not allowed in password");
                break;

            case 310:
                //errorLabel.setText("Password must contain at least 2 numbers");
                System.out.println("Password must contain at least 2 numbers");
                break;

                //Password is valid
            case 300:
                System.out.println(currPatient.password);
                System.out.println(newPassword);
                ApplicationGUI.updatePassword(newPassword, currPatient.username);
                currPatient.password = newPassword;
        }

    }

    public void submitNewContact()
    {
        String newContactNumber = contactTextField.getText();
        System.out.println(currPatient.cont_number);
        System.out.println(newContactNumber);
        ApplicationGUI.updatePhoneNumber(newContactNumber, currPatient.username);
        currPatient.cont_number = newContactNumber;
    }
}
