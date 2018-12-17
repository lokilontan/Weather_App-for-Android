package plug_off.androweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    Weather W;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeather(View v) {

        // Get the text from the input field
        EditText location = (EditText) findViewById(R.id.input);
        new GetWeatherInBackground().execute(location.getText().toString());
    }

    public void changeUnit(View v) {
        Switch s = (Switch) findViewById(R.id.unit);
        flag = !flag;
        s.setChecked(flag);


        if(s.isChecked()) {
            TextView tempField = (TextView) findViewById(R.id.temperature);
            tempField.setText("" + W.getTemperatureC(W.DynamicJson));
        }
        else {
            TextView tempField = (TextView) findViewById(R.id.temperature);
            tempField.setText("" + W.getTemperatureF(W.DynamicJson));
        }
    }



   private class GetWeatherInBackground extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... zip){
            W = new Weather(zip[0]);
            W.fetch();
            return W;
        }

        @Override
        protected void onPostExecute(Weather weather){
            TextView temp = (TextView) findViewById(R.id.temperature);
            temp.setText("" + weather.getTemperatureF(weather.DynamicJson) );
        }
    }

}


