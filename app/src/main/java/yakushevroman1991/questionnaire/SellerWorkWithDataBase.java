package yakushevroman1991.questionnaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SellerWorkWithDataBase {
    private Context rContext;
    private DataBaseSeller rDataBaseSeller;
    private SQLiteDatabase rSqLiteDatabase;

    public SellerWorkWithDataBase(Context context) {
        this.rContext = context.getApplicationContext();
        rDataBaseSeller = new DataBaseSeller(rContext);
        rSqLiteDatabase = rDataBaseSeller.getWritableDatabase();
    }
    // добавление данных
    public static ContentValues getContentValuesQuestionnaire(Questionnaire questionnaire){
        ContentValues values = new ContentValues();
        values.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.ID, questionnaire.getId());
        values.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.QUESTIONNAIRE, questionnaire.getQuestion());
        values.put(DataBaseSellerChema.INFORMATION_TABLE.Columns.TIME,questionnaire.getTime());
        return values;
    }
    // добавление данных
    private static ContentValues getContentValuesListPeople(ListPeople listPeople){
        ContentValues values = new ContentValues();
        return values;
    }

}
