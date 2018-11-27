package n.gbds2.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button finishButton = (Button)findViewById(R.id.Finish);
        finishButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SubActivity.this,MainActivity.class);
                startActivityForResult(intent,3000);

            }
        });
    }


    public void add_button(){

    }
}