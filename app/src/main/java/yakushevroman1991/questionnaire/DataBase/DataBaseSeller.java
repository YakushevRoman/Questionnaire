package yakushevroman1991.questionnaire.DataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import yakushevroman1991.questionnaire.DataBaseSellerChema;
import yakushevroman1991.questionnaire.QuestioningConstants;
/**
 *
 */
public class DataBaseSeller extends SQLiteOpenHelper {
    // version Db
    private static final int VERSION = 12;
    // database name
    private static final String DATABASE_NAME = "seller1.db";
    // the string for create  TABLE_PEOPLE
    private static final String TABLE_PEOPLE =
            "CREATE TABLE " + DataBaseSellerChema.Seller_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.Seller_TABLE.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE + " TEXT"
                    + ");";
    // the string for create TABLE_QUESTIONNAIRE
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
    public DataBaseSeller(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(QuestioningConstants.TAG, "onCreate: TABLE_PEOPLE");
        db.execSQL(TABLE_PEOPLE);
        Log.d(QuestioningConstants.TAG, "onCreate: TABLE_QUESTIONNAIRE");
        db.execSQL(TABLE_QUESTIONNAIRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(QuestioningConstants.TAG, "DROP : TABLE_PEOPLE");
        db.execSQL(sql_drop_people_table);
        Log.d(QuestioningConstants.TAG, "DROP : TABLE_QUESTIONNAIRE");
        db.execSQL(sql_drop_questionnaire_table);
        Log.d(QuestioningConstants.TAG, "onUpgrade");
        onCreate(db);
    }
}
