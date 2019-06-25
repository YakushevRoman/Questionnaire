package yakushevroman1991.questionnaire.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Query("Select * from entityone")
    List <EntityOne> getAll ();

    @Insert
    void insertOne (EntityOne entityOne);

    @Query("Select * from entitytwo")
    List <EntityTwo> getAllEntityTwo ();


    @Insert
    void insertTwo (EntityTwo entityTwo);


}
