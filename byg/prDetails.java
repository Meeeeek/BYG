package michaelkim.byg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class prDetails extends AppCompatActivity {

    TextView postName, postGrade, postText, postTimeAndDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_layout_details);

        postName = (TextView) findViewById(R.id.prName);
        postGrade = (TextView) findViewById(R.id.prGrade);
        postText = (TextView) findViewById(R.id.prDetails);
        postTimeAndDate = (TextView) findViewById(R.id.prTimeDate);

        Bundle strings = getIntent().getExtras();

        String grade = strings.getString("postGrade");
        if (grade.equals("A")){
            grade = "";
        }
        else if (grade.charAt(grade.length()-1) == 'P'){
            grade = grade.substring(0, grade.length()-1);
            Toast.makeText(getBaseContext(), grade, Toast.LENGTH_SHORT).show();
        }

        postName.setText(strings.getString("postName"));
        postGrade.setText(grade);
        postText.setText(strings.getString("postDetails"));
        postTimeAndDate.setText(strings.getString("postDate") + " " + strings.getString("postTime"));

    }

}