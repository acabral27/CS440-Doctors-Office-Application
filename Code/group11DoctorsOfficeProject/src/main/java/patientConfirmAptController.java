import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class patientConfirmAptController implements Initializable {

    @FXML
    private BorderPane root8;

    @FXML
    private Button bookAptBtn;

    @FXML
    private Label doctorName;

    @FXML
    private Label apptTime;

    @FXML
    private Label apptDate;

    @FXML
    private Label locationLabel;

    @FXML
    private WebView mapView;


    private MedicalStaff appointedDoctor;
    private LocalDateTime appointmentTime;
    private Patient currPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void initInformation(Patient currUser,MedicalStaff staffSelected, LocalDateTime tempTime) {

        currPatient = currUser;
        appointmentTime = tempTime;
        appointedDoctor = staffSelected;
        apptDate.setText(tempTime.getMonth().toString() + " " + tempTime.getDayOfMonth());
        int apptHour = tempTime.getHour();
        String amOrPm = "am";
        if(apptHour >= 12)
            amOrPm = "pm";
        if(apptHour > 12)
            apptHour -= 12;

        apptTime.setText(String.valueOf(apptHour) + ":00 " + amOrPm);
        doctorName.setText(appointedDoctor.fName + " " + appointedDoctor.lName);
        locationLabel.setText(appointedDoctor.location);

        System.out.println(currPatient.fName + currPatient.lName + currPatient.username);
        System.out.println(currPatient.appointmentVector);

        switch (appointedDoctor.location) {
            case "NorthWestern": {
                String stringForHTMLPath = "/NorthWesternMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "UIHealth": {
                String stringForHTMLPath = "/UIHealthMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "RushHospital": {
                String stringForHTMLPath = "/RushHospitalMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "SaintAnthonyHospital": {
                String stringForHTMLPath = "/SaintAnthonyHospitalMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "WeissMemorialHospital": {
                String stringForHTMLPath = "/WeissMemorialHospitalMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "MercyHospital": {
                String stringForHTMLPath = "/MercyHospitalMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "MountSinaiHospital": {
                String stringForHTMLPath = "/MountSinaiHospitalMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
            case "KindredHospitalChicagoNorth": {
                String stringForHTMLPath = "/KindredHospitalChicagoNorthMini.html";
                WebEngine engine = mapView.getEngine();
                URL url2 = this.getClass().getResource(stringForHTMLPath);
                engine.load(url2.toString());
                break;
            }
        }

    }

    public void bookAptBtnPressed(ActionEvent e)throws IOException{



        ApplicationGUI.bookAppointment(currPatient,  appointedDoctor, appointmentTime);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        patientDashboardController controller = loader.getController();
        currPatient = ApplicationGUI.getPatientRecords(currPatient.username);
        controller.initPatient(currPatient);
        root8.getScene().setRoot(root);//update scene graph

    }


    public void homeBtnPressed(ActionEvent e) throws IOException {


        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        patientDashboardController controller = loader.getController();
        controller.initPatient(currPatient);
        root8.getScene().setRoot(root);//update scene graph
    }

    public void calendarPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientCalendarScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientCalendarController controller = loader.getController();
        controller.initPatient(currPatient);
        root8.getScene().setRoot(root);//update scene graph

    }

    public void settingsPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientSettingsScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientSettingsController controller = loader.getController();
        controller.initPatient(currPatient);
        root8.getScene().setRoot(root);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        root8.getScene().setRoot(root);//update scene graph
    }
}
