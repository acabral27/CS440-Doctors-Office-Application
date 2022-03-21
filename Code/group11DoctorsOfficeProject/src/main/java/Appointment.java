import java.time.LocalDateTime;


public class Appointment {
    String patientUsername;
    String doctorUsername;
    LocalDateTime appointTime;
    String appointmentLocation;
    String appointmentDescription;


    public Appointment(String patient, String doctor, LocalDateTime dateTime, String location, String description) {
        patientUsername = patient;
        doctorUsername = doctor;
        appointTime = dateTime;
        appointmentLocation = location;
        appointmentDescription = description;
    }
}
