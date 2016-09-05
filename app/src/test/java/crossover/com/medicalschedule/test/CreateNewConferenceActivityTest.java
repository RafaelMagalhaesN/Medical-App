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

import crossover.com.medicalschedule.CreateNewConferenceActivity;
import crossover.com.medicalschedule.R;

import static org.junit.Assert.*;

/**
 * Created by rafae on 31/08/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateNewConferenceActivityTest {

    CreateNewConferenceActivity createNewConferenceActivity;
    String title, date, hour, address, spoke;
    Editable editable;

    @Before
    public void setUp() throws Exception {
        createNewConferenceActivity = Mockito.spy(CreateNewConferenceActivity.class);
        editable = Mockito.mock(Editable.class);
        title = "MEDICAL COFERENCE";
        date = "00/00/00";
        hour = "00:00";
        address = "NY, USA";
        spoke = "TOPIC 1, TOPIC 2";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTitleOf() throws Exception {
        final EditText title = Mockito.mock(EditText.class);
        Mockito.doReturn(title).when(createNewConferenceActivity).findViewById(R.id.editTextTitle);

        Mockito.when(title.getText()).thenReturn(editable);
        Mockito.when(title.getText().toString()).thenReturn(this.title);

        assertEquals(this.title, title.getText().toString());
    }

    @Test
    public void testGetDateOf() throws Exception {
        final EditText date = Mockito.mock(EditText.class);
        Mockito.doReturn(date).when(createNewConferenceActivity).findViewById(R.id.editTextDate);

        Mockito.when(date.getText()).thenReturn(editable);
        Mockito.when(date.getText().toString()).thenReturn(this.date);

        assertEquals(this.date, date.getText().toString());
    }

    @Test
    public void testGetHourOf() throws Exception {
        final EditText hour = Mockito.mock(EditText.class);
        Mockito.doReturn(hour).when(createNewConferenceActivity).findViewById(R.id.editTextHour);

        Mockito.when(hour.getText()).thenReturn(editable);
        Mockito.when(hour.getText().toString()).thenReturn(this.hour);

        assertEquals(this.hour, hour.getText().toString());
    }

    @Test
    public void testGetAddressOf() throws Exception {
        final EditText address = Mockito.mock(EditText.class);
        Mockito.doReturn(address).when(createNewConferenceActivity).findViewById(R.id.editTextAddress);

        Mockito.when(address.getText()).thenReturn(editable);
        Mockito.when(address.getText().toString()).thenReturn(this.address);

        assertEquals(this.address, address.getText().toString());
    }

    @Test
    public void testGetSpokeOf() throws Exception {
        final EditText spoke = Mockito.mock(EditText.class);
        Mockito.doReturn(spoke).when(createNewConferenceActivity).findViewById(R.id.editTextSpoke);

        Mockito.when(spoke.getText()).thenReturn(editable);
        Mockito.when(spoke.getText().toString()).thenReturn(this.spoke);

        assertEquals(this.spoke, spoke.getText().toString());
    }

    @Test
    public void testIsEditTextEmpty() throws Exception {
        final EditText title = Mockito.mock(EditText.class);
        final EditText date = Mockito.mock(EditText.class);
        final EditText hour = Mockito.mock(EditText.class);
        final EditText address = Mockito.mock(EditText.class);
        final EditText spoke = Mockito.mock(EditText.class);
        Mockito.doReturn(title).when(createNewConferenceActivity).findViewById(R.id.editTextTitle);
        Mockito.doReturn(date).when(createNewConferenceActivity).findViewById(R.id.editTextDate);
        Mockito.doReturn(hour).when(createNewConferenceActivity).findViewById(R.id.editTextHour);
        Mockito.doReturn(address).when(createNewConferenceActivity).findViewById(R.id.editTextAddress);
        Mockito.doReturn(spoke).when(createNewConferenceActivity).findViewById(R.id.editTextSpoke);

        Mockito.when(title.getText()).thenReturn(editable);
        Mockito.when(title.getText().toString()).thenReturn("");
        Mockito.when(date.getText()).thenReturn(editable);
        Mockito.when(date.getText().toString()).thenReturn("");
        Mockito.when(hour.getText()).thenReturn(editable);
        Mockito.when(hour.getText().toString()).thenReturn("");
        Mockito.when(address.getText()).thenReturn(editable);
        Mockito.when(address.getText().toString()).thenReturn("");
        Mockito.when(spoke.getText()).thenReturn(editable);
        Mockito.when(spoke.getText().toString()).thenReturn("");


        assertTrue(createNewConferenceActivity.isEditTextEmpty());
    }

    @Test
    public void testOnCreateNewConference() throws Exception {
        Button btnOnNewConference = Mockito.mock(Button.class);
        Mockito.doReturn(btnOnNewConference).when(createNewConferenceActivity).findViewById(R.id.button);

        btnOnNewConference.performClick();
        Mockito.verify(btnOnNewConference).performClick();
    }
}