package androidlocationmaps.android.javapapers.com.surveyapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity implements  View.OnClickListener {

    private TextView input;
    private Button nextPerson;
    private Button submitSurvey;
    private SurveyPojo surveyPojo;
    private ListView listView;
    private List<SurveyPojo> list=new ArrayList<SurveyPojo>();
    static List<SurveyPojo> surveyList=new ArrayList<SurveyPojo>();
    static List<SurveyPojo> outputSurveyList=new ArrayList<SurveyPojo>();
    AdapterClass adapter;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_listview);
        submitSurvey=(Button)findViewById(R.id.btn_submit_survey);
        nextPerson=(Button)findViewById(R.id.btn_next_person);




        String array[] = getIntent().getExtras().getStringArray("paramsarray");
        for (int i=0;i<array.length;i++)
        {
            surveyPojo=new SurveyPojo();
            surveyPojo.setId(array[i]);
            surveyPojo.setCount(0);
            list.add(surveyPojo);
        }




        listView = (ListView) findViewById(R.id.listview);
        //View footer = getLayoutInflater().inflate(R.layout.footer, null);

        adapter=new AdapterClass();
       // listView.addFooterView(footer);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
        submitSurvey.setOnClickListener(this);
        nextPerson.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        if (view==nextPerson){
            if(outputSurveyList.size()==0){
                outputSurveyList=list;
            }
                for (int i=0;i<outputSurveyList.size();i++){
                    for (int j=0;j<surveyList.size();j++){
                        if (outputSurveyList.get(i).getId().equals(surveyList.get(j).getId())){
                            outputSurveyList.get(i).setCount(outputSurveyList.get(i).getCount()+surveyList.get(j).getCount());
                        }
                    }
                }

            adapter.notifyDataSetChanged();
            surveyList.clear();
        }else if(view==submitSurvey){
            if(outputSurveyList.size()==0){
                outputSurveyList=list;
            }
            for (int i=0;i<outputSurveyList.size();i++){
                for (int j=0;j<surveyList.size();j++){
                    if (outputSurveyList.get(i).getId().equals(surveyList.get(j).getId())){
                        outputSurveyList.get(i).setCount(outputSurveyList.get(i).getCount()+surveyList.get(j).getCount());
                    }
                }
            }

            adapter.notifyDataSetChanged();
            surveyList.clear();
            Intent i=new Intent(this,OutputActivity.class);
            startActivity(i);
        }
    }

    public class AdapterClass extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder {
            TextView id;
            TextView description;
            public View btnInc;
            public View btnDec;

            ViewHolder(View view) {
                id = (TextView) view.findViewById(R.id.tvId);
                description = (EditText) view.findViewById(R.id.etDescription);
                btnInc= (Button) view.findViewById(R.id.increment);
                btnDec= (Button) view.findViewById(R.id.decrement);
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder viewHolder;
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row=inflater.inflate(R.layout.row_survey, parent, false);
                viewHolder=new ViewHolder(row);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)row.getTag();
            }
            final SurveyPojo surveyPojo=list.get(position);
            viewHolder.id.setText(surveyPojo.getId());
            viewHolder.description.setText("0");

            //Log.i("ppppppppppp", surveyPojo.getId());
/*
            viewHolder.description.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    if (hasFocus) {
                        final EditText etxt = (EditText) v;
                        final TextView tt = (TextView) v;
                        //viewHolder.mEditText.setText(etxt.getText().toString());

                        Log.i("ppppppppppp", etxt.getText().toString() + "                                     " + surveyPojo.getId());

                    }
                }
            });
*/
















            viewHolder.btnInc.setOnClickListener(new View.OnClickListener() {


                //Boolean temp=false;
                @Override
                public void onClick(final View view) {
                    SurveyPojo survey = new SurveyPojo();
                    TextView id = (TextView) (((ViewGroup) view.getParent()).getChildAt(0));
                    EditText description = (EditText) (((ViewGroup) view.getParent()).getChildAt(2));
                    int count = Integer.parseInt(description.getText().toString()) + 1;
                    Boolean temp = false;
                    description.setText("" + count);
                    survey = new SurveyPojo();
                    survey.setId(id.getText() + "");
                    survey.setCount(count);

                     for(int i=0;i<surveyList.size();i++){

                       if((surveyList.get(i).getId().equals(id.getText()+""))){

                            surveyList.remove(i);
                            if(count==0){
                                temp=true;
                            }
                        }
                    }if (temp==false) {
                        surveyList.add(survey);
                    }

                    for (int x=0;x<surveyList.size();x++) {
                        Log.i("CustomAdapter", "Size :" + surveyList.get(x).getId() + "                        "+surveyList.get(x).getCount());
                    }
                }
            });




            viewHolder.btnDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    int i=0;
                    SurveyPojo survey = new SurveyPojo();
                    TextView id = (TextView) (((ViewGroup) view.getParent()).getChildAt(0));
                    EditText description = (EditText) (((ViewGroup) view.getParent()).getChildAt(2));
                    int count = Integer.parseInt(description.getText().toString()) - 1;
                    Boolean temp=false;
                    if(count>=0){
                        description.setText("" + count);
                    }else{
                        count++;
                        description.setText("" + count);
                    }
                    survey = new SurveyPojo();
                    survey.setId(id.getText() + "");
                    survey.setCount(count);
                    for(int j=0;j<surveyList.size();j++){

                        if((surveyList.get(j).getId().equals(id.getText()+""))){

                            surveyList.remove(j);
                            if(count==0){
                                temp=true;
                            }
                        }
                    }if (temp==false) {
                        surveyList.add(survey);
                    }
                    for (int y=0;y<surveyList.size();y++) {
                        Log.i("CustomAdapter", "Size :" + surveyList.get(y).getId() + "                        "+surveyList.get(y).getCount());
                    }
                }
            });










            return row;
        }
    }


    void surveyCountCalculation(){
        if(outputSurveyList.size()==0){
            outputSurveyList=list;
        }
        for (int i=0;i<outputSurveyList.size();i++){
            for (int j=0;j<surveyList.size();j++){
                if (outputSurveyList.get(i).getId().equals(surveyList.get(j).getId())){
                    outputSurveyList.get(i).setCount(outputSurveyList.get(i).getCount()+surveyList.get(j).getCount());
                }
            }
        }

        adapter.notifyDataSetChanged();
        surveyList.clear();
    }
}
