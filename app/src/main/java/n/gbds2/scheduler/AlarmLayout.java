package n.gbds2.scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

public class AlarmLayout extends LinearLayout{
    TextView name;
    TextView time;
    TextView start;
    TextView end;

    public String Name;
    public String Time;
    public String Start;
    public String End;

    Button fix;
    Button del;

    int Index;
    public boolean show;

    public AlarmManager am;

    public AlarmLayout(Context context,AttributeSet attrs){
        super(context,attrs);

        init(context);
    }

    public AlarmLayout(Context context,String name,String time,String start,String end,int index){
        super(context);
        Name = name;
        Time = time;
        Start = start;
        End = end;
        show = true;
        Index = index;
        init(context);
    }



    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.alarmlayout,this,true);
        name = (TextView)findViewById(R.id.nameT);
        name.setText(Name);
        time = (TextView)findViewById(R.id.timeT);
        time.setText(Time);
        start = (TextView)findViewById(R.id.startT);
        start.setText(Start);
        end = (TextView)findViewById(R.id.endT);
        end.setText(End);

        try {
            int a = Integer.parseInt(Time);

            Calendar c = Calendar.getInstance();

            c.set(Calendar.HOUR_OF_DAY, a/100);
            c.set(Calendar.MINUTE, a%100);
            am = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getContext(), MainActivity.class);
            PendingIntent operation = PendingIntent.getActivity(getContext(), 0, intent, 0);
            am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), operation);

            end.setText(""+a/100+" "+a%100);
        }
        catch(Exception e){

        }


        fix = (Button)findViewById(R.id.Fix);
        del = (Button)findViewById(R.id.Del);

        fix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });

        del.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ViewGroup parent = (ViewGroup)view.getParent();
                ViewGroup parent2 = (ViewGroup)parent.getParent();
                parent2.removeView(parent);
                show = false;

            }
        });

    }


}
