package michaelkim.byg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class homeLogs extends Fragment {

    String grade;

    // Widget declaration
    Button submitLog;
    TextView missingStudentsText, highlightsText, smallGroupGoalText, solutionsText, meetupText, prayerRequestsText;
    EditText missingStudents, highlights, smallGroupGoal, solutions, meetup, prayerRequests;
    ImageView construction;

    // Database declarations
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logs, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Widget Instantiation.
        submitLog = (Button) view.findViewById(R.id.submitLog);

        missingStudentsText = (TextView) view.findViewById(R.id.textView6);
        missingStudents = (EditText) view.findViewById(R.id.missingStudents);

        highlightsText = (TextView) view.findViewById(R.id.textView7);
        highlights = (EditText) view.findViewById(R.id.highlights);

        smallGroupGoalText = (TextView) view.findViewById(R.id.textView8);
        smallGroupGoal = (EditText) view.findViewById(R.id.smallGroupGoal);

        solutionsText = (TextView) view.findViewById(R.id.textView9);
        solutions = (EditText) view.findViewById(R.id.improveSGTime);

        meetupText = (TextView) view.findViewById(R.id.textView10);
        meetup = (EditText) view.findViewById(R.id.meetup);

        prayerRequestsText = (TextView) view.findViewById(R.id.textView11);
        prayerRequests = (EditText) view.findViewById(R.id.prayerRequests);

        construction = (ImageView) view.findViewById(R.id.construction);

        // Submit Button Usage.
        submitLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] emailAddress = {"michaelkim6@yahoo.com"};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "LOG for " + grade + " (" + getDate() + ")");
                emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Missing Students : " + missingStudents.getText().toString().trim() + "\n\n" +
                        "Highlights : " + highlights.getText().toString().trim() + "\n\n" +
                        "Did you meet the small group goal of the day? Why or why not? : " + smallGroupGoal.getText().toString().trim() + "\n\n" +
                        "Solutions on how to improve small group time : " + solutions.getText().toString().trim() + "\n\n" +
                        "Who did you meet up with this week? : " +  meetup.getText().toString().trim() + "\n\n" +
                        "Prayer Requests : " + prayerRequests.getText().toString().trim()
                );
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Toast.makeText(getActivity(), "Preparing Email...", Toast.LENGTH_SHORT).show();
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String name = currentUser.getUid();

        DatabaseReference reference = firebaseDatabase.getReference("Mentor/" + name + "/grade");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                grade = dataSnapshot.getValue(String.class);
                if (grade == null) {
                    hideAllViews();
                    highlightsText.setText("May change this format to show who has completed the logs at a later date.");
                    construction.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(), "ALL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), grade, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private String getDate(){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        String todaysDate = df.format(c.getTime());

        return todaysDate;
    }

    private void hideAllViews () {
        missingStudents.setVisibility(View.INVISIBLE);
        missingStudentsText.setVisibility(View.INVISIBLE);
        highlights.setVisibility(View.INVISIBLE);
        smallGroupGoal.setVisibility(View.INVISIBLE);
        smallGroupGoalText.setVisibility(View.INVISIBLE);
        solutions.setVisibility(View.INVISIBLE);
        solutionsText.setVisibility(View.INVISIBLE);
        meetup.setVisibility(View.INVISIBLE);
        meetupText.setVisibility(View.INVISIBLE);
        prayerRequests.setVisibility(View.INVISIBLE);
        prayerRequestsText.setVisibility(View.INVISIBLE);
        submitLog.setVisibility(View.INVISIBLE);
    }

}