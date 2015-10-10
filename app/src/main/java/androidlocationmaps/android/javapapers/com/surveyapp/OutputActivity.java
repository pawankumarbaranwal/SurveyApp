package androidlocationmaps.android.javapapers.com.surveyapp;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class OutputActivity extends AppCompatActivity {

    private SurveyPojo surveyPojo;
    private ListView listView;
    private List<SurveyPojo> list=SurveyActivity.outputSurveyList;
    AdapterClass adapter;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_output_lisview);

        listView = (ListView) findViewById(R.id.survey_output_listview);

        adapter=new AdapterClass();
        listView.setAdapter(adapter);
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
            TextView count;

            ViewHolder(View view) {
                id = (TextView) view.findViewById(R.id.tvId);
                count = (TextView) view.findViewById(R.id.tvCount);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder viewHolder;
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row=inflater.inflate(R.layout.row_survey_output, parent, false);
                viewHolder=new ViewHolder(row);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)row.getTag();
            }
            final SurveyPojo surveyPojo=list.get(position);
            viewHolder.id.setText(surveyPojo.getId());
            viewHolder.count.setText(surveyPojo.getCount()+"");

            return row;
        }
    }
}
