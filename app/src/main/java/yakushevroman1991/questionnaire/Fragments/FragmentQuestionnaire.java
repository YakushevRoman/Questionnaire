package yakushevroman1991.questionnaire.Fragments;
import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import yakushevroman1991.questionnaire.DataBase.DataBaseSeller;
import yakushevroman1991.questionnaire.DataBaseSellerChema;
import yakushevroman1991.questionnaire.QuestioningConstants;
import yakushevroman1991.questionnaire.R;

public class FragmentQuestionnaire extends Fragment {
    private SQLiteDatabase rSqLiteDatabase;
    //
    private int id;
    Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        DataBaseSeller rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("ID");
        bundle.clear();
        Log.d(QuestioningConstants.TAG, "onCreate FragmentQuestionnaire: " + id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);
        //
        Button rButtonHappy = view.findViewById(R.id.happy_button);
        Button rButtonUsual = view.findViewById(R.id.usual_button);
        Button rButtonUnHappy = view.findViewById(R.id.unhappy_button);

        rButtonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddDataQuestionnaire().execute(QuestioningConstants.QUESTIONNAIRE_HAPPY);
                //addDataSqliteQuestionnaire(QUESTIONNAIRE_HAPPY);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        rButtonUsual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddDataQuestionnaire().execute(QuestioningConstants.QUESTIONNAIRE_USUAL);
                //addDataSqliteQuestionnaire(QUESTIONNAIRE_USUAL);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        rButtonUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddDataQuestionnaire().execute(QuestioningConstants.QUESTIONNAIRE_UNHAPPY);
                //addDataSqliteQuestionnaire(QUESTIONNAIRE_UNHAPPY);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(QuestioningConstants.TAG, "onDestroy: FragmentQuestionnaire");
        bundle.clear();
        rSqLiteDatabase.close();
    }

    private void addDataSqliteQuestionnaire(int question){
        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        ContentValues rContentValues = new ContentValues();
        rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID, id);
        rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE, question);
        rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME,timeText);
        rSqLiteDatabase.insert(DataBaseSellerChema.INFORMATION_TABLE.NAME, null, rContentValues);

        Log.d(QuestioningConstants.TAG, "onClick FragmentQuestionnaire: " + id + "---" + "time" + timeText + " QUESTIONNAIRE " + question);
    }
    // background task for add results of questioning.
    @SuppressLint("StaticFieldLeak")
    private class AddDataQuestionnaire extends AsyncTask<Integer, Void, Void>{
        // main thread - before add results of questioning.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        // background - add results of questioning
        @Override
        protected Void doInBackground(Integer... integers) {
            addDataSqliteQuestionnaire(integers[0]);
            return null;
        }
        // main thread - after add results of questioning
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
