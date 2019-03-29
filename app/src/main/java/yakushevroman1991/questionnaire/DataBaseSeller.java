package yakushevroman1991.questionnaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseSeller extends SQLiteOpenHelper {
    // Tag for log
    private static final String TAG = "QuestionnaireActivity";
    // version Db
    private static final int VERSION = 12;
    // database name
    private static final String DATABASE_NAME = "seller1.db";
    // this`s the string to create  TABLE_PEOPLE
    private static final String TABLE_PEOPLE =
            "CREATE TABLE " + DataBaseSellerChema.Seller_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.Seller_TABLE.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE + " TEXT"
                    + ");";
    // this`s the string to create  TABLE_QUESTIONNAIRE
    private static final String TABLE_QUESTIONNAIRE =
            "CREATE TABLE " + DataBaseSellerChema.INFORMATION_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.ID + " INTEGER,"
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE + " INTEGER,"
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME + " TEXT"
                    + ");";

    /**
     * these stings`re for delete tables
     */
    private static final String sql_drop_people_table = "DROP TABLE IF EXISTS " + DataBaseSellerChema.Seller_TABLE.NAME;
    private static final String sql_drop_questionnaire_table = "DROP TABLE IF EXISTS " + DataBaseSellerChema.INFORMATION_TABLE.NAME;

    // constructor for database
     DataBaseSeller(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // this the method is called when the database created  first times.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: TABLE_PEOPLE");
        db.execSQL(TABLE_PEOPLE);
        db.execSQL(TABLE_QUESTIONNAIRE);
    }

    // this the method is called when the database will update.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sql_drop_people_table);
        db.execSQL(sql_drop_questionnaire_table);
        onCreate(db);
    }
}
