package yakushevroman1991.questionnaire;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentAddNewPeople extends Fragment {
    public static final String TAG = "QuestionnaireActivity";

    private Button rButtonAddNewUser;
    private EditText rEditTextNewUser;
    //
    private DataBaseSeller rDataBaseSeller;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_people, container, false);
        Log.d(TAG, "onCreateView: ");
        rEditTextNewUser = view.findViewById(R.id.edit_text_add_new_people);
        rButtonAddNewUser = view.findViewById(R.id.button_add_new_people);
        rButtonAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rEditTextNewUser.getText().toString();
                rContentValues = new ContentValues();
                rContentValues.put(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE, name);
                rSqLiteDatabase.insert(DataBaseSellerChema.Seller_TABLE.NAME, null, rContentValues);
                Log.d(TAG, "onClick: " + name);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);;
                if (fragment != null){
                    fragment = new FragmentListPeople();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rSqLiteDatabase.close();
        Log.d(TAG, "onDestroy: ");
    }
}
