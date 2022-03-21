import java.sql.Date;
import java.util.Vector;


public class Patient extends User{

    private int p_id;
    public int age;
    public int height;
    public int weight;
    public String dob;
    public String p_address;
    public String patientNotes;
    //public Patient_Insurance p_ins = new Patient_Insurance();

    public Patient(){

    }

    public Patient(String user, String pass, String firstName,
                   String lastName, String userEmail, String contactNum, int patientAge, int pHeight,
                   int pWeight, String birthDate, String patientAddress, String pNotes, Vector<Appointment> appointments)
    {
        username = user;
        password = pass;
        fName = firstName;
        lName = lastName;
        email = userEmail;
        cont_number = contactNum;
        age = patientAge;
        height = pHeight;
        weight = pWeight;
        dob = birthDate;
        p_address = patientAddress;
        appointmentVector = appointments;
        patientNotes = pNotes;
    }

    //maybe add stuff for constructor here







}
