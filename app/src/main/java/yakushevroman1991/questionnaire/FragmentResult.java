package yakushevroman1991.questionnaire;

import android.database.Cursor;
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

public class FragmentResult extends Fragment {
    public static final String TAG = "QuestionnaireActivity";
    private Button rButtonResult;
    private DataBaseSeller rDataBaseSeller;
    private SQLiteDatabase rSqLiteDatabase;
    /*private String sql_inner = "SELECT * FROM " + DataBaseSellerChema.Seller_TABLE.NAME + " SELLER INNER JOIN "
                                                + DataBaseSellerChema.INFORMATION_TABLE.NAME + " INFO ON SELLER.ID=INFO.ID";
    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";*/
    private String sql = "Select * From "
            + DataBaseSellerChema.Seller_TABLE.NAME + " Inner Join " + DataBaseSellerChema.INFORMATION_TABLE.NAME
            + " On " + DataBaseSellerChema.Seller_TABLE.Columns.ID + " = " + DataBaseSellerChema.INFORMATION_TABLE.Columns.ID;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rDataBaseSeller = new DataBaseSeller(getActivity());
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        rButtonResult = view.findViewById(R.id.button_result_questionare);
        rButtonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = rSqLiteDatabase.query(DataBaseSellerChema.INFORMATION_TABLE.NAME, null,null,null,null,null,DataBaseSellerChema.INFORMATION_TABLE.Columns.ID);
                if (cursor.moveToFirst()){
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID));
                        int q = cursor.getInt(cursor.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE));
                        String time = cursor.getString(cursor.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME));
                        Log.d(TAG, "onClick: id :" + id + " --- Q:" +  q  + " --- time: " + time);
                    }while (cursor.moveToNext());
                }
                Cursor cursor1 = rSqLiteDatabase.rawQuery(sql,null);
                if (cursor1.moveToFirst()){
                    do {
                        String name = cursor1.getString(cursor1.getColumnIndex(DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE));
                        int id = cursor1.getInt(cursor1.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID));
                        int q = cursor1.getInt(cursor1.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE));
                        String time = cursor1.getString(cursor1.getColumnIndex(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME));
                        Log.d(TAG, "onClick: " + name + " " + id + " " + q + " " + time);
                    }while (cursor1.moveToNext());
                }

            }
        });
        return view;
    }
}
