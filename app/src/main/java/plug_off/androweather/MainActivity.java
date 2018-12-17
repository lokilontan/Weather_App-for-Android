package plug_off.androweather;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeather(View v)
    {
        // Disable threading. We'll fix this later.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Get the text from the input field
        EditText location = (EditText) findViewById(R.id.input);
        Weather w = new Weather(location.getText().toString());

        w.fetch();

        // Set the text of the temperature field
        TextView temp = (TextView) findViewById(R.id.temperature);
        temp.setText("" + w.getTemperatureF(w.DynamicJson) + " / " + w.getTemperatureC(w.DynamicJson) );
    }
}
