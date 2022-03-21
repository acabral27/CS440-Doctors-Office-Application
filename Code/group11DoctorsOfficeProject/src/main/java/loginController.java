

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class loginController implements Initializable {

    public static User currentUser;

    @FXML
    private BorderPane root;

    @FXML
    private BorderPane root3;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Button newAccountBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private ToggleGroup btnGroup;

    //--------------------------------------------------------
    private Boolean username = false;
    private Boolean pw = false;


    //        passwordTextField.setText("bob12345");ethod stub



    // static so each instance of controller can access to update
    //  private static String identifierPressed = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub
//        usernameTextField.setText("hmungus");
//        passwordTextField.setText("bob1234");
//        username = true;
//        pw = true;
    }

    public void usernameEntered(KeyEvent e) throws IOException{
        String input = usernameTextField.getText();
        if (!Objects.equals(input, "")) {
            username = true;
        } else {
            username = false;
        }
    }
    public void passwordEntered(KeyEvent e) throws IOException{
        String input = passwordTextField.getText();
        if (!Objects.equals(input, "")) {
            pw = true;
        } else {
            pw = false;
        }
    }

    public void signInBtnPressed(ActionEvent e) throws IOException {
//        System.out.println("Username = " + usernameTextField.getText());
//        System.out.println("Password = " + passwordTextField.getText());



        //we should check the db for these values
        if ((username) && (pw)) {

//            System.out.println("Signing in");
            if (ApplicationGUI.checkIfUserExists(usernameTextField.getText()))
            {
                if (ApplicationGUI.checkIfPasswordMatches(passwordTextField.getText()))
                {
                    loginErrorLabel.setText("");
//                    System.out.println("Successful login\n");

                    //get instance of the loader class
                    if (ApplicationGUI.getIsPatient())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
                        Parent root3 = loader.load(); //load view into parent
                        patientDashboardController controller = loader.getController();
                        Patient currPatient = ApplicationGUI.getPatientRecords(usernameTextField.getText());
                        controller.initPatient(currPatient);


                        root.getScene().setRoot(root3);//update scene graph
                    }
                    if (ApplicationGUI.getIsStaff())
                    {
//                        System.out.println("is staff 1");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/staffDashboard.fxml"));
                        Parent root2 = loader.load(); //load view into parent
                        staffDashboardController controller = loader.getController();
                        MedicalStaff currStaff = ApplicationGUI.getStaffRecords(usernameTextField.getText());
                        controller.initMedicalStaff(currStaff);

                        root.getScene().setRoot(root2);//update scene graph
                    }
                }
                else
                {
                    loginErrorLabel.setText("Incorrect password!");
                }
            }
            else
            {
                loginErrorLabel.setText("Username does not exist!");
            }
//            System.out.println("after sign in");

        }
        // both values are missing
        else {
            loginErrorLabel.setText("Both username and password need to be filled");
        }
    }

    public void newAccountPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/registerPage1.fxml"));
        Parent root2 = loader.load(); //load view into parent

        root.getScene().setRoot(root2);//update scene graph

    }

//    public void backBtnPressed(ActionEvent e) throws IOException {
//
////        textEntered = putText.getText();
////        System.out.println(textEntered);
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
//        Parent root = loader.load();
//        Controller myctr = loader.getController();
////        myctr.setText2();
//        root2.getScene().setRoot(root);
//
//    }


}
