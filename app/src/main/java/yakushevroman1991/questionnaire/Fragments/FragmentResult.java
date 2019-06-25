package yakushevroman1991.questionnaire.Fragments;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import yakushevroman1991.questionnaire.DataBase.DataBaseSeller;
import yakushevroman1991.questionnaire.DataBaseSellerChema;
import yakushevroman1991.questionnaire.QuestioningConstants;
import yakushevroman1991.questionnaire.R;

public class FragmentResult extends Fragment {
    private SQLiteDatabase rSqLiteDatabase;
    // for counting percentage
    private int count_positive = 0;
    private int count_usual = 0;
    private int count_negative = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create database
        // for database
        DataBaseSeller rDataBaseSeller = new DataBaseSeller(getActivity());
        // create object for to read information
        rSqLiteDatabase = rDataBaseSeller.getReadableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate from resurs a layout for the view
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        // push prosessing the button
        Button rButtonResult = view.findViewById(R.id.button_result_questionare);
        rButtonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                new GetResultQuestionary().execute();
            }
        });
        return view;
    }
    // the method is  for to find the information about users
    public void getQuestionary (String name){
        // sql the request
        String sql =
                "Select * From "
                        + DataBaseSellerChema.Seller_TABLE.NAME + " , " + DataBaseSellerChema.INFORMATION_TABLE.NAME
                        + " Where " + DataBaseSellerChema.Seller_TABLE.Columns.ID + " = " + DataBaseSellerChema.INFORMATION_TABLE.Columns.ID
                        + " And " + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE + " = '"+ name + "'"
                        + " Order by " + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE;

        // create cursor for the data from the database
        @SuppressLint("Recycle")
        Cursor cursorResultUsersSurvey = rSqLiteDatabase.rawQuery(sql,null);
        if (cursorResultUsersSurvey.moveToFirst()){
            do {
                // get name person
                String namePerson = cursorResultUsersSurvey.getString(cursorResultUsersSurvey.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE));
                // get id person
                int id = cursorResultUsersSurvey.getInt(cursorResultUsersSurvey.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID));
                // get questioning
                int questioning = cursorResultUsersSurvey.getInt(cursorResultUsersSurvey.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE));
                // count positive results of questioning
                if (QuestioningConstants.QUESTIONNAIRE_HAPPY == questioning) ++count_positive;
                // count positive results of questioning
                if (QuestioningConstants.QUESTIONNAIRE_USUAL  == questioning) ++count_usual;
                // count positive results of questioning
                if (QuestioningConstants.QUESTIONNAIRE_UNHAPPY == questioning) ++count_negative;
                //
                String time = cursorResultUsersSurvey.getString(cursorResultUsersSurvey.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME));
                Log.d(QuestioningConstants.TAG, String.format("onClick: namePerson: %s , id: %s , questioning: %s, time: %s", namePerson, id, questioning, time));
            }while (cursorResultUsersSurvey.moveToNext());
        }
        // count all results of questioning
        int rAllCount = count_positive + count_usual + count_negative;
        // count procents positive
        double rProcentCountPositive = Math.round((double) count_positive*100 / rAllCount);
        // count procents usual
        double  rProcentCountUsual= Math.round((double)count_usual*100 / rAllCount);
        // count procents negative
        double rProcentCountNegative = Math.round((double)count_negative*100 / rAllCount);
        // enter log
        Log.d(QuestioningConstants.TAG, String.format("onClick: \n count positive: %s, count usual: %s, count negative: %s", count_positive, count_usual,count_negative ));
        Log.d(QuestioningConstants.TAG, String.format("onClick: \n Procent questioning : \n Positive: %s , Usual: %s, Negative: %s", rProcentCountPositive,rProcentCountUsual,rProcentCountNegative));
        // assign
        count_positive = 0;
        count_usual = 0;
        count_negative = 0;

    }
    /*AsyncTask<[Input_Parameter Type], [Progress_Report Type], [Result Type]>
    1 параметр - передаваемый тип в doInBackground
    2 параметр - тип для процесса если нужно
    3 параметр - Тип результата после выполнения doInBackground
     */
    @SuppressLint("StaticFieldLeak")
    private class GetResultQuestionary extends AsyncTask<Void, Void, Void>{
        // start i
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(QuestioningConstants.TAG, "onPreExecute: ");
        }
        // do a request with parametrs to seach
        @Override
        protected Void doInBackground(Void... voids) {
            getQuestionary("Roma");
            Log.d(QuestioningConstants.TAG, "doInBackground: ");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(QuestioningConstants.TAG, "onPostExecute: ");
        }
    }
    // closind conection with database
    @Override
    public void onDestroy() {
        rSqLiteDatabase.close();
        Log.d(QuestioningConstants.TAG, "onDestroy: ");
        super.onDestroy();
    }
}
