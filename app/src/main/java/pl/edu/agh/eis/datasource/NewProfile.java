package pl.edu.agh.eis.datasource;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import pl.edu.agh.eis.amlprofile.R;
import pl.edu.agh.eis.amlprofile.StartActivity;

public class NewProfile extends DialogFragment {


    StartActivity mCallback;
	EditText mEditText;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    CheckBox check4;
    CheckBox check5;
    CheckBox check6;
    CheckBox check7;
//        //Volume - on/off
//        //Volume -level
//        //Brightness - level
//        //Vibrations - on/off
//        //DataRoaming
//        //Airplane mode - on/off
//        //Notification sound
//        //screen offtimeout
////        android.provider.Settings.System.?

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mCallback = (StartActivity) getActivity();//getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Maker dialog, onCreate");
        }
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.new_profile_activity, null);
        mEditText = (EditText) view.findViewById(R.id.new_profile_name);
        check1 = (CheckBox) view.findViewById(R.id.checkBox);
        check2 = (CheckBox) view.findViewById(R.id.checkBox2);
        check3 = (CheckBox) view.findViewById(R.id.checkBox3);
        check4 = (CheckBox) view.findViewById(R.id.checkBox4);
        check5 = (CheckBox) view.findViewById(R.id.checkBox5);
        check6 = (CheckBox) view.findViewById(R.id.checkBox6);
        check7 = (CheckBox) view.findViewById(R.id.checkBox7);

        builder.setView(view);
        builder.setMessage("Save?")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (mEditText.getText() != null) {
                            mCallback.onSaveClick(mEditText.getText().toString(), check1.isChecked(),
                                    check2.isChecked(), check3.isChecked(), check4.isChecked(),
                                    check5.isChecked(),check6.isChecked(),check7.isChecked());
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mCallback.onCancelClick();
            }
        });
        return builder.create();
    }



}
