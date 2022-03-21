import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Vector;

public class staffDashboardController implements Initializable {

    private MedicalStaff currMedicalStaff;

    @FXML
    private BorderPane rootA;

    @FXML
    private Label drLastName;

    @FXML
    private ListView<String> upcomingAppointments;

    @FXML
    private Label agendaDate;

    @FXML
    private DatePicker datePicker2 = new DatePicker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker2.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        agendaDate.setText(LocalDate.now().toString());
    }

    public void initMedicalStaff(MedicalStaff currStaff){
        System.out.println("In staff dashboard currently \n");
        currMedicalStaff = currStaff;
        System.out.println(currMedicalStaff.fName + currMedicalStaff.lName + currMedicalStaff.username);
        drLastName.setText(currMedicalStaff.lName);

        LocalDate today = LocalDate.now();
        Vector<Appointment> appointments = ApplicationGUI.getAppointmentsByDate(currMedicalStaff, today);
        agendaDate.setText(today.toString());
        upcomingAppointments.getItems().clear();
        for (Appointment a: appointments){
            Patient patient = ApplicationGUI.getPatientRecords(a.patientUsername);
            String appointTimeString = a.appointTime.toString();
            String[] timeStringSplit = appointTimeString.split("T");
            int apptHour = a.appointTime.getHour();
            String amOrPm = "am";
            if(apptHour >= 12)
                amOrPm = "pm";
            if(apptHour > 12)
                apptHour -= 12;
            upcomingAppointments.getItems().add(timeStringSplit[0] + "  @  " + apptHour + ":00 " + amOrPm + "  -  " +
                    patient.fName + " " + patient.lName + "  -  " + a.appointmentLocation);
        }

//        for (Appointment a: currMedicalStaff.appointmentVector)
//        {
//            Patient patient = ApplicationGUI.getPatientRecords(a.patientUsername);
//            String appointTimeString = a.appointTime.toString();
//            String[] timeStringSplit = appointTimeString.split("T");
//            upcomingAppointments.getItems().add(timeStringSplit[0] + "  @  " + timeStringSplit[1] + "  -  " +
//                                                patient.fName + " " + patient.lName + "  -  " + a.appointmentLocation);
//        }
//        System.out.println(currStaff.appointmentVector);
    }

    public void dateSelected(ActionEvent e){
        LocalDate dateSelected = datePicker2.getValue();
        Vector<Appointment> appointments = ApplicationGUI.getAppointmentsByDate(currMedicalStaff, dateSelected);
        agendaDate.setText(datePicker2.getValue().toString());
        upcomingAppointments.getItems().clear();
        for (Appointment a: appointments){
            Patient patient = ApplicationGUI.getPatientRecords(a.patientUsername);
            String appointTimeString = a.appointTime.toString();
            String[] timeStringSplit = appointTimeString.split("T");
            upcomingAppointments.getItems().add(timeStringSplit[0] + "  @  " + timeStringSplit[1] + "  -  " +
                    patient.fName + " " + patient.lName + "  -  " + a.appointmentLocation);
        }
    }

    public void myPatientsBtn(ActionEvent e) throws IOException {
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/staffPatientListScene.fxml"));
        Parent root = loader.load(); //load view into parent
        staffPatientListController controller = loader.getController();
        controller.initMedicalStaff(currMedicalStaff);

        rootA.getScene().setRoot(root);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        rootA.getScene().setRoot(root);//update scene graph
    }

}
