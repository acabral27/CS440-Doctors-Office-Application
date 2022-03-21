import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class staffPatientListController implements Initializable {

    private MedicalStaff currMedicalStaff;
    private Vector<Patient> patientList = new Vector<>();

    private Patient patientFocus;
    @FXML
    private BorderPane rootB;

    @FXML
    private ListView<String> listOfPatients;

    @FXML
    private Label patientName;

    @FXML
    private Label pHeight;

    @FXML
    private Label pWeight;

    @FXML
    private Label pDoB;

    @FXML
    private TextArea pNotes;

    @FXML
    private Button confirmBtn;

    @FXML
    private Pane infoPane;

    @FXML public void handleMouseClick(){
        System.out.println("clicked on " + listOfPatients.getSelectionModel().getSelectedItem());
        String[] splitString = listOfPatients.getSelectionModel().getSelectedItem().split(" ");
        System.out.println(splitString[0]);
        String username = splitString[0];
        Patient patient = ApplicationGUI.getPatientRecords(username);
        patientFocus = patient;
        patientName.setText(patient.fName + " " + patient.lName);
        pHeight.setText(Integer.toString(patient.height));
        pWeight.setText(Integer.toString(patient.weight));
        pDoB.setText(patient.dob);
        pNotes.setText(patient.patientNotes);

        infoPane.setVisible(true);
        //comment
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void confirmBtnClicked(){
        if(patientFocus != null)
        {
            String newNote = pNotes.getText();
            patientFocus.patientNotes = newNote;
            ApplicationGUI.updatePatientNotes(newNote, patientFocus.username);
        }
    }
    public void initMedicalStaff(MedicalStaff currStaff) {
        System.out.println("In staff dashboard currently \n");
        currMedicalStaff = currStaff;
        System.out.println(currMedicalStaff.fName + currMedicalStaff.lName + currMedicalStaff.username);

        //currMedicalStaff.getPatients
        System.out.println(currMedicalStaff.getPatientUsernames());

//        patientList = currMedicalStaff.getPatientUsernames();
        Vector<String> patientUsernames = currMedicalStaff.getPatientUsernames();
        for(String username: patientUsernames)
        {
            Patient newPatient = ApplicationGUI.getPatientRecords(username);
            patientList.add(newPatient);
        }

        System.out.println("Populating patient list");
        for (Patient p : patientList) {
            System.out.println(p.fName + p.lName);
            listOfPatients.getItems().add(p.username + " : " + p.fName + " " + p.lName);
        }
    }



    public void homeBtnPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/staffDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        staffDashboardController controller = loader.getController();
        controller.initMedicalStaff(currMedicalStaff);

        rootB.getScene().setRoot(root);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        rootB.getScene().setRoot(root);//update scene graph
    }
}
