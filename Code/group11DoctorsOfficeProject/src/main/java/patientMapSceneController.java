import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class patientMapSceneController implements Initializable {

    private Patient currPatient;
    @FXML
    private BorderPane root4;

    @FXML
    private WebView mapView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadMap();

    }

    public void loadMap()
    {
        String stringForHTMLPath = "";


        String[] checkForLocation = patientDashboardController.userPressedLocation.split(" ");


        System.out.println(checkForLocation[checkForLocation.length - 1]);
        if (checkForLocation[checkForLocation.length - 1].equals("RushHospital")){
            stringForHTMLPath = "/RushHospital.html";
        }

        if (checkForLocation[checkForLocation.length - 1].equals("NorthWestern")){
            stringForHTMLPath = "/NorthWestern.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("UIHealth")){
            stringForHTMLPath = "/UiHealth.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("SaintAnthonyHospital")){
            stringForHTMLPath = "/SaintAnthonyHospital.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("WeissMemorialHospital")){
            stringForHTMLPath = "/WeissMemorialHospital.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("KindredHospitalChicagoNorth")){
            stringForHTMLPath = "/KindredHospitalChicagoNorth.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("MercyHospital")){
            stringForHTMLPath = "/MercyHospital.html";
        }
        if (checkForLocation[checkForLocation.length - 1].equals("MountSinaiHospital")){
            stringForHTMLPath = "/MountSinaiHospital.html";
        }
        //fix the map here
        WebEngine engine = mapView.getEngine();
        URL url2 = this.getClass().getResource(stringForHTMLPath);
        engine.load(url2.toString());

    }

    public void initPatient(Patient currUser){
        currPatient = currUser;

    }



    public void homeBtnPressed(ActionEvent e) throws IOException {


        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientDashboard.fxml"));
        Parent root = loader.load(); //load view into parent
        patientDashboardController controller = loader.getController();
        controller.initPatient(currPatient);
        root4.getScene().setRoot(root);//update scene graph
    }

    public void calendarPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientCalendarScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientCalendarController controller = loader.getController();
        controller.initPatient(currPatient);
        root4.getScene().setRoot(root);//update scene graph

    }

    public void settingsPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/patientSettingsScene.fxml"));
        Parent root = loader.load(); //load view into parent
        patientSettingsController controller = loader.getController();
        controller.initPatient(currPatient);
        root4.getScene().setRoot(root);//update scene graph
    }

    public void logOutPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        root4.getScene().setRoot(root);//update scene graph
    }
}
