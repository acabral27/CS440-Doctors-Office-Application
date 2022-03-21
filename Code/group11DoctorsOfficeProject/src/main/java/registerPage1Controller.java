import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class registerPage1Controller implements Initializable {

    @FXML
    private BorderPane root2;

    @FXML
    private TextField fnField;

    @FXML
    private TextField lnField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField pwField;

    @FXML
    private TextField cpwField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField dobField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField insField;

    @FXML
    private TextField pnField;

    @FXML
    private Button createAccountBtn;

    @FXML
    private Button backBtn;

    private Boolean fn = false;
    private Boolean ln = false;
    private Boolean email = false;
    private Boolean username = false;
    private Boolean pw = false;
    private Boolean cpw = false;
    private Boolean dob = false;
    private Boolean addr = false;
    private Boolean ins = false;
    private Boolean pn = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createAccountBtn.setDisable(true);

    }


    public void checkBools() {
        if ((fn) && (ln) && (email) && (username) && (pw) && (cpw) && (dob) && (addr) && (pn)) {

            createAccountBtn.setDisable(false);

        } else {
            createAccountBtn.setDisable(true);
        }
    }


    public void firstNameEntered(KeyEvent e) throws IOException {

        String input = fnField.getText();
        if (!Objects.equals(input, "")) {
            fn = true;
        } else {
            fn = false;
        }

        checkBools();

    }

    public void lastNameEntered(KeyEvent e) throws IOException {
        String input = lnField.getText();
        if (!Objects.equals(input, "")) {
            ln = true;
        } else {
            ln = false;
        }

        checkBools();
    }

    public void emailEntered(KeyEvent e) throws IOException {
        String input = emailField.getText();
        if (!Objects.equals(input, "")) {
            email = true;
        } else {
            email = false;
        }

        checkBools();
    }

    public void usernameEntered(KeyEvent e) throws IOException {
        String input = usernameTextField.getText();
        if (!Objects.equals(input, "")) {
            username = true;
        } else {
            username = false;
        }

        checkBools();
    }

    public void passwordEntered(KeyEvent e) throws IOException {
        String input = pwField.getText();
        if (!Objects.equals(input, "")) {
            pw = true;
        } else {
            pw = false;
        }


        checkBools();
    }

    public void confirmPasswordEntered(KeyEvent e) throws IOException {
        String input = cpwField.getText();
        if (!Objects.equals(input, "")) {
            cpw = true;
        } else {
            cpw = false;
        }

        checkBools();
    }

    public void dobEntered(KeyEvent e) throws IOException {
        String input = dobField.getText();
        if (!Objects.equals(input, "")) {
            dob = true;
        } else {
            dob = false;
        }

        checkBools();


    }

    public void addressEntered(KeyEvent e) throws IOException {
        String input = addressField.getText();
        if (!Objects.equals(input, "")) {
            addr = true;
        } else {
            addr = false;
        }

        checkBools();

    }

    public void insEntered(KeyEvent e) throws IOException {

        String input = insField.getText();
        if (!Objects.equals(input, "")) {
            ins = true;
        } else {
            ins = false;
        }

        checkBools();

    }

    public void pnEntered(KeyEvent e) throws IOException {
        String input = pnField.getText();
        if (!Objects.equals(input, "")) {
            pn = true;
        } else {
            pn = false;
        }

        checkBools();

    }

    public void createAccountBtnPressed(ActionEvent e) throws IOException {
        switch (AccountActions.isValidUsername(usernameTextField.getText())) {
            case 440:
                errorLabel.setText("Username must be between 8 and 15 chars");
                break;

            case 420:
                errorLabel.setText("Special characters not allowed in username");
                break;
        }

        switch (AccountActions.isValidPassword(pwField.getText())) {
            case 340:
                errorLabel.setText("Password must be between 8 and 15 chars");
                break;

            case 320:
                errorLabel.setText("Special characters not allowed in password");
                break;

            case 310:
                errorLabel.setText("Password must contain at least 2 numbers");
                break;
        }

        if (!pwField.getText().equals(cpwField.getText())) {
            System.out.println("Passwords must match");
            errorLabel.setText("Passwords must match");
        }


        if(ApplicationGUI.checkIfUserExists(usernameTextField.getText())){
            System.out.println(usernameTextField.getText());
            //it could be because the text fields are the same name maybe
            errorLabel.setText("Username already exists");
        }
        //Both username and password are valid
        else if (AccountActions.isValidUsername(usernameTextField.getText()) == 400
                && AccountActions.isValidPassword(pwField.getText()) == 300
                && pwField.getText().equals(cpwField.getText())) {
            System.out.println("Inserting");
            ApplicationGUI.insertAccount(fnField.getText(), lnField.getText(), emailField.getText(), usernameTextField.getText(), pwField.getText(), dobField.getText(), addressField.getText(), pnField.getText());
            System.out.println("after insertion");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
            Parent root = loader.load(); //load view into parent
            root2.getScene().setRoot(root);//update scene graph
        }

    }

    public void backBtnPressed(ActionEvent e) throws IOException {

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/loginPage.fxml"));
        Parent root = loader.load(); //load view into parent

        root2.getScene().setRoot(root);//update scene graph

    }



}
