package plug_off.androweather;

import android.os.AsyncTask;
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

    public void getWeather(View v) {

        // Get the text from the input field
        EditText location = (EditText) findViewById(R.id.input);
        new GetWeatherInBackground().execute(location.getText().toString());
    }

    private class GetWeatherInBackground extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... zip){
            Weather W = new Weather(zip[0]);
            W.fetch();
            return W;
        }

        @Override
        protected void onPostExecute(Weather weather){
            TextView temp = (TextView) findViewById(R.id.temperature);
            temp.setText("" + weather.getTemperatureF(weather.DynamicJson) + " / " + weather.getTemperatureC(weather.DynamicJson) );
        }
    }

}


