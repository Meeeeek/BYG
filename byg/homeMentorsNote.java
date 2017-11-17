package michaelkim.byg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Michael Kim on 11/16/2017.
 */

public class homeMentorsNote extends Fragment {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    TextView mentorsNote;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mentors_note, container, false);

        mentorsNote = (TextView) view.findViewById(R.id.note);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        // Get the user's ID so we can look at the currently logged in user's grade.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String name = currentUser.getUid();

        // Get the database reference for the currently logged in user's grade.
        DatabaseReference mentorsGrade = firebaseDatabase.getReference("Mentor/" + name + "/grade");

        // Get the mentor's grade.
        mentorsGrade.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String grade = dataSnapshot.getValue(String.class);
                Toast.makeText(getContext(), grade, Toast.LENGTH_SHORT).show();
                // Find the corresponding note.
                // Get the database reference for all the mentor's notes.
                DatabaseReference noteReference = firebaseDatabase.getReference("Mentors Notes/" + grade);
                noteReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String foundNote = dataSnapshot.getValue(String.class);
                        mentorsNote.setText(foundNote);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;

    }

}