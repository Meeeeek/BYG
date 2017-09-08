package michaelkim.byg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addPost extends AppCompatActivity {

    EditText postSubject;
    EditText postText;
    Button post, addPicture;

    DatabaseReference staffPosts;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bulletin_post);

        postSubject = (EditText) findViewById(R.id.subjectText);
        postText = (EditText) findViewById(R.id.postText);
        post = (Button) findViewById(R.id.post);
        addPicture = (Button) findViewById(R.id.addPicture);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Text into the Post.", Toast.LENGTH_SHORT).show();
                }
                else{
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
                    Date date = new Date();
                    String todaysDate = dateFormat.format(date);
                    String name = firebaseAuth.getCurrentUser().getEmail();
                    String justTheName = name.substring(0, name.indexOf("@"));
                    post post = new post(postSubject.getText().toString().trim(), todaysDate.substring(0, 10),
                            todaysDate.substring(10), postText.getText().toString().trim(), name);
                    staffPosts.child(todaysDate).setValue(post);
                    Toast.makeText(getApplicationContext(), "Post added.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        staffPosts = FirebaseDatabase.getInstance().getReference("Staff Posts");
    }

}
