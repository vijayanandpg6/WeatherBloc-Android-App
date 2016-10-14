package vijayanand.weatherbloc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id==R.id.action_map){
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void openPreferredLocationInMap(){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String location=sharedPreferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        Uri geoLocation=Uri.parse("geo:0,0?)").buildUpon()
                .appendQueryParameter("q", location)
                .build();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            Log.d("LOG_TAG", "Couldn't call"+location+", no recieving apps involved!");
        }

    }
}
