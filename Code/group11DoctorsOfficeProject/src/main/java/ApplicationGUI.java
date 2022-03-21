
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class ApplicationGUI extends Application {




    //test
    private static Connection connection;
    //TODO:Maybe change the names of these variables to avoid confusion? ex: sqlUsername
    private static String url = "jdbc:mysql://localhost:3306/cs440-project?allowPublicKeyRetrieval=true&useSSL=false";
    private static String mysqlUsername = "root";
    // 668909074Uic
    private static String mysqlPassword = "668909074Uic";
    private Stage stage;

    private static boolean isPatient = false;
    private static boolean isStaff = false;

    // getters
    public static boolean getIsPatient() {
        return isPatient;
    }

    public static boolean getIsStaff() {
        return isStaff;
    }


    //TODO: Put username and password for MySQL here

    public static void insertAccount(String firstName, String lastName, String email, String username, String password,
                                     String dateOfBirth, String address, String phoneNumber) {
        String insert = "INSERT INTO patients \n" +
                "VALUES('" + firstName + "','" + lastName + "','" + username + "','" + password + "', 25 ,'" + phoneNumber + "','" + dateOfBirth + "','" + address + "','" + email + "','62', '100', 'Notes...');";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            System.out.println("Here");
            statement.executeUpdate(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Vector<MedicalStaff> getSpecialtyDoctors(String requestedSpecialty)


    {

        Vector<MedicalStaff> returnVector = new Vector<>();
        MedicalStaff currStaff = new MedicalStaff();
        Vector<Appointment> appointments = new Vector<Appointment>();





        String input = "SELECT * FROM medicalprofessionals\n" +
                "WHERE specialty = '" + requestedSpecialty + "'";


        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(input);


            //now we should have a result set with all the doctors that match the specialty.

            while (result.next()) {

                String user = result.getString("username");
                String pass = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                String email = result.getString("email");
                String phoneNum = result.getString("contact_num");
                String location = result.getString("location");
                String specialty = result.getString("specialty");

                currStaff = new MedicalStaff(user, pass, fname, lname, email, phoneNum,appointments, location, specialty);

                returnVector.add(currStaff);



            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //should return a list of all the doctors fname and last name
        return returnVector;
    }

    public static Vector<Appointment> getAppointmentsByDate(MedicalStaff doctor, LocalDate date){
        try {
            String input = "SELECT * FROM appointments\n" +
                    "WHERE doctor_username = '" + doctor.username + "'\n" +
                    "AND date_time like '" + date + "%'\n" +
                    "ORDER BY date_time ASC";
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(input);

            Vector<Appointment> appointments = new Vector<Appointment>();

            while (result.next()) {
                String patientUser = result.getString("patient_username");
                String doctorUser = result.getString("doctor_username");
                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();
                String location = result.getString("location");
                Appointment currAppointment = new Appointment(patientUser, doctorUser, dateTime, location, " ");
                appointments.add(currAppointment);
            }

            return appointments;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Vector<MedicalStaff> getDoctorsBasedOnLocation(String inputtedLocation)


    {

        Vector<MedicalStaff> returnVector = new Vector<>();
        MedicalStaff currStaff = new MedicalStaff();
        Vector<Appointment> appointments = new Vector<Appointment>();





        String input = "SELECT * FROM medicalprofessionals\n" +
                "WHERE location = '" + inputtedLocation + "'";


        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(input);



            while (result.next()) {

                String user = result.getString("username");
                String pass = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                String email = result.getString("email");
                String phoneNum = result.getString("contact_num");
                String location = result.getString("location");
                String specialty = result.getString("specialty");

                currStaff = new MedicalStaff(user, pass, fname, lname, email, phoneNum,appointments, location, specialty);

                returnVector.add(currStaff);



            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //should return a list of all the doctors fname and last name
        return returnVector;
    }



    public static HashSet<String> getLocations()


    {

        HashSet<String> returnSet = new HashSet<>();
        Vector<Appointment> appointments = new Vector<Appointment>();





        String input = "SELECT * FROM medicalprofessionals\n";


        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(input);



            while (result.next()) {

                String location = result.getString("location");

                returnSet.add(location);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //should return a list of all the doctors fname and last name
        return returnSet;
    }


        public static void updatePatientNotes(String notes, String username)
    {
        String update = "UPDATE patients\n" +
                "SET notes = '" + notes + "'\n" +
                " WHERE username = '" + username + "'";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateEmail(String newEmail, String username)
    {
        String update = "UPDATE patients\n" +
                "SET email = '" + newEmail + "'\n" +
                " WHERE username = '" + username + "'";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateUsername(String newUsername, String username)
    {
        String update = "UPDATE patients\n" +
                "SET username = '" + newUsername + "'\n" +
                " WHERE username = '" + username + "'";
        String updateAppointments = "UPDATE appointments\n" +
                "SET patient_username = '" + newUsername + "'" + "\n" +
                "WHERE patient_username = '" + username + "'";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

            statement.executeUpdate(updateAppointments);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updatePassword(String password, String username)
    {
        String update = "UPDATE patients\n" +
                "SET password = '" + password + "'\n" +
                " WHERE username = '" + username + "'";
        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updatePhoneNumber(String phoneNumber, String username)
    {
        String update = "UPDATE patients\n" +
                "SET contact_num = '" + phoneNumber + "'\n" +
                " WHERE username = '" + username + "'";
        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static boolean checkIfUserExists(String usernameGiven) {
        String checkExists1 = "SELECT username FROM patients " +
                "WHERE username = '" + usernameGiven + "'";

        String checkExists2 = "SELECT username FROM medicalprofessionals " +
                "WHERE username = '" + usernameGiven + "'";
        try {

            // execute query to find a patient user
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
//            System.out.println("Checking if " + usernameGiven + " exists in patients");
            ResultSet result = statement.executeQuery(checkExists1);

            // if query can't find patient info, then check if medical staff
            if (result.next() == false) {
//                System.out.println("*** Not a patient ***");

                connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

//                System.out.println("Checking if " + usernameGiven + " exists in medicalprofessionals");
                ResultSet result2 = statement.executeQuery(checkExists2);

                if (result2.next() == false) {
//                    System.out.println("*** Not a medical professional ***");
                }
                // user is a medical staff
                else
                {
                    isStaff = true;
//                    System.out.println("User is a medical professional");
                }
            }
            // user is a patient
            else
            {
                isPatient = true;
//                System.out.println("User is a patient");
            }

            // if boolean flags are still false then user dosent exist
            if (!isPatient && !isStaff)
            {
                return false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //Change to patient return type once it's working
    public static MedicalStaff getStaffRecords(String username)
    {
//        System.out.println("in medical staff area");
        MedicalStaff currStaff = new MedicalStaff();
        //First grab doctor's appointments from appointments table
        String lookUpAppointments = "SELECT * FROM appointments\n" +
                "WHERE doctor_username = '" + username + "'\n" +
                "ORDER BY date_time ASC";

        String lookUpUser = "SELECT * FROM medicalprofessionals\n" +
                "WHERE username = '" + username + "'";
        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(lookUpAppointments);

            Vector<Appointment> appointments = new Vector<Appointment>();

            while (result.next()) {
                String patient = result.getString("patient_username");
                String doctor = result.getString("doctor_username");
                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();
                String location = result.getString("location");
                String description = result.getString("description");
                appointments.add(new Appointment(patient, doctor, dateTime, location, description));
                System.out.println("IN WHILE");
                System.out.println(patient + " " + doctor + " " + dateTime + " " + location + " " + description);
            }
            // access connection

//            System.out.println("Now grab user info");
            // make the statement from the query
            Statement statement2 = connection.createStatement();
            ResultSet result2 = statement2.executeQuery(lookUpUser);
            while (result2.next()) {
//                System.out.println("IN WHILE2");
                String user = result2.getString("username");
                String pass = result2.getString("password");
                String fname = result2.getString("fname");
                String lname = result2.getString("lname");
                String email = result2.getString("email");
                String phoneNum = result2.getString("contact_num");
                String location = result2.getString("location");
                String specialty = result2.getString("specialty");
                currStaff = new MedicalStaff(user, pass, fname, lname, email, phoneNum,appointments, location,specialty);

                System.out.println(user + " " + fname + " " + lname);
                System.out.println(currStaff.appointmentVector);
            }

//            System.out.println("Grabbed user info");
            return currStaff;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Patient getPatientRecords(String username){
        Patient currPatient = new Patient();
        //First grab patient appointments from appointments table
        String lookUpAppointments = "SELECT * FROM appointments\n" +
                "WHERE patient_username = '" + username + "'\n" +
                "AND date_time >= '" + LocalDateTime.now() +  "'\n" +
                "ORDER BY date_time ASC";

        String lookUpUser = "SELECT * FROM patients\n" +
                "WHERE username = '" + username + "'";
        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(lookUpAppointments);

            Vector<Appointment> appointments = new Vector<Appointment>();

            while (result.next()) {
                String patient = result.getString("patient_username");
                String doctor = result.getString("doctor_username");
                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();
                String location = result.getString("location");
                String description = result.getString("description");
                appointments.add(new Appointment(patient, doctor, dateTime, location, description));
//                System.out.println("IN WHILE");
//                System.out.println(patient + " " + doctor + " " + dateTime + " " + location + " " + description);
            }
            // access connection

//            System.out.println("Now grab user info");
            // make the statement from the query
            Statement statement2 = connection.createStatement();
            ResultSet result2 = statement2.executeQuery(lookUpUser);
            while (result2.next()) {
//                System.out.println("IN WHILE2");
                String user = result2.getString("username");
                String pass = result2.getString("password");
                String fname = result2.getString("fname");
                String lname = result2.getString("lname");
                String email = result2.getString("email");
                String phoneNum = result2.getString("contact_num");
                int age = result2.getInt("age");
                int height = result2.getInt("height");
                int weight = result2.getInt("weight");
                String dob = result2.getString("dob");
                String address = result2.getString("address");
                String notes = result2.getString("notes");
                currPatient = new Patient(user, pass, fname, lname, email, phoneNum, age, height, weight, dob, address, notes, appointments);

                System.out.println(user + " " + fname + " " + lname);
                System.out.println(currPatient.appointmentVector);
            }

//            System.out.println("Grabbed user info");
            return currPatient;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime convertToLDT(String dateTime)
    {
        String splitDate[] = dateTime.split(" ");
        String date = splitDate[0];
        String hour = splitDate[1];
//        System.out.println("test: " + splitDate[1]);
        if(splitDate[1].length() == 7)
            hour = "0" + splitDate[1];
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime2 = LocalDateTime.parse(date + " " + hour, formatter);
//        System.out.println(dateTime2);

        return dateTime2;
    }

    //Functionality to book appointments
    public static void bookAppointment(Patient patient, MedicalStaff doctor, LocalDateTime appointmentTime)
    {

//        System.out.println("Booking appointment for " + patient.username + "with " + doctor.username);
        String addAppointment = "INSERT INTO appointments \n" +
                "VALUES('" + patient.username + "', '" + doctor.username + "', '" + appointmentTime + "', '" + doctor.location + "', " + "'description');";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(addAppointment);

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAppointment(Patient patient, LocalDateTime appointmentTime)
    {
//        System.out.println("Deleting appointment for " + patient.username);
        String addAppointment = "DELETE FROM appointments \n" +
                "WHERE patient_username = '" + patient.username + "'\n" +
                "AND date_time = '" + appointmentTime + "'";

        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(addAppointment);

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static boolean checkIfPasswordMatches(String passwordGiven) {

        try {
            String checkMatches;
            if(isPatient) {
                checkMatches = "SELECT password FROM patients " +
                        "WHERE patients.password = '" + passwordGiven + "'";
            }
            else{
                checkMatches = "SELECT password FROM medicalprofessionals " +
                        "WHERE medicalprofessionals.password = '" + passwordGiven + "'";
            }

            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
            Statement statement = connection.createStatement();
//            System.out.println("Checking if " + passwordGiven + " matches");
            ResultSet result = statement.executeQuery(checkMatches);

            if (!result.isBeforeFirst()) {
                return false; //password does not match
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        // connect to MySQL
        try {
            // access connection
            connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            // make the statement from the query
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);

            // from the query make a dataset
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(connection);
//            System.out.println(dataset.getRowCount());
//            dataset.executeQuery(sql);
//            System.out.println("Connection established here\n");

//            System.out.println("Result = " + result.findColumn("age"));

//            while (result.next()) {
//                System.out.println("IN WHILE");
//                String id = result.getString("id");
//                String fname = result.getString("fname");
//                String lname = result.getString("lname");
//
//                System.out.println(id + " " + fname + " " + lname);
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJN1t_tDeuEmsRUsoyG83frY4&fields=name%2Crating%2Cformatted_phone_number&key=YOUR_API_KEY_HERE\n")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            // Read file fxml and draw interface.



            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/FXML/loginPage.fxml")));

//
            Scene s1 = new Scene(root);
            s1.getStylesheets().add("/css/mainStyle.css");



            primaryStage.setScene(s1);
            primaryStage.show();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    Platform.exit();
                    System.exit(0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}