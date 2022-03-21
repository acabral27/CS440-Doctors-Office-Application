import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountActionsTest {

    @Test
    void isValidUsername() {
        String goodUsername = "maggydaley123";
        String badUsername = "spiderpig1234%";
        String tooLongUsername = "thisusernameiswaytoolong";

        assertEquals(true, AccountActions.isValidUsername(goodUsername));
        assertEquals(false, AccountActions.isValidUsername(badUsername));
        assertEquals(false, AccountActions.isValidUsername(tooLongUsername));
    }

    @Test
    void isValidPassword() {
        String goodPass = "goodpassword12";
        String badPass = "badpassword%";
        String notEnoughNums = "badpassword1";
        String tooLong = "thispasswordistoolong12";

        assertEquals(true, AccountActions.isValidPassword(goodPass));
        assertEquals(false, AccountActions.isValidPassword(badPass));
        assertEquals(false, AccountActions.isValidPassword(notEnoughNums));
        assertEquals(false, AccountActions.isValidPassword(tooLong));
    }

    @Test
    void verifyUser() {
    }

    @Test
    void login() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void changeEmail() {
    }
}