package yakushevroman1991.questionnaire;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentQuestionnaire extends Fragment {

    private Button rButtonHappy;
    private Button rButtonUsual;
    private Button rButtonUnHappy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);

        rButtonHappy = view.findViewById(R.id.happy_button);
        rButtonUsual = view.findViewById(R.id.usual_button);
        rButtonUnHappy = view.findViewById(R.id.unhappy_button);

        rButtonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "happy_button",Toast.LENGTH_SHORT).show();
            }
        });

        rButtonUsual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "usual_button",Toast.LENGTH_SHORT).show();
            }
        });

        rButtonUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "unhappy_button",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
