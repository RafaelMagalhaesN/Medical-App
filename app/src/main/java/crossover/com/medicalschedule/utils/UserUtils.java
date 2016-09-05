package crossover.com.medicalschedule.utils;

/**
 * Created by rafae on 31/08/2016.
 */
public class UserUtils {

    private static String userName;
    private static String isAdmin;

    public UserUtils(String userName, String isAdmin){
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getIsAdmin() {
        return isAdmin;
    }
}
