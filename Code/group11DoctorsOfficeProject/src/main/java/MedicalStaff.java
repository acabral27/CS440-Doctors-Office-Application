import java.util.Vector;

public class MedicalStaff extends User {

    private int medical_id;
    String location;
    String specialty;

    public MedicalStaff(){

    }
    public MedicalStaff(String user, String pass, String fname, String lname, String staffEmail,
                        String phoneNum, Vector<Appointment> appointments, String doctorLocation, String doctorSpecialty) {
        username = user;
        password = pass;
        fName = fname;
        lName = lname;
        email = staffEmail;
        cont_number = phoneNum;
        location = doctorLocation;
        appointmentVector = appointments;
        specialty = doctorSpecialty;
    }

    public MedicalStaff(String doctorLocation) {
        location = doctorLocation;
    }

    public Vector<String> getPatientUsernames()
    {
        Vector<String> patients = new Vector();
        for (Appointment a: appointmentVector)
        {
            if(patients.contains(a.patientUsername))
                continue;
            else
                patients.add(a.patientUsername);
        }
        return patients;
    }
}
