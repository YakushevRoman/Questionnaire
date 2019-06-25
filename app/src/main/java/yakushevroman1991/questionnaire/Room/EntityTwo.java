package yakushevroman1991.questionnaire.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EntityTwo {

    @PrimaryKey (autoGenerate = true)
    public long _id;

    @ColumnInfo (name = "entityTwo_name_people")
    public String namePeople;
}
