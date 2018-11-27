package n.gbds2.scheduler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.Add);
        name = (TextView) findViewById(R.id.textView8);
        name.setText("fuc2k");
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, 3000);

            }
        });

    }



    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        name.setText("no");
        if (requestCode == 3000) {
            if(resultCode == Activity.RESULT_OK){
                String name=data.getStringExtra("name");
                String time=data.getStringExtra("time");
                String start=data.getStringExtra("start");
                String end=data.getStringExtra("end");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                name.setText("no");
            }
        }
    }
}
