package crossover.com.medicalschedule.test;

import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import crossover.com.medicalschedule.LoginActivity;
import crossover.com.medicalschedule.NewUserActivity;
import crossover.com.medicalschedule.R;

import static org.junit.Assert.*;

/**
 * Created by rafae on 30/08/2016.
 */
public class NewUserActivityTest {

    String username, password;
    NewUserActivity newUserActivity;
    @Before
    public void setUp() throws Exception {
        username = "RAFAEL";
        password = "PASS";
        newUserActivity = Mockito.mock(NewUserActivity.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testeGetPassword(){
        final EditText password = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);

        Mockito.doReturn(password).when(newUserActivity).findViewById(R.id.etSenha);

        Mockito.when(password.getText()).thenReturn(editable);
        Mockito.when(password.getText().toString()).thenReturn(this.password);

        assertEquals(this.password,password.getText().toString());
    }

    @Test
    public void testGetUserName(){
        final EditText username = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);

        Mockito.doReturn(username).when(newUserActivity).findViewById(R.id.etUsername);

        Mockito.when(username.getText()).thenReturn(editable);
        Mockito.when(username.getText().toString()).thenReturn(this.username);

        assertEquals(this.username,username.getText().toString());
    }

    @Test
    public void testFieldsEmpty(){
        final EditText login = Mockito.mock(EditText.class);
        final EditText password = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.doReturn(login).when(newUserActivity).findViewById(R.id.etLogin);
        Mockito.doReturn(password).when(newUserActivity).findViewById(R.id.etSenha);

        Mockito.when(login.getText()).thenReturn(editable);
        Mockito.when(password.getText()).thenReturn(editable);

        Mockito.when(login.getText().toString()).thenReturn("");
        Mockito.when(password.getText().toString()).thenReturn("");

        Mockito.when(newUserActivity.getPassword()).thenReturn("");
        Mockito.when(newUserActivity.getLogin()).thenReturn("");
        Mockito.when(newUserActivity.isEditTextEmpty()).thenReturn(true);
        assertTrue(newUserActivity.isEditTextEmpty());
    }

    @Test
    public void testRegister() throws Exception {
        Button btnOnRegister = Mockito.mock(Button.class);
        Mockito.doReturn(btnOnRegister).when(newUserActivity).findViewById(R.id.onCreateUser);

        btnOnRegister.performClick();
        Mockito.verify(btnOnRegister).performClick();
    }

}