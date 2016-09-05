package crossover.com.medicalschedule.test.daoTest;

import android.database.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;

import static org.junit.Assert.*;

/**
 * Created by rafae on 01/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerDAOTest {

    ControllerDAO controllerDAO;
    List<User> listUsers;
    User user;
    Conference conference;
    List<Conference> listConference;
    List<String> titles;
    String username, password;

    @Before
    public void setUp() throws SQLException {
        controllerDAO = Mockito.mock(ControllerDAO.class);
        user = Mockito.mock(User.class);
        listUsers = Mockito.mock(List.class);
        listConference = Mockito.mock(List.class);
        titles = Mockito.mock(List.class);


        conference = Mockito.mock(Conference.class);
        conference.setHour("00:00");
        conference.setTitle("TITLE 1");
        conference.setDate("00/00/0000");
        titles.add("TITLE 1");
        username = "RAFAEL";
        password = "PASS";
        user.setUsername(username);
        user.setPassword(password);
        listUsers.add(user);
    }


    @Test
    public void testOpen() throws SQLException {
        Mockito.doThrow(new SQLException()).doNothing().when(controllerDAO).open();
    }


    @Test
    public void testClose() throws SQLException {
        Mockito.doThrow(new SQLException()).doNothing().when(controllerDAO).close();
    }

    @Test
    public void testReadOneUser() throws Exception {
        Mockito.when(controllerDAO.readOneUser(username)).thenReturn(user);
        assertEquals(controllerDAO.readOneUser(username), user);
    }

    @Test
    public void testReadAllUsers() throws Exception {
        Mockito.when(controllerDAO.readAllUsers()).thenReturn(listUsers);
        assertEquals(controllerDAO.readAllUsers(), listUsers);
    }


    @Test
    public void testCreateNewUser() throws Exception {
        Mockito.when(controllerDAO.createNewUser(user)).thenReturn(true);
        assertTrue(controllerDAO.createNewUser(user));
    }

    @Test
    public void testCreateNewConference() throws Exception {
        Mockito.when(controllerDAO.createNewConference(conference)).thenReturn(true);
        assertTrue(controllerDAO.createNewConference(conference));
    }

    @Test
    public void testReadAllConferences() throws Exception {
        Mockito.when(controllerDAO.readAllConferences()).thenReturn(listConference);
        assertEquals(controllerDAO.readAllConferences(),listConference);
    }


    @Test
    public void testReadAllConferenceTitle() throws Exception {
        Mockito.when(controllerDAO.readAllConferenceTitle(titles)).thenReturn(listConference);
        assertEquals(controllerDAO.readAllConferenceTitle(titles),listConference);
    }
}