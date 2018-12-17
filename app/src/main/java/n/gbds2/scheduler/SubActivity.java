package n.gbds2.scheduler;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button finishButton = (Button)findViewById(R.id.Finish);
        final TextView name = (TextView) findViewById(R.id.Name);
        final TextView time = (TextView) findViewById(R.id.Time);
        final TextView start = (TextView) findViewById(R.id.DueStart);
        final TextView end = (TextView) findViewById(R.id.DueEnd);

        finishButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent(SubActivity.this,MainActivity.class);
                //startActivityForResult(intent,3000);
                Intent intent = new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("time",time.getText().toString());
                intent.putExtra("start",start.getText().toString());
                intent.putExtra("end",end.getText().toString());

                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }


    public void add_button(){

    }
}