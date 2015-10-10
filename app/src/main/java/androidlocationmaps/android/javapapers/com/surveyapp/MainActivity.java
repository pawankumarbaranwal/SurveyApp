package androidlocationmaps.android.javapapers.com.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView input;
    private Button submit;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=(TextView)findViewById(R.id.tvInputText);
        next=(Button)findViewById(R.id.btnNext);
        submit=(Button)findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {

        if (view==submit) {
            String[] params = (input.getText() + "").split(",");


            Bundle bundel = new Bundle();
            bundel.putStringArray("paramsarray", params);

            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtras(bundel);
            Log.i("pawanwa", params.length + "");
            SurveyActivity.surveyList.clear();
            SurveyActivity.outputSurveyList.clear();
            startActivity(intent);
        }
    else if (view==next){

            String[] params = (input.getText() + "").split(",");


            Bundle bundel = new Bundle();
            bundel.putStringArray("paramsarray", params);

            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtras(bundel);
            Log.i("pawanwa", params.length + "");
            startActivity(intent);
        }
    }
}
