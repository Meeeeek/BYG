package michaelkim.byg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    TextView STUDENTREG, MENTORREG, STAFFREG;

    TextView regiTextTitle, rName, rEmail, rPassword, rGrade, rBirthday, rPhone, rAddress, rCity, rState, rZip;
    EditText rNameField, rEmailField, rPasswordField, rAddressField, rPhoneField, rCityField, rZipField;
    Spinner rGradeSpinner, rStateSpinner;
    Button create, rBirthdayField;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    Fragment fragment = null;

    DatabaseReference databaseMentors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        STUDENTREG = (TextView) findViewById(R.id.STUDENTREG);
        MENTORREG = (TextView) findViewById(R.id.MENTORREG);
        STAFFREG = (TextView) findViewById(R.id.STAFFREG);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        regiTextTitle = (TextView) findViewById(R.id.regTypeText);
        rName = (TextView) findViewById(R.id.rName);
        rNameField = (EditText) findViewById(R.id.rNameField);
        rEmail = (TextView) findViewById(R.id.rEmail);
        rEmailField = (EditText) findViewById(R.id.rEmailField);
        rPassword = (TextView) findViewById(R.id.rPassword);
        rPasswordField = (EditText) findViewById(R.id.rPasswordField);
        rGrade = (TextView) findViewById(R.id.rGrade);
        rGradeSpinner = (Spinner) findViewById(R.id.rGradeSpinner);
        rBirthday = (TextView) findViewById(R.id.rBirthday);
        rBirthdayField = (Button) findViewById(R.id.rBirthdayField);
        rAddress = (TextView) findViewById(R.id.rAddress);
        rAddressField = (EditText) findViewById(R.id.rAddressField);
        rPhone = (TextView) findViewById(R.id.rPhone);
        rPhoneField = (EditText) findViewById(R.id.rPhoneField);
        rCity = (TextView) findViewById(R.id.rCity);
        rCityField = (EditText) findViewById(R.id.rCityField);
        rState = (TextView) findViewById(R.id.rState);
        rStateSpinner = (Spinner) findViewById(R.id.rStateSpinner);
        rZip = (TextView) findViewById(R.id.rZip);
        rZipField = (EditText) findViewById(R.id.rZipField);
        create = (Button) findViewById(R.id.createAccount);

        databaseMentors = FirebaseDatabase.getInstance().getReference("Mentor");

        STUDENTREG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STUDENTREG.setVisibility(View.INVISIBLE);
                MENTORREG.setVisibility(View.INVISIBLE);
                STAFFREG.setVisibility(View.INVISIBLE);

                regiTextTitle.setVisibility(View.VISIBLE);
                regiTextTitle.setText("STUDENT REGISTRATION");
                rName.setVisibility(View.VISIBLE);
                rNameField.setVisibility(View.VISIBLE);
                rEmail.setVisibility(View.VISIBLE);
                rEmailField.setVisibility(View.VISIBLE);
                rPassword.setVisibility(View.VISIBLE);
                rPasswordField.setVisibility(View.VISIBLE);
                rGrade.setVisibility(View.VISIBLE);
                rGradeSpinner.setVisibility(View.VISIBLE);
                rBirthday.setVisibility(View.VISIBLE);
                rBirthdayField.setVisibility(View.VISIBLE);
                rAddress.setVisibility(View.VISIBLE);
                rAddressField.setVisibility(View.VISIBLE);
                rPhone.setVisibility(View.VISIBLE);
                rPhoneField.setVisibility(View.VISIBLE);
                rCity.setVisibility(View.VISIBLE);
                rCityField.setVisibility(View.VISIBLE);
                rState.setVisibility(View.VISIBLE);
                rStateSpinner.setVisibility(View.VISIBLE);
                rZip.setVisibility(View.VISIBLE);
                rZipField.setVisibility(View.VISIBLE);
                create.setVisibility(View.VISIBLE);
            }
        });

        MENTORREG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STUDENTREG.setVisibility(View.INVISIBLE);
                MENTORREG.setVisibility(View.INVISIBLE);
                STAFFREG.setVisibility(View.INVISIBLE);

                regiTextTitle.setVisibility(View.VISIBLE);
                regiTextTitle.setText("MENTOR REGISTRATION");

                rName.setVisibility(View.VISIBLE);
                rNameField.setVisibility(View.VISIBLE);

                rEmail.setVisibility(View.VISIBLE);
                rEmailField.setVisibility(View.VISIBLE);

                rPassword.setVisibility(View.VISIBLE);
                rPasswordField.setVisibility(View.VISIBLE);

                rGrade.setVisibility(View.VISIBLE);
                rGradeSpinner.setVisibility(View.VISIBLE);
                String[] gradeArray = {"6B", "6G", "7B", "7G", "8B", "8G", "9B", "9G", "10B", "10G", "11B", "11G", "12B", "12G"};
                ArrayAdapter gradeAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, gradeArray);
                rGradeSpinner.setAdapter(gradeAdapter);

                rBirthday.setVisibility(View.VISIBLE);
                rBirthdayField.setVisibility(View.VISIBLE);

                rAddress.setVisibility(View.VISIBLE);
                rAddressField.setVisibility(View.VISIBLE);

                rPhone.setVisibility(View.VISIBLE);
                rPhoneField.setVisibility(View.VISIBLE);

                rCity.setVisibility(View.VISIBLE);
                rCityField.setVisibility(View.VISIBLE);

                rState.setVisibility(View.VISIBLE);
                rStateSpinner.setVisibility(View.VISIBLE);
                String[] stateArray = {"NJ", "NY"};
                ArrayAdapter stateSpinner = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stateArray);
                rStateSpinner.setAdapter(stateSpinner);

                rZip.setVisibility(View.VISIBLE);
                rZipField.setVisibility(View.VISIBLE);

                create.setVisibility(View.VISIBLE);
            }
        });

        STAFFREG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STUDENTREG.setVisibility(View.INVISIBLE);
                MENTORREG.setVisibility(View.INVISIBLE);
                STAFFREG.setVisibility(View.INVISIBLE);

                regiTextTitle.setVisibility(View.VISIBLE);
                regiTextTitle.setText("STAFF REGISTRATION");
                rName.setVisibility(View.VISIBLE);
                rNameField.setVisibility(View.VISIBLE);
                rEmail.setVisibility(View.VISIBLE);
                rEmailField.setVisibility(View.VISIBLE);
                rPassword.setVisibility(View.VISIBLE);
                rPasswordField.setVisibility(View.VISIBLE);
                rGrade.setVisibility(View.VISIBLE);
                rGradeSpinner.setVisibility(View.VISIBLE);
                rBirthday.setVisibility(View.VISIBLE);
                rBirthdayField.setVisibility(View.VISIBLE);
                rAddress.setVisibility(View.VISIBLE);
                rAddressField.setVisibility(View.VISIBLE);
                rPhone.setVisibility(View.VISIBLE);
                rPhoneField.setVisibility(View.VISIBLE);
                rCity.setVisibility(View.VISIBLE);
                rCityField.setVisibility(View.VISIBLE);
                rState.setVisibility(View.VISIBLE);
                rStateSpinner.setVisibility(View.VISIBLE);
                rZip.setVisibility(View.VISIBLE);
                rZipField.setVisibility(View.VISIBLE);
                create.setVisibility(View.VISIBLE);
            }
        });

    }

    public void toHome (View v){
        final String name = rNameField.getText().toString().trim();
        String email = rEmailField.getText().toString().trim();
        String password = rPasswordField.getText().toString().trim();
        String grade = rGradeSpinner.getSelectedItem().toString().trim();
        String birthday = rBirthdayField.getText().toString().trim();
        String phone = rPhoneField.getText().toString().trim();
        String address = rAddressField.getText().toString().trim();
        String city = rCityField.getText().toString().trim();
        String state = rStateSpinner.getSelectedItem().toString().trim();
        String zip = rZipField.getText().toString().trim();
        if (regiTextTitle.getText().equals("STUDENT REGISTRATION")){
            Toast.makeText(getApplicationContext(), "Student Added.", Toast.LENGTH_SHORT).show();
        }
        else if (regiTextTitle.getText().equals("MENTOR REGISTRATION")){
            if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(grade) || !TextUtils.isEmpty(birthday) || !TextUtils.isEmpty(phone) ||
                    !TextUtils.isEmpty(address) || !TextUtils.isEmpty(city) || !TextUtils.isEmpty(state) || !TextUtils.isEmpty(zip)){
                final mentor newMentor = new mentor(name, email, grade, birthday, phone, address, city, state, zip);
                progressDialog.setMessage("Registering User");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    databaseMentors.child(name).setValue(newMentor);
                                    Toast.makeText(getApplicationContext(), "Mentor Added.", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                                else{
                                    try {
                                        throw task.getException();
                                    } catch(FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address looks a little wrong there", Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                    } catch(Exception e) {

                                    }
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Staff Added.", Toast.LENGTH_SHORT).show();
                }
    }

    @Override
    public void onBackPressed() {
        if (STUDENTREG.getVisibility() == View.INVISIBLE){
            STUDENTREG.setVisibility(View.VISIBLE);
            MENTORREG.setVisibility(View.VISIBLE);
            STAFFREG.setVisibility(View.VISIBLE);

            regiTextTitle.setVisibility(View.INVISIBLE);
            rName.setVisibility(View.INVISIBLE);
            rNameField.setVisibility(View.INVISIBLE);
            rEmail.setVisibility(View.INVISIBLE);
            rEmailField.setVisibility(View.INVISIBLE);
            rPassword.setVisibility(View.INVISIBLE);
            rPasswordField.setVisibility(View.INVISIBLE);
            rGrade.setVisibility(View.INVISIBLE);
            rGradeSpinner.setVisibility(View.INVISIBLE);
            rBirthday.setVisibility(View.INVISIBLE);
            rBirthdayField.setVisibility(View.INVISIBLE);
            rAddress.setVisibility(View.INVISIBLE);
            rAddressField.setVisibility(View.INVISIBLE);
            rPhone.setVisibility(View.INVISIBLE);
            rPhoneField.setVisibility(View.INVISIBLE);
            rCity.setVisibility(View.INVISIBLE);
            rCityField.setVisibility(View.INVISIBLE);
            rState.setVisibility(View.INVISIBLE);
            rStateSpinner.setVisibility(View.INVISIBLE);
            rZip.setVisibility(View.INVISIBLE);
            rZipField.setVisibility(View.INVISIBLE);
            create.setVisibility(View.INVISIBLE);
        }
        else{
            Intent backToHome = new Intent(this, MainActivity.class);
            startActivity(backToHome);
        }
    }
}
