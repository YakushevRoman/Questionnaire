package yakushevroman1991.questionnaire.Fragments;

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
import java.util.Objects;
import yakushevroman1991.questionnaire.DataBase.DataBaseSeller;
import yakushevroman1991.questionnaire.DataBaseSellerChema;
import yakushevroman1991.questionnaire.QuestioningConstants;
import yakushevroman1991.questionnaire.R;

public class FragmentAddNewPeople extends Fragment {
    //the class field
    private EditText rEditTextNewUser;
    // for to work with the database
    private SQLiteDatabase rSqLiteDatabase;
    // for to write a new user
    private ContentValues rContentValues;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get database
        DataBaseSeller rDataBaseSeller = new DataBaseSeller(getActivity());
        // create the object database for to write information
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        Log.d(QuestioningConstants.TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate from res a container for a fragment
        View view = inflater.inflate(R.layout.fragment_add_new_people, container, false);
        Log.d(QuestioningConstants.TAG, "onCreateView: fragment_add_new_people");
        // find component
        rEditTextNewUser = view.findViewById(R.id.edit_text_add_new_people);
        Button rButtonAddNewUser = view.findViewById(R.id.button_add_new_people);
        // processing of the results
        rButtonAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reading new user name
                String name = rEditTextNewUser.getText().toString();
                //
                rContentValues = new ContentValues();
                rContentValues.put(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE, name);
                //
                rSqLiteDatabase.insert(DataBaseSellerChema.Seller_TABLE.NAME, null, rContentValues);
                // I write in log new name
                Log.d(QuestioningConstants.TAG, "onClick: " + name);
                // work with fragment and go back in the parents fragment
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
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
    // close database
    @Override
    public void onDestroy() {
        super.onDestroy();
        rSqLiteDatabase.close();
        Log.d(QuestioningConstants.TAG, "onDestroy: ");
    }
}
