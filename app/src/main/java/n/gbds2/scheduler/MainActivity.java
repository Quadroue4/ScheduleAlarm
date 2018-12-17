package n.gbds2.scheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView name;
    int count;
    LinearLayout hi;
    AlarmLayout[] alarmList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.Add);
        alarmList = new AlarmLayout[100];
        count = 0;
        hi = (LinearLayout) findViewById(R.id.yo);

        StringBuffer buffer = new StringBuffer();
        String data = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput("internal.txt");
            BufferedReader iReader = new BufferedReader(new InputStreamReader((fis)));

            while(iReader.readLine() != null){
                String name = iReader.readLine();
                String time = iReader.readLine();
                String start = iReader.readLine();
                String end = iReader.readLine();
                alarmList[count] = new AlarmLayout(getApplicationContext(),name,time,start,end,count);
                hi.addView(alarmList[count],count++);
            }

            buffer.append("\n");
            iReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, 3000);

            }
        });

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == 3000) { // 호출결과가 add 버튼의 결과라면 여기서 해결
            if(resultCode == Activity.RESULT_OK){
                String name=data.getStringExtra("name");
                String time=data.getStringExtra("time");
                String start=data.getStringExtra("start");
                String end=data.getStringExtra("end");

                alarmList[count] = new AlarmLayout(getApplicationContext(),name,time,start,end,count);
                hi.addView(alarmList[count],count);

                try{
                    FileOutputStream fos = openFileOutput("internal.txt",Context.MODE_APPEND);
                    fos.write(("NotNull"+"\n").getBytes());
                    fos.write((alarmList[count].Name+"\n").getBytes());
                    fos.write((alarmList[count].Time+"\n").getBytes());
                    fos.write((alarmList[count].Start+"\n").getBytes());
                    fos.write((alarmList[count].End+"\n").getBytes());
                    count ++;
                }
                catch(FileNotFoundException e){

                }
                catch(IOException e){

                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    public void onStop(){
        try{
            FileOutputStream fos = openFileOutput("internal.txt",Context.MODE_PRIVATE);
            int count2 = 0;
            for(int i =0;i<count && alarmList[i].show;i++) {
                fos.write(("NotNull"+"\n").getBytes());
                fos.write((alarmList[i].Name+"\n").getBytes());
                fos.write((alarmList[i].Time+"\n").getBytes());
                fos.write((alarmList[i].Start+"\n").getBytes());
                fos.write((alarmList[i].End+"\n").getBytes());

                count2++;
            }
            count = count2;

        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){

        }

        super.onStop();
    }
}
