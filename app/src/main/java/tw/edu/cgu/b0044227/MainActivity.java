package tw.edu.cgu.b0044227;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static tw.edu.cgu.b0044227.Utils.readFile;


public class MainActivity extends ActionBarActivity {

    private Button btn1;
    private EditText edt1;
    private CheckBox checkBox;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 =(Button) findViewById(R.id.btn1);
        edt1 = (EditText)findViewById(R.id.edt1);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        listView = (ListView) findViewById(R.id.listView);

        sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sp.edit();

        btn1.setText("submit");
        edt1.setText(sp.getString("text",""));
        boolean b = sp.getBoolean("checkbox",false);
        checkBox.setChecked(b);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        edt1.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            String text = edt1.getText().toString();
            editor.putString("text",text);
            editor.commit();

            Log.d("debug", "keycode = " + keyCode);
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                send();
                return true;
            }
            return false;
        }
    });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("checkbox",isChecked);
                editor.commit();
            }
        });
        updateHistory();
    }

    public void goToOrderActivity(View view){
        Intent intent = new Intent();
        intent.setClass(this, OrderActivity.class);
        startActivity(intent);
    }

    private void updateHistory() {
        String[] data = Utils.readFile(this,"history").split("\n");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }


    private void send() {
        String text = edt1.getText().toString();
        if (checkBox.isChecked()){
            text = "*****";
        }

        Utils.writeFile(this,"history",text + "\n");

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        edt1.setText("");

        updateHistory();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
