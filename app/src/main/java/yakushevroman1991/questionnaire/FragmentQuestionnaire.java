package yakushevroman1991.questionnaire;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

public class FragmentQuestionnaire extends Fragment {
    public static final String TAG = "QuestionnaireActivity";
    public static final int QUESTIONNAIRE_HAPPY = 1;
    public static final int QUESTIONNAIRE_USUAL = 2;
    public static final int QUESTIONNAIRE_UNHAPPY = 3;
    //
    private Button rButtonHappy;
    private Button rButtonUsual;
    private Button rButtonUnHappy;
    //
    private DataBaseSeller rDataBaseSeller;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    //
    private int id;
    Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        bundle = getArguments();
        id = bundle.getInt("ID");
        bundle.clear();
        Log.d(TAG, "onCreate FragmentQuestionnaire: " + id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);

        rButtonHappy = view.findViewById(R.id.happy_button);
        rButtonUsual = view.findViewById(R.id.usual_button);
        rButtonUnHappy = view.findViewById(R.id.unhappy_button);

        rButtonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);

                rContentValues = new ContentValues();
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID, id);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE, QUESTIONNAIRE_HAPPY);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME,timeText);
                rSqLiteDatabase.insert(DataBaseSellerChema.INFORMATION_TABLE.NAME, null, rContentValues);

                Log.d(TAG, "onClick FragmentQuestionnaire: " + id + "---" + "time" + timeText + " QUESTIONNAIRE " + QUESTIONNAIRE_HAPPY);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        rButtonUsual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);

                rContentValues = new ContentValues();
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID, id);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE, QUESTIONNAIRE_USUAL);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME,timeText);
                rSqLiteDatabase.insert(DataBaseSellerChema.INFORMATION_TABLE.NAME, null, rContentValues);

                Log.d(TAG, "onClick FragmentQuestionnaire: " + id + "---" + "time: " + timeText + "---" + " QUESTIONNAIRE: " + QUESTIONNAIRE_USUAL);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        rButtonUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);

                rContentValues = new ContentValues();
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID, id);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE, QUESTIONNAIRE_UNHAPPY);
                rContentValues.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME,timeText);
                rSqLiteDatabase.insert(DataBaseSellerChema.INFORMATION_TABLE.NAME, null, rContentValues);

                Log.d(TAG, "onClick FragmentQuestionnaire: " + id + "---" + "time" + timeText + " QUESTIONNAIRE " + QUESTIONNAIRE_UNHAPPY);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: FragmentQuestionnaire");
        bundle.clear();
        rSqLiteDatabase.close();
    }
}
