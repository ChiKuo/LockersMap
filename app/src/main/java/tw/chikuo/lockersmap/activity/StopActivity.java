package tw.chikuo.lockersmap.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import tw.chikuo.lockersmap.R;
import tw.chikuo.lockersmap.object.Locker;
import tw.chikuo.lockersmap.object.Stop;
import tw.chikuo.lockersmap.TagManagement;

/**
 *  Input data "stop"
 */

public class StopActivity extends AppCompatActivity {

    private Stop stop;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference lockerRef = database.getReference("Locker");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        // Get extra data
        if (getIntent() != null && getIntent().hasExtra("stop")){
            stop = (Stop) getIntent().getSerializableExtra("stop");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            if (stop != null && stop.getName() != null){
                actionBar.setTitle(stop.getName());
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (stop != null) {

            TextView timeTextView = (TextView) findViewById(R.id.time_textView);
            final TextView sizeTextView = (TextView) findViewById(R.id.size_textView);
            TextView notesTextView = (TextView) findViewById(R.id.notes_textView);
            TextView descriptionTextView = (TextView) findViewById(R.id.description_textView);
            LinearLayout tagLayout = (LinearLayout) findViewById(R.id.tag_layout);

            // Tag
            if (stop.getTagList() != null) {
                TagManagement tagManagement = new TagManagement(StopActivity.this,tagLayout, stop.getTagList());
            }
            // Time
            if (stop.getTime() != null) {
                timeTextView.setText(stop.getTime());
            }
            // Size
            if (stop.getId() != null) {
                Query lockerQuery = lockerRef.orderByChild("locationId").equalTo(stop.getId());
                lockerQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            // Each locker
                            Locker locker = postSnapshot.getValue(Locker.class);
                            if (locker != null && locker.getSize() != null && locker.getPrices() != null && sizeTextView != null) {
                                // Setup text
                                if (sizeTextView.getText().toString().equals("")){
                                    sizeTextView.setText(locker.getSize() + "  " + locker.getPrices());
                                } else {
                                    sizeTextView.setText(sizeTextView.getText().toString() + "\n" +
                                            locker.getSize() + "  " + locker.getPrices());
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            // Note
            if (stop.getNote() != null) {
                notesTextView.setText(stop.getNote());
            }
            // Description
            if (stop.getDescription() != null) {
                descriptionTextView.setText(stop.getDescription());
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
