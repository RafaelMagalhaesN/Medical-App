package crossover.com.medicalschedule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.models.User;

public class NewUserActivity extends AppCompatActivity {


    private ControllerDAO _controllerDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        _controllerDAO = new ControllerDAO(getBaseContext());

    }

    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setNegativeButton("OK",null);
        builder.setMessage(message);
        builder.show();
    }

    private String whoYouAre(){
        RadioGroup radioYoureGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedRadioId = radioYoureGroup.getCheckedRadioButtonId();
        switch (selectedRadioId) {
            case R.id.iamDoctor:
                return "0";

            case R.id.iamConferenceAdmin:
                return "1";

            default:
                return "0";
        }
    }


    public String getLogin() {
        EditText login = (EditText) findViewById(R.id.etUsername);
        return login.getText().toString();
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

    public void onCreateUser(View v){
        if(!isEditTextEmpty()){
            User user = new User(getLogin(), getPassword(), whoYouAre(), "");
            if(_controllerDAO.createNewUser(user)) {
                showMessage("Info", "User created!", this);
            }else{
                showMessage("Error", "User not created!", this);
            }
        }else{
            showMessage("Info","Fields empty!", this);
        }

    }

    public void onCancel(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
