public class AccountActions {
    //TODO: Implement these checks into our registerpage1

    //We make this return an integer to return an error code, so we know which section is invalid
    //440 : Username too long, 420: No special characters allowed, 400: Username is valid
    public static int isValidUsername(String username){
        if(username.length() > 15 || username.length() < 4){
            System.out.println("Username must be between 8 and 15 characters");
            return 440;
        }
        for(char x: username.toCharArray()){
            if(!Character.isAlphabetic(x) && !Character.isDigit(x)){
                System.out.println("No special characters allowed in username");
                return 420;
            }
        }

        return 400;
    }





    //340 : Password too long, 320: Password cannot contain special characters
    //310 : Password does not contain at least 2 numbers, 300 : Password is valid
    public static int isValidPassword(String password){
        if(password.length() > 15 || password.length() < 8){
            return 340;
        }
        int numCounter = 0;
        for(char x: password.toCharArray()){
            if(!Character.isAlphabetic(x) && !Character.isDigit(x)){
                return 320;
            }
            else if(Character.isDigit(x)){
                numCounter++;
            }
        }
        if(numCounter < 2){
            return 310;
        }
        else
            return 300;
    }
    //Return true if successful, false if unsuccessful
    public static boolean verifyUser(String username, String password, UserType userType) {
        switch (userType) {
            case PATIENT:
                System.out.println("User is a patient");
                //Will implement lookup here once we finalize how our database will look
                break;
            case STAFF:
                System.out.println("User is a doctor");
                break;
        }
        return false;//TODO: Once finalized implement correct return
    }

    public static void login(String username, String password, UserType userType) {

        if (verifyUser(username, password, userType)) {
            switch (userType) {
                case PATIENT:
                    System.out.println("Logging in as a patient");
                    break;
                case STAFF:
                    System.out.println("Logging in as a doctor");
                    break;
            }
        } else {
            System.out.println("Invalid username or password");
        }

    }

    //Return true if successful, false if unsuccessful
    public static boolean createAccount(String username, String password, UserType userType) {
        switch (userType) {
            case PATIENT:
                System.out.println("Creating account for patient");
                //Will implement database insertion here once we finalize how our database will look
                break;
            case STAFF:
                System.out.println("Creating account for doctor");
                break;
        }
        return false;//TODO: Once finalized implement correct return
    }

    //Return true if successful, false if unsuccessful
    public boolean resetPassword(String username, String prevPass, UserType userType) {
        switch (userType) {
            case PATIENT:
                System.out.println("resetting password for patient");
                //Will implement database insertion here once we finalize how our database will look
                break;
            case STAFF:
                System.out.println("resetting password for doctor");
                break;
        }
        return false;//TODO: Once finalized implement correct return
    }

    public boolean changeEmail(String username, String password, UserType userType){
        switch (userType) {
            case PATIENT:
                System.out.println("resetting password for patient");
                //Will implement database insertion here once we finalize how our database will look
                break;
            case STAFF:
                System.out.println("resetting password for doctor");
                break;
        }
        return false;
    }
}
