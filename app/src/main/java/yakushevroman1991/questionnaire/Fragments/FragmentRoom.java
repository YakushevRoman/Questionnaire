package yakushevroman1991.questionnaire.Fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yakushevroman1991.questionnaire.PojoClass.Questionnaire;
import yakushevroman1991.questionnaire.QuestioningConstants;
import yakushevroman1991.questionnaire.R;
import yakushevroman1991.questionnaire.Room.AppDataBase;
import yakushevroman1991.questionnaire.Room.DAO;
import yakushevroman1991.questionnaire.Room.EntityOne;
import yakushevroman1991.questionnaire.Room.EntityTwo;

public class FragmentRoom extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_room, container, false);
        Log.d(QuestioningConstants.TAG, "onCreateView: FragmentRoom");

        AppDataBase appDataBase = Room.databaseBuilder(getActivity(), AppDataBase.class, "my.db")
                .allowMainThreadQueries()
                .build();
        Log.d(QuestioningConstants.TAG, "onCreateView: " + appDataBase.toString());
        DAO dao = appDataBase.getDAO();

        EntityTwo entityTwo = new EntityTwo();
        entityTwo.namePeople = "Ivan1";
        //dao.insertTwo(entityTwo);

        List <EntityTwo> entityTwos = dao.getAllEntityTwo();
        Log.d(QuestioningConstants.TAG, "onCreateView: " + entityTwos.size());

        return  view;
    }
}
