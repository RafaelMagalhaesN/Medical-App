package crossover.com.medicalschedule.test;

import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import crossover.com.medicalschedule.LoginActivity;
import crossover.com.medicalschedule.R;

import static org.junit.Assert.*;

/**
 * Created by rafae on 30/08/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {

    LoginActivity loginActivity;
    String username, password;
    @Before
    public void setUp() throws Exception {
        loginActivity = Mockito.spy(LoginActivity.class);
        username = "RAFAEL";
        password = "PASS";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnLogin() throws Exception {
        Button btnOnLogin = Mockito.mock(Button.class);
        Mockito.doReturn(btnOnLogin).when(loginActivity).findViewById(R.id.login);

        btnOnLogin.performClick();
        Mockito.verify(btnOnLogin).performClick();

    }

    @Test
    public void testOnNewUser() throws Exception {
        Button btnOnNewUser = Mockito.mock(Button.class);
        Mockito.doReturn(btnOnNewUser).when(loginActivity).findViewById(R.id.newUser);

        btnOnNewUser.performClick();
        Mockito.verify(btnOnNewUser).performClick();
    }

    @Test
    public void testGetPassword() throws Exception {
        final EditText password = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);

        Mockito.doReturn(password).when(loginActivity).findViewById(R.id.etSenha);

        Mockito.when(password.getText()).thenReturn(editable);
        Mockito.when(password.getText().toString()).thenReturn(this.password);

        assertEquals(this.password,password.getText().toString());
    }

    @Test
    public void  testGetLogin() throws Exception {
        final EditText login = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);

        Mockito.doReturn(login).when(loginActivity).findViewById(R.id.etLogin);

        Mockito.when(login.getText()).thenReturn(editable);
        Mockito.when(login.getText().toString()).thenReturn(username);

        assertEquals(username,login.getText().toString());
    }

    @Test
    public void testIsEditTextEmpty() throws Exception {
        final EditText login = Mockito.mock(EditText.class);
        final EditText password = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.doReturn(login).when(loginActivity).findViewById(R.id.etLogin);
        Mockito.doReturn(password).when(loginActivity).findViewById(R.id.etSenha);

        Mockito.when(login.getText()).thenReturn(editable);
        Mockito.when(password.getText()).thenReturn(editable);
        Mockito.when(login.getText().toString()).thenReturn("");
        Mockito.when(password.getText().toString()).thenReturn("");

        assertTrue(loginActivity.isEditTextEmpty());

    }
}