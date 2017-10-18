package michaelkim.byg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registrationSelector extends AppCompatActivity {

    TextView mentorSelect, staffSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_selector);

        mentorSelect = (TextView) findViewById(R.id.rMentorSelect);
        staffSelect = (TextView) findViewById(R.id.rStaffSelect);

        mentorSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mentorReg = new Intent(getBaseContext(), registrationScreen.class);
                mentorReg.putExtra("name", "MENTOR");
                startActivity(mentorReg);
            }
        });

        mentorSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    mentorSelect.setTextColor(Color.parseColor("#FFFFFF"));
                    mentorSelect.setBackgroundColor(Color.parseColor("#000000"));
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    mentorSelect.setTextColor(Color.parseColor("#000000"));
                    mentorSelect.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    return false;
                }
                return true;
            }
        });

        staffSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent staffReg = new Intent(getBaseContext(), registrationScreen.class);
                staffReg.putExtra("name", "STAFF");
                startActivity(staffReg);
            }
        });

        staffSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    staffSelect.setTextColor(Color.parseColor("#000000"));
                    staffSelect.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    staffSelect.setTextColor(Color.parseColor("#FFFFFF"));
                    staffSelect.setBackgroundColor(Color.parseColor("#000000"));
                    return false;
                }
                return true;
            }
        });
    }

}
