package tw.chikuo.lockersmap;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  Input data "stops"
 */

public class StopActivity extends AppCompatActivity {

    private Stops stops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        // Get extra data
        if (getIntent() != null && getIntent().hasExtra("stops")){
            stops = (Stops) getIntent().getSerializableExtra("stops");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            if (stops != null && stops.getName() != null){
                actionBar.setTitle(stops.getName());
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (stops != null) {

            TextView timeTextView = (TextView) findViewById(R.id.time_textView);
            TextView sizeTextView = (TextView) findViewById(R.id.size_textView);
            TextView notesTextView = (TextView) findViewById(R.id.notes_textView);
            TextView descriptionTextView = (TextView) findViewById(R.id.description_textView);
            LinearLayout tagLayout = (LinearLayout) findViewById(R.id.tag_layout);

            // Tag
            if (stops.getTagList() != null) {
                TagManagement tagManagement = new TagManagement(StopActivity.this,tagLayout,stops.getTagList());
            }
            // Time
            if (stops.getTime() != null) {
                timeTextView.setText(stops.getTime());
            }
            // Note
            if (stops.getNote() != null) {
                notesTextView.setText(stops.getNote());
            }
            // Description
            if (stops.getDescription() != null) {
                descriptionTextView.setText(stops.getDescription());
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
