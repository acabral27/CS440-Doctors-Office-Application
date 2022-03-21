import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Vector;

public class patientCalendarController implements Initializable {
    private String selectedOnListView;
    private String selectedOnListView2;
    private Vector<MedicalStaff> medicalStaffChoices;
    private HashSet<String> hospitals;
    private MedicalStaff staffSelected;
    private String hourSelected;
    private Patient currPatient;
    @FXML
    private BorderPane root6;
    @FXML
    private DatePicker datePicker = new DatePicker();
    @FXML
    private ChoiceBox hourPickedDropDwn;
    @FXML
    private ChoiceBox minPicked;
    @FXML
    private Pane calPane;
    @FXML
    private Button genPhysBtn;
    @FXML
    private Button orthopedistBtn;
    @FXML
    private Button surgeonBtn;
    @FXML
    private ListView<String> listOfDr = new ListView<>();
    @FXML
    private ListView<String> list2 = new ListView<>();
    @FXML
    private Button locationBtn;
    @FXML
    private WebView hospitalLocations;
    @FXML
    private Button confirmAptBtn;
    @FXML
    private Label list1Label;
    @FXML
    private Label list2Label;

    static String userPressedLocationBtn;
    private boolean locationClicked = false;

    public void handleSelectionMouseClick1() {
        list2.getItems().clear();
        //Only grab last name from string, ignoring location
        selectedOnListView = listOfDr.getSelectionModel().getSelectedItem().split(" ")[0];
        if (!locationClicked) {
            for (MedicalStaff s : medicalStaffChoices) {
                if (selectedOnListView.equals(s.lName)) {
                    staffSelected = ApplicationGUI.getStaffRecords(s.username);
                    continue;
                }
            }
            datePicker.setValue(null);
            hourPickedDropDwn.setValue(null);
            hourPickedDropDwn.setDisable(true);
            datePicker.setDisable(false);
        } else {
            medicalStaffChoices = ApplicationGUI.getDoctorsBasedOnLocation(selectedOnListView);
            for (MedicalStaff s : medicalStaffChoices) {
                list2.getItems().add(s.lName + " - " + s.specialty);
            }

            switch (selectedOnListView) {
                case "NorthWestern": {
                    String stringForHTMLPath = "/NorthWesternMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "UIHealth": {
                    String stringForHTMLPath = "/UIHealthMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "RushHospital": {
                    String stringForHTMLPath = "/RushHospitalMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "SaintAnthonyHospital": {
                    String stringForHTMLPath = "/SaintAnthonyHospitalMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "WeissMemorialHospital": {
                    String stringForHTMLPath = "/WeissMemorialHospitalMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "MercyHospital": {
                    String stringForHTMLPath = "/MercyHospitalMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "MountSinaiHospital": {
                    String stringForHTMLPath = "/MountSinaiHospitalMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
                case "KindredHospitalChicagoNorth": {
                    String stringForHTMLPath = "/KindredHospitalChicagoNorthMini.html";
                    WebEngine engine = hospitalLocations.getEngine();
                    URL url2 = this.getClass().getResource(stringForHTMLPath);
                    engine.load(url2.toString());
                    break;
                }
            }


        }
        hourPickedDropDwn.getItems().clear();
        hourPickedDropDwn.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM");
    }

    public void handleSelectionMouseClick2() {

        selectedOnListView2 = list2.getSelectionModel().getSelectedItem().split(" ")[0];
        for (MedicalStaff s : medicalStaffChoices) {
            if (selectedOnListView2.equals(s.lName)) {
                staffSelected = ApplicationGUI.getStaffRecords(s.username);
                continue;
            }
        }
        datePicker.setValue(null);
        hourPickedDropDwn.setValue(null);
        hourPickedDropDwn.setDisable(true);
        datePicker.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list1Label.setText("Doctors");
        genPhysBtn.setStyle("-fx-font-size:12; -fx-background-radius:1em");
        orthopedistBtn.setStyle("-fx-font-size:12; -fx-background-radius:1em");
        surgeonBtn.setStyle("-fx-font-size:12; -fx-background-radius:1em");
        locationBtn.setStyle("-fx-font-size:12; -fx-background-radius:1em");
        hourPickedDropDwn.getItems().clear();

        datePicker.setValue(null);
        datePicker.setDisable(true);
        hourPickedDropDwn.setDisable(true);
        confirmAptBtn.setDisable(true);

        hourPickedDropDwn.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM");
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        String stringForHTMLPath = "/hospitalsNearUser.html";
        WebEngine engine = hospitalLocations.getEngine();
        URL url2 = this.getClass().getResource(stringForHTMLPath);
        engine.load(url2.toString());
    }

    public void confirmAptBtnPressed(ActionEvent e) throws IOException {
        String temp = "";
        LocalDateTime tempTime;
        hourSelected = hourPickedDropDwn.getSelectionModel().getSelectedItem().toString();
        switch (hourSelected) {
            case "9:00 AM":
                temp = "09:00:00";
                break;
            case "10:00 AM":
                temp = "10:00:00";
                break;
            case "11:00 AM":
                temp = "11:00:00";
                break;
            case "12:00 PM":
                temp = "12:00:00";
                break;
            case "1:00 PM":
                temp = "13:00:00";
                break;
            case "2:00 PM":
                temp = "14:00:00";
                break;
            case "3:00 PM":
                temp = "15:00:00";
                break;
            case "4:00 PM":
                temp = "16:00:00";
                break;
            case "5:00 PM":
                temp = "17:00:00";
                break;
            default:
                temp = "00:00:00";
                break;
        }

        System.out.println(datePicker.getValue() + " " + temp);
        temp = datePicker.getValue() + " " + temp;
        tempTime = ApplicationGUI.convertToLDT(temp);
        Appointment newAppointment = new Appointment(currPatient.username, staffSelected.username, tempTime, staffSelected.location, " ");
//        ApplicationGUI.bookAppointment(currPatient, staffSelected, tempTime);


        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientConfirmAptScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientConfirmAptController controller = loader.getController();
        currPatient = ApplicationGUI.getPatientRecords(currPatient.username);
        controller.initInformation(currPatient, staffSelected, tempTime);
        root6.getScene().setRoot(root);//update scene graph
    }

    public void initPatient(Patient currUser) {

        currPatient = currUser;
        System.out.println(currPatient.fName + currPatient.lName + currPatient.username);
        System.out.println(currPatient.appointmentVector);


//        LocalDateTime apptTime = ApplicationGUI.convertToLDT("2023-10-11 11:00:00");
//        MedicalStaff doctor = ApplicationGUI.getStaffRecords("doctor1");
//        ApplicationGUI.bookAppointment(currPatient, doctor, apptTime);
    }

    public void dateSelected(ActionEvent e) {
        hourPickedDropDwn.setDisable(false);
    }

    public void hoursDropSelected(MouseEvent e) throws IOException {

        String temp = " ";
        LocalDateTime temptime;
        temp = datePicker.getValue().toString();
        System.out.println(temp);

        if (!staffSelected.appointmentVector.isEmpty()) {
            for (Appointment a : staffSelected.appointmentVector) {
                if (a.appointTime.toLocalDate().toString().contains(temp)) {

                    System.out.println(a.appointTime.getHour());

                    switch (a.appointTime.getHour()) {
                        case 9:
                            hourPickedDropDwn.getItems().remove("09:00 AM");
                            break;
                        case 10:
                            hourPickedDropDwn.getItems().remove("10:00 AM");
                            break;
                        case 11:
                            hourPickedDropDwn.getItems().remove("11:00 AM");
                            break;
                        case 12:
                            hourPickedDropDwn.getItems().remove("12:00 PM");
                            break;
                        case 13:
                            hourPickedDropDwn.getItems().remove("1:00 PM");
                            break;
                        case 14:
                            hourPickedDropDwn.getItems().remove("2:00 PM");
                            break;
                        case 15:
                            hourPickedDropDwn.getItems().remove("3:00 PM");
                            break;
                        case 16:
                            hourPickedDropDwn.getItems().remove("4:00 PM");
                            break;
                        case 17:
                            hourPickedDropDwn.getItems().remove("5:00 PM");
                            break;
                    }
                }
            }
        }
    }

    public void hourSelected(ActionEvent e) {
        confirmAptBtn.setDisable(false);
    }

    public void homeBtnPressed(ActionEvent e) throws IOException {
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        patientDashboardController controller = loader.getController();
        controller.initPatient(currPatient);
        root6.getScene().setRoot(root);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent
        root6.getScene().setRoot(root);//update scene graph
    }

    public void settingsPressed(ActionEvent e) throws IOException {
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientSettingsScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientSettingsController controller = loader.getController();
        controller.initPatient(currPatient);
        root6.getScene().setRoot(root);//update scene graph
    }

    public void genPhysButtonPressed(MouseEvent e) throws IOException {
        list1Label.setText("Doctors");
        locationClicked = false;
        list2Label.setVisible(false);
        list2.setVisible(false);
        listOfDr.getItems().clear();
        medicalStaffChoices = ApplicationGUI.getSpecialtyDoctors("physician");
        for (MedicalStaff s : medicalStaffChoices) {
            listOfDr.getItems().add(s.lName + " - " + s.location);
        }
        datePicker.setValue(null);
        datePicker.setDisable(true);
        hourPickedDropDwn.setValue(null);
        hourPickedDropDwn.setDisable(true);
        confirmAptBtn.setDisable(true);
    }

    public void orthopedistButtonPressed(MouseEvent e) throws IOException {
        list1Label.setText("Doctors");
        locationClicked = false;
        list2Label.setVisible(false);
        list2.setVisible(false);
        listOfDr.getItems().clear();
        medicalStaffChoices = ApplicationGUI.getSpecialtyDoctors("orthopedist");
        for (MedicalStaff s : medicalStaffChoices) {
            listOfDr.getItems().add(s.lName + " - " + s.location);
        }
        datePicker.setValue(null);
        datePicker.setDisable(true);
        hourPickedDropDwn.setValue(null);
        hourPickedDropDwn.setDisable(true);
        confirmAptBtn.setDisable(true);
    }

    public void surgeonButtonPressed(MouseEvent e) throws IOException {
        list1Label.setText("Doctors");
        locationClicked = false;
        list2Label.setVisible(false);
        list2.setVisible(false);
        listOfDr.getItems().clear();
        medicalStaffChoices = ApplicationGUI.getSpecialtyDoctors("surgeon");
        for (MedicalStaff s : medicalStaffChoices) {
            listOfDr.getItems().add(s.lName + " - " + s.location);
        }
        datePicker.setValue(null);
        datePicker.setDisable(true);
        hourPickedDropDwn.setValue(null);
        hourPickedDropDwn.setDisable(true);
        confirmAptBtn.setDisable(true);
    }

    public void locationBtnPressed(ActionEvent e) throws IOException {
        locationClicked = true;
        list2Label.setVisible(true);
        list2.setVisible(true);
        list1Label.setText("Hospital Location");
        list2Label.setText("Doctors");

        listOfDr.getItems().clear();
        hospitals = ApplicationGUI.getLocations();
        for (String s : hospitals) {
            listOfDr.getItems().add(s);
        }
        datePicker.setValue(null);
        datePicker.setDisable(true);
        hourPickedDropDwn.setValue(null);
        hourPickedDropDwn.setDisable(true);
        confirmAptBtn.setDisable(true);
    }
}