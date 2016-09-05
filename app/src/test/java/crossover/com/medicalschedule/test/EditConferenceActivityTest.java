package crossover.com.medicalschedule.test;

import android.text.Editable;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import crossover.com.medicalschedule.EditConferenceActivity;
import crossover.com.medicalschedule.R;

import static org.junit.Assert.*;

/**
 * Created by rafae on 01/09/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class EditConferenceActivityTest {

    EditConferenceActivity editConferenceActivity;
    String _date, _hour, _addess, _spoke;

    @Test
    public void testGetTitleOf() throws Exception {
        editConferenceActivity = Mockito.mock(EditConferenceActivity.class);
        _date = "00/00/0000";
        _hour = "00:00";
        _addess = "NY, USA";
        _spoke = "TOPIC 1, TOPIC2";
    }

    @Test
    public void testGetDateOf() throws Exception {
        final EditText date = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.when(date.getText()).thenReturn(editable);
        Mockito.when(date.getText().toString()).thenReturn(this._date);

        assertEquals(_date,date.getText().toString());
    }

    @Test
    public void testGetHourOf() throws Exception {
        final EditText hour = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.when(hour.getText()).thenReturn(editable);
        Mockito.when(hour.getText().toString()).thenReturn(this._hour);

        assertEquals(_hour,hour.getText().toString());
    }

    @Test
    public void testGetAddressOf() throws Exception {
        final EditText address = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.when(address.getText()).thenReturn(editable);
        Mockito.when(address.getText().toString()).thenReturn(this._addess);

        assertEquals(_addess,address.getText().toString());
    }

    @Test
    public void testGetSpokeOf() throws Exception {
        final EditText date = Mockito.mock(EditText.class);
        final Editable editable = Mockito.mock(Editable.class);
        Mockito.when(date.getText()).thenReturn(editable);
        Mockito.when(date.getText().toString()).thenReturn(this._spoke);

        assertEquals(_spoke,date.getText().toString());
    }

}