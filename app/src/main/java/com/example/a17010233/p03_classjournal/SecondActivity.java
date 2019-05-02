package com.example.a17010233.p03_classjournal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnInfo;
    Button btnAdd;
    Button btnEmail;
    ArrayAdapter aa;
    ArrayList<Details> details;

    // These request identify who started the second activity
    int requestCodeForC347Stats = 1;
    int requestCodeForC302Stats = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.lvWeek);
        btnInfo = (Button) findViewById(R.id.buttonInfo);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnEmail = (Button) findViewById(R.id.buttonEmail);

        details = new ArrayList<Details>();

        Intent i = getIntent();
        final String name = i.getStringExtra("name");
        this.setTitle("Info for " + name);

        if (name.equals("C347")) {
            details.add(new Details(1, "A", "C347"));
            details.add(new Details(2, "B", "C347"));
            details.add(new Details(3, "A", "C347"));

        } else {
            details.add(new Details(1, "A", "C302"));
            details.add(new Details(2, "A", "C302"));
            details.add(new Details(3, "B", "C302"));
        }

        aa = new DetailsAdapter(this, R.layout.row, details);
        lv.setAdapter(aa);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to display data
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                // Set the URL to be used.
                if (name.equals("C347")) {
                    rpIntent.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C347"));
                } else {
                    rpIntent.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C302"));
                }

                startActivity(rpIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), AddActivity.class);

                if (name.equals("C347")){
                    int num = details.size();
                    i.putExtra("num", num);
                    startActivityForResult(i,requestCodeForC347Stats);
                } else {
                    int num = details.size();
                    i.putExtra("num", num);
                    startActivityForResult(i,requestCodeForC302Stats);
                }
            }
        });


        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder sb = new StringBuilder();
                for (Details s : details) {
                    sb.append("Week " + s.getWeek() + ": DG: " + s.getGrade());
                    sb.append("\n");
                }

                    // The action you want this intent to do;
                    // ACTION_SEND is used to indicate sending text
                    Intent email = new Intent(Intent.ACTION_SEND);
                    // Put essentials like email address, subject & body text
                    email.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{"jason_lim@rp.edu.sg"});
                    email.putExtra(Intent.EXTRA_SUBJECT,
                            "Test Email from C347");
                    email.putExtra(Intent.EXTRA_TEXT,
                            "Hello faci, \nI am Shumei\nPlease see my remarks so far, thankyou!\n\n" + sb.toString());

                    // This MIME type indicates email
                    email.setType("message/rfc822");
                    // createChooser shows user a list of app that can handle
                    // this MIME type, which is, email
                    startActivity(Intent.createChooser(email,
                            "Choose an Email client :"));


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                String grade = data.getStringExtra("grade");
                String myWeek = data.getStringExtra("week");
                int week = Integer.parseInt(myWeek);

                // If it is activity started by clicking
                //  Superman, create corresponding String
                if(requestCode == requestCodeForC347Stats){
                    details.add(new Details(week, grade, "C347"));
                    aa.notifyDataSetChanged();

                }
                // If 2nd activity started by clicking
                //  Batman, create a corresponding String
                if(requestCode == requestCodeForC302Stats){
                    details.add(new Details(week, grade, "C302"));
                    aa.notifyDataSetChanged();
                }

//                Toast.makeText(SecondActivity.this, statement,
//                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
