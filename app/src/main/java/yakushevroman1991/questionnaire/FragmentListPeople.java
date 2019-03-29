package yakushevroman1991.questionnaire;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentListPeople extends Fragment {
    public static final String TAG = "QuestionnaireActivity";
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
        new GetList().execute();
    }

    /*AsyncTask<[Input_Parameter Type], [Progress_Report Type], [Result Type]>
    1 параметр - передаваемый тип в doInBackground
    2 параметр - тип для процесса если нужно
    3 параметр - Тип результата после выполнения doInBackground
     */

    private class GetList extends AsyncTask<Void, Void, List<ListPeople>>{
        List <ListPeople> listPeoples;
        @Override
        protected void onPreExecute() {
            listPeoples = new ArrayList<>();
            rDataBaseSeller = new DataBaseSeller(getActivity());
            rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
            rContentValues = new ContentValues();
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected List<ListPeople> doInBackground(Void... voids) {
            rCursor = rSqLiteDatabase.query(DataBaseSellerChema.Seller_TABLE.NAME, null, null, null, null, null, null, null);
            if (rCursor.moveToFirst()){
                do{
                    int id = rCursor.getInt(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.ID));
                    String name = rCursor.getString(rCursor.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE));
                    ListPeople listPeople = new ListPeople(id,name);
                    listPeoples.add(listPeople);
                    Log.d(TAG, "doInBackground: ----- " + id + " ----" + name);
                }while (rCursor.moveToNext());
                Log.d(TAG, "doInBackground: " + listPeoples.size());
            }
            rListPeople = listPeoples;
            return rListPeople;
        }

        @Override
        protected void onPostExecute(List<ListPeople> listPeople){
            rListPeople = listPeople;
            Log.d(TAG, "onPostExecute: ");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        rBundle = new Bundle();
        Log.d(TAG, "onStart: ");
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
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_list_recycler_view, viewGroup, false);
            return new PeopleHolder(getActivity(),view);
        }

        @Override
        public void onBindViewHolder(@NonNull PeopleHolder peopleHolder, int i) {

            ListPeople listPeople = rListPeople.get(i);
            peopleHolder.settingsButton(listPeople);
            Log.d(TAG, "onBindViewHolder: " + listPeople.getId());
        }

        @Override
        public int getItemCount() {
            return rListPeople.size();
        }
    }

    private class PeopleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button rButtonListItem;
        private Context context;

        public PeopleHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            rButtonListItem = itemView.findViewById(R.id.button_people);
            itemView.setOnClickListener(this);
        }

        public void settingsButton(ListPeople listPeople){
            rButtonListItem.setText(listPeople.getTitle());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int IDsql = rListPeople.get(position).getId();
            Log.d(TAG, "onClick: position" + IDsql);
            FragmentManager  fragmentManager = getFragmentManager();
            Fragment fragment = Objects.requireNonNull(fragmentManager).findFragmentById(R.id.fragment_container);
            if (fragment != null){
                fragment = new FragmentQuestionnaire();
                rBundle.putInt("ID", IDsql);
                fragment.setArguments(rBundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        rSqLiteDatabase.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
