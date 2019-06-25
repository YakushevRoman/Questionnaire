package yakushevroman1991.questionnaire.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EntityOne {

    @PrimaryKey
    public long _id;

    @ColumnInfo (name = "entityOne_questinnare")
    public int questionnaire;

    @ColumnInfo (name =  "entityOne_time")
    public String time;
}
