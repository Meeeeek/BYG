package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeBulletin extends Fragment {

    ListView pinnedPosts;
    ArrayList<post> databasePosts;

    bulletinAdapter bulletinAdapter;
    DatabaseReference staffPosts;

    FloatingActionButton addPost;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.under_construction, container, false);
/*
        databasePosts = new ArrayList<>();
        staffPosts = FirebaseDatabase.getInstance().getReference("Staff Posts");

        pinnedPosts = (ListView) view.findViewById(R.id.pinnedPosts);
        bulletinAdapter bulletinAdapter = new bulletinAdapter();
        pinnedPosts.setAdapter(bulletinAdapter);

        pinnedPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent details = new Intent(getContext(), postDetails.class);
                details.putExtra("postName", databasePosts.get(i).subject);
                details.putExtra("postText", databasePosts.get(i).content);
                details.putExtra("postTime", databasePosts.get(i).time);
                details.putExtra("postDate", databasePosts.get(i).date);
                details.putExtra("postAuthor", databasePosts.get(i).name);

                startActivity(details);
            }
        });

        addPost = (FloatingActionButton) view.findViewById(R.id.addPinned);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPost = new Intent(getActivity(), addPost.class);
                startActivity(addPost);
            }
        });
*/
        return view;

    }
/*
    @Override
    public void onStart() {
        super.onStart();

        staffPosts.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                databasePosts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    post post = postSnapshot.getValue(post.class);
                    databasePosts.add(post);
                }

                bulletinAdapter = new bulletinAdapter();
                pinnedPosts.setAdapter(bulletinAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
    private class bulletinAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return databasePosts.size();
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
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_bulletin, null);

            TextView name = (TextView) view.findViewById(R.id.bulletinName);
            TextView postAuthor = (TextView) view.findViewById(R.id.bulletinAuthor);
            TextView postTime = (TextView) view.findViewById(R.id.publishedTime);
            TextView postDate = (TextView) view.findViewById(R.id.publishedDate);

            post post = databasePosts.get(i);

            name.setText(post.getSubject());
            postAuthor.setText(post.getName());
            postTime.setText(post.getTime().substring(0,5).concat(post.getTime().substring(8)));
            postDate.setText(post.getDate());


            return view;
        }
    }

}