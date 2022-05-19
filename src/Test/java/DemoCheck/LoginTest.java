package DemoCheck;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }


    @Test
    void initalWeb() {
        login.initalWeb();
    }
}