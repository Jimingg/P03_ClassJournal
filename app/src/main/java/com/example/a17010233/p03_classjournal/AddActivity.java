package com.example.a17010233.p03_classjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    TextView tvAdd;
    private RadioGroup rgGrade;
    private RadioButton radioButton;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvAdd = findViewById(R.id.tvAdd);
        rgGrade = findViewById(R.id.RadioGroupG);
        btnSubmit = findViewById(R.id.buttonSubmit);

        Intent i = getIntent();
        int theNum = i.getIntExtra("num", 0);
        final String num = String.valueOf(theNum + 1);

        tvAdd.setText("Week " + num);

        // When button Like is clicked, set the results
        //  accordingly and finish() to close this act.
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // Create intent & pass in String data
                Intent i = new Intent();

                int selected = rgGrade.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selected);

                i.putExtra("grade", radioButton.getText());
                i.putExtra("week", num);

                // Set result to RESULT_OK to indicate normal
                // response and pass in the intent containing the
                // grade
                setResult(RESULT_OK, i);
                finish();
            }});

    }
}
