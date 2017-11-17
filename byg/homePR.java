package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class homePR extends Fragment {

    ListView prList;
    ArrayList<prayerRequest> requestList = new ArrayList<>();

    prAdapter prAdapter;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pr, container, false);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Prayer Requests");

        prList = (ListView) view.findViewById(R.id.prList);
        prAdapter prAdapter = new prAdapter();
        prList.setAdapter(prAdapter);

        prList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder prDialog = new AlertDialog.Builder(getContext());
                View prView = getLayoutInflater(savedInstanceState).inflate(R.layout.pr_layout_details, null);
                prDialog.setView(prView);

                AlertDialog dialog = prDialog.create();
                dialog.show();

                TextView postName, postGrade, postText, postTimeAndDate;

                postName = (TextView) prView.findViewById(R.id.prName);
                postGrade = (TextView) prView.findViewById(R.id.prGrade);
                postText = (TextView) prView.findViewById(R.id.prDetails);
                postTimeAndDate = (TextView) prView.findViewById(R.id.prTimeDate);

                // Set dialog's textviews to the selected prayer request.
                postName.setText(requestList.get(i).name);
                postText.setText(requestList.get(i).prayerRequest);
                postTimeAndDate.setText(requestList.get(i).date);

                // Change the grade correspondingly so it only shows up as a grade, rather than including 'P'
                String grade = requestList.get(i).grade;
                if (grade.equals("A")){
                    grade = "";
                }
                else if (grade.charAt(grade.length()-1) == 'P'){
                    grade = grade.substring(0, grade.length()-1);
                }

                postGrade.setText(grade);
            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        // Get the user's ID so we can look at the currently logged in user's grade.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String name = currentUser.getUid();

        // Get the database reference for the currently logged in user's grade.
        DatabaseReference reference = firebaseDatabase.getReference("Mentor/" + name + "/grade");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Check if the user is currently a pastor or a teacher.
                final String grade = dataSnapshot.getValue(String.class);
                if (grade == null){
                    Toast.makeText(getContext(), "PASTOR", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), grade, Toast.LENGTH_SHORT).show();
                }
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        requestList.clear();
                        // Look through the list of prayer requests.
                        for (DataSnapshot prSnapshot : dataSnapshot.getChildren()){
                            prayerRequest prayerRequest = prSnapshot.getValue(prayerRequest.class);
                            String prGrade = prayerRequest.grade;

                            // Get the prayer request's grade.
                            boolean forPastors = false;
                            if (prGrade.length() > 1){
                                if (prGrade.charAt(prGrade.length()-1) == 'P'){
                                    forPastors = true;
                                    prGrade = prGrade.substring(0, prGrade.length()-1);
                                }
                                Log.e("PR ITEM", forPastors + " " + prGrade);
                            }
                            else if (prGrade.equals("P")){
                                forPastors = true;
                            }

                            if (grade == null){
                                if (forPastors){
                                    requestList.add(prayerRequest);
                                }
                                else{
                                    if (prGrade.equals("A")){
                                        requestList.add(prayerRequest);
                                    }
                                }
                            }
                            else {
                                if (prGrade.equals("A")) {
                                    requestList.add(prayerRequest);
                                }
                                else{
                                    if (prGrade.equals(grade)){
                                        requestList.add(prayerRequest);
                                    }
                                }
                            }
                        }

                        prAdapter = new prAdapter();
                        prList.setAdapter(prAdapter);
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
    }

    private class prAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return requestList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_pr, null);

            TextView studentName = (TextView) view.findViewById(R.id.stuName);
            studentName.setText(requestList.get(i).name);
            TextView prayerRequestItem = (TextView) view.findViewById(R.id.prayerRequestItem);
            prayerRequestItem.setText(requestList.get(i).prayerRequest);
            TextView prDate = (TextView) view.findViewById(R.id.prDate);
            prDate.setText(requestList.get(i).date);

            return view;
        }
    }

}