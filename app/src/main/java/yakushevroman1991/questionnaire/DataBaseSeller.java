package yakushevroman1991.questionnaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseSeller extends SQLiteOpenHelper {
    public static final String TAG  = "DataBaseSeller";

    public static final int VERSION = 6;
    public static final String DATABASE_NAME = "seller1.db";
    /**
     *
     */
    public static final String TABLE_PEOPLE =
            "CREATE TABLE " + DataBaseSellerChema.Seller_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.Seller_TABLE.Columns.ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE + " TEXT"
                    + ");" ;
    /**
     *
     */
    public static final String TABLE_QUESTIONNAIRE =
            "CREATE TABLE " + DataBaseSellerChema.INFORMATION_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.ID + " INTEGER,"
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE + " INTEGER,"
                    + DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME + " TEXT"
                    + ");";

    /**
     *
     * добавляем начальные данные
     */
    public static final String add_new_people_one =
            "INSERT INTO " + DataBaseSellerChema.Seller_TABLE.NAME
            + "("
            + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE
            + ") VALUES ('THIS IS 1')";
    public static final String add_new_people_two =
            "INSERT INTO " + DataBaseSellerChema.Seller_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE
                    + ") VALUES ('THIS IS 2')";
    public static final String add_new_people_three =
            "INSERT INTO " + DataBaseSellerChema.Seller_TABLE.NAME
                    + "("
                    + DataBaseSellerChema.Seller_TABLE.Columns.NAME_PEOPLE
                    + ") VALUES ('THIS IS 3')";
    //
    public static final String add_information =
            "INSERT INTO " + DataBaseSellerChema.INFORMATION_TABLE.NAME
            + "("
            + DataBaseSellerChema.INFORMATION_TABLE.Columns.ID + ", "
            + DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE + ", "
            + DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME
            + ") VALUES (1, 2, '12:32')";

    /**
     *
     * удаление таблиц
     */
    public static final String sql_drop_people_table = "DROP TABLE IF EXISTS " + DataBaseSellerChema.Seller_TABLE.NAME ;
    public static final String sql_drop_questionnaire_table = "DROP TABLE IF EXISTS " + DataBaseSellerChema.INFORMATION_TABLE.NAME;

    public DataBaseSeller(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: TABLE_PEOPLE");
        db.execSQL(TABLE_PEOPLE);

        db.execSQL(add_new_people_one);
        db.execSQL(add_new_people_two);
        db.execSQL(add_new_people_three);
        db.execSQL(TABLE_QUESTIONNAIRE);
        Log.d(TAG, "onCreate: TABLE_QUESTIONNAIRE");
        db.execSQL(add_information);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sql_drop_people_table);
        db.execSQL(sql_drop_questionnaire_table);

        onCreate(db);
    }
}
