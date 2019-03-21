package yakushevroman1991.questionnaire;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentListPeople extends Fragment {
    public static final String TAG = "FragmentListPeople";

    private List <ListPeople> rListPeople;

    private RecyclerView rRecyclerViewl;

    private DataBaseSeller rDataBaseSeller;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    private Cursor rCursor;
    //
    private Bundle rBundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rListPeople = new ArrayList<>();


        rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        rContentValues = new ContentValues();
        rCursor = rSqLiteDatabase.query(DataBaseSellerChema.Seller_TABLE.NAME, null, null, null, null, null, null, null);
        if (rCursor.moveToFirst()){
            do{
                int id = rCursor.getInt(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.ID));
                String name = rCursor.getString(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE));
                ListPeople listPeople = new ListPeople(id,name);
                rListPeople.add(listPeople);
                Log.d(TAG, "onCreate: ----- " + id + " ----" + name);
            }while (rCursor.moveToNext());
            Log.d(TAG, "onCreate: " + rListPeople.size());
        }

        rBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_people, container, false);
        rRecyclerViewl = view.findViewById(R.id.recycler_view_list_people);
        rRecyclerViewl.setLayoutManager(new LinearLayoutManager(getActivity()));
        rRecyclerViewl.setAdapter(new PeopleAdapter(rListPeople));


        return view;
    }

    private class PeopleAdapter extends RecyclerView.Adapter<PeopleHolder>{
        List <ListPeople> rListPeople;

        public PeopleAdapter(List<ListPeople> rListPeople) {
            this.rListPeople = rListPeople;
            Log.d(TAG, "PeopleAdapter: " + rListPeople.size());
        }

        @NonNull
        @Override
        public PeopleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_list_recycler_view, viewGroup, false);
            return new PeopleHolder(view, rListPeople);
        }

        @Override
        public void onBindViewHolder(@NonNull PeopleHolder peopleHolder, int i) {

            ListPeople listPeople = rListPeople.get(i);
            peopleHolder.settingsButton(listPeople);
            rBundle.putInt("ID", listPeople.getId());
        }

        @Override
        public int getItemCount() {
            return rListPeople.size();
        }
    }

    private class PeopleHolder extends RecyclerView.ViewHolder {

        private Button rButtonListItem;
        private List<ListPeople> rListPeople;

        public PeopleHolder(@NonNull View itemView, List<ListPeople> listPeople) {
            super(itemView);
            this.rListPeople = listPeople;
            rButtonListItem = itemView.findViewById(R.id.button_people);
            rButtonListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager  fragmentManager = getFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                    if (fragment != null){
                        fragment = new FragmentQuestionnaire();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                    fragment.setArguments(rBundle);
                }
            });
        }

        public void settingsButton(ListPeople listPeople){
            rButtonListItem.setText(listPeople.getTitle());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rSqLiteDatabase.close();
    }
}
