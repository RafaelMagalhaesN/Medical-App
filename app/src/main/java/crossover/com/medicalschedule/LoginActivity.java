package crossover.com.medicalschedule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.models.User;
import crossover.com.medicalschedule.utils.UserUtils;

public class LoginActivity extends AppCompatActivity {


    private ControllerDAO _controllerDAO = new ControllerDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK",null);
        builder.show();
    }

    public void onNewUser(View v){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLogin(View v) {
        if(!isEditTextEmpty()){
            if(successLogin()){
                User user = _controllerDAO.readOneUser(getLogin());
                Intent intent = new Intent(this, ConferenceActivity.class);
                intent.putExtra("username", user.getUsername());
                intent.putExtra("isAdmin", user.getConferenceAdmin());
                new UserUtils(user.getUsername(), user.getConferenceAdmin());
                startActivity(intent);
            }else{
                showMessage("Info","User/Password incorrect!", this);
            }
        }else{
            showMessage("Info","Fields empty!", this);
        }
    }


    public String getLogin() {
        EditText login = (EditText) findViewById(R.id.etLogin);
        return login.getText().toString().trim();
    }

    public String getPassword() {
        EditText password = (EditText) findViewById(R.id.etSenha);
        return password.getText().toString();
    }

    public boolean isEditTextEmpty() {
        if (getLogin().equals("") || getPassword().equals("")) {
            return true;
        }
        return false;
    }

    private boolean successLogin() {
        try {
            User user = _controllerDAO.readOneUser(getLogin());
            if (getLogin().equalsIgnoreCase(user.getUsername()) && getPassword().equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException error) {
           return false;
        }
    }
}
