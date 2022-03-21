import java.util.Vector;

abstract class User {

    //every account will have this.
    //private int uniqueID = 0; //not sure if needed but may make searching easier.
    public String username;
    public String password;
    public String fName;
    public String lName;
    public String email;
    public String cont_number;

    public Vector<Appointment> appointmentVector;




    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }




    //placeholder abstract function that all classes will need to initialize.
//    abstract void dog();








}
