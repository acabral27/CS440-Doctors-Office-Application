import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ResourceBundle;

public class patientDashboardController implements Initializable {

    static String userPressedLocation;

    private LocalDateTime userPressedDate;

    private Patient currPatient;

    @FXML
    private BorderPane root3;

    @FXML
    private BorderPane root4;

    @FXML
    private Button calendarBtn;

    @FXML
    private Button mapBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Label patientName;

    @FXML
    private Button logOutBtn;

    @FXML
    private Label pHeight;

    @FXML
    private Label pWeight;

    @FXML
    private Label pDoB;

    @FXML
    private Circle profileIcon;

    @FXML
    private Button deleteApt;

    @FXML
    private ListView<String> appointmentList = new ListView<>();

    public void handleMouseClick(){
        System.out.println("clicked on " + appointmentList.getSelectionModel().getSelectedItem());

        userPressedLocation = appointmentList.getSelectionModel().getSelectedItem();
        mapBtn.setDisable(false);

        String entry = appointmentList.getSelectionModel().getSelectedItem();
        String[] splitString = entry.split(" ");
        String date = splitString[0] + " " + splitString[4] + ":00";
        userPressedDate = ApplicationGUI.convertToLDT(date);
        deleteApt.setDisable(false);
        //comment
    }

    public void deleteAptClicked()
    {
        ApplicationGUI.deleteAppointment(currPatient, userPressedDate);
        Iterator<Appointment> appointments = currPatient.appointmentVector.iterator();
        while(appointments.hasNext()){
            Appointment appt = appointments.next();
            if(appt.appointTime == userPressedDate){
                appointments.remove();
            }
        }

        appointmentList.getItems().clear();

        currPatient = ApplicationGUI.getPatientRecords(currPatient.username);
        for(Appointment a : currPatient.appointmentVector){
            MedicalStaff doctor = ApplicationGUI.getStaffRecords(a.doctorUsername);
            String appointTimeString = a.appointTime.toString();
            String[] timeStringSplit = appointTimeString.split("T");
            System.out.println(timeStringSplit[0]);
            appointmentList.getItems().add(String.valueOf(timeStringSplit[0] + "  @  " + timeStringSplit[1] + "  -  Dr. " + doctor.lName +"  -  " + a.appointmentLocation));
        }

        deleteApt.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set profile image
        Image img = new Image("images/no-photo-available.png");
        profileIcon.setFill(new ImagePattern(img));
    }




    public void initPatient(Patient currUser){
        System.out.println("In patient dashboard currently \n");
        currPatient = currUser;

        patientName.setText(currPatient.fName + " " + currPatient.lName);
        pWeight.setText(String.valueOf(currPatient.weight));
        pHeight.setText(String.valueOf(currPatient.height));
        pDoB.setText(currPatient.dob);

        System.out.println("Building appointment listview");
        for(Appointment a : currPatient.appointmentVector){
            MedicalStaff doctor = ApplicationGUI.getStaffRecords(a.doctorUsername);
            String appointTimeString = a.appointTime.toString();
            String[] timeStringSplit = appointTimeString.split("T");
            int apptHour = a.appointTime.getHour();
            String amOrPm = "am";
            if(apptHour >= 12)
                amOrPm = "pm";
            if(apptHour > 12)
                apptHour -= 12;
            System.out.println(timeStringSplit[0]);
            appointmentList.getItems().add(String.valueOf(timeStringSplit[0] + "  @  " + apptHour + ":00 " + amOrPm + "  -  Dr. " + doctor.lName +"  -  " + a.appointmentLocation));
        }
    }

    public void calendarPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientCalendarScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientCalendarController controller = loader.getController();
        controller.initPatient(currPatient);

        root3.getScene().setRoot(root);//update scene graph
    }

    public void mapBtnPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientMapScene.fxml"));
        Parent root4 = loader.load(); //load view into parent
        patientMapSceneController controller = loader.getController();
        controller.initPatient(currPatient);

        root3.getScene().setRoot(root4);//update scene graph

    }

    public void settingsPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientSettingsScene.fxml"));
        Parent root5 = loader.load(); //load view into parent
        patientSettingsController controller = loader.getController();

        controller.initPatient(currPatient);
        root3.getScene().setRoot(root5);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        root3.getScene().setRoot(root);//update scene graph
    }
}
