package yakushevroman1991.questionnaire;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rListPeople = new ArrayList<>();


        rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
        rContentValues = new ContentValues();
        rCursor = rSqLiteDatabase.query(DataBaseSellerChema.Seller_TABLE.NAME, null, null, null, null, null, null);
        if (rCursor.moveToFirst()){
            do{
                int id = rCursor.getInt(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.ID));
                String name = rCursor.getString(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE));
                ListPeople listPeople = new ListPeople(id,name);
                rListPeople.add(listPeople);
                Log.d(TAG, "onCreate: -----" + id + " ----" + name);
            }while (rCursor.moveToNext());
        }
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
        }

        @NonNull
        @Override
        public PeopleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_list_recycler_view, viewGroup, false);
            return new PeopleHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PeopleHolder peopleHolder, int i) {

            ListPeople listPeople = rListPeople.get(i);

            peopleHolder.setButtonText(listPeople);
        }

        @Override
        public int getItemCount() {
            return rListPeople.size();
        }
    }

    private class PeopleHolder extends RecyclerView.ViewHolder{

        private Button rButtonListItem;

        public PeopleHolder(@NonNull View itemView) {
            super(itemView);
            rButtonListItem = itemView.findViewById(R.id.button_people);
        }

        public void setButtonText (ListPeople listPeople){
            rButtonListItem.setText(listPeople.getTitle());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rSqLiteDatabase.close();
    }
}
