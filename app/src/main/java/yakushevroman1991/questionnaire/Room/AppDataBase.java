package yakushevroman1991.questionnaire.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database( entities = {EntityOne.class, EntityTwo.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DAO getDAO();
}
