package tw.edu.cgu.b0044227;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OrderActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    public void cnt(View view){
        Button button = (Button) view;
        int i = Integer.parseInt(button.getText().toString());
        button.setText(String.valueOf(i+1));
        getData();
    }

    public void getData(){
        String blackTeaL =((Button)findViewById(R.id.black_tea_l)).getText().toString();
        String blackTeaM =((Button)findViewById(R.id.black_tea_m)).getText().toString();
        String milkTeaL =((Button)findViewById(R.id.milk_tea_l)).getText().toString();
        String milkTeaM =((Button)findViewById(R.id.milk_tea_l)).getText().toString();




        try {
            JSONObject blackTea = new JSONObject();
            blackTea.put("name", "black-tea");
            blackTea.put("L", Integer.parseInt(blackTeaL));
            blackTea.put("M", Integer.parseInt(blackTeaM));
            JSONObject milkTea = new JSONObject();
            milkTea.put("name", "milk-tea");
            milkTea.put("L", Integer.parseInt(milkTeaL));
            milkTea.put("M", Integer.parseInt(milkTeaM));
            JSONArray order = new JSONArray();
            order.put(blackTea);
            order.put(milkTea);
            Log.d("debug", order.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
