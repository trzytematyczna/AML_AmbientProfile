
package pl.edu.agh.eis.listview;


import pl.edu.agh.eis.amlprofile.R;
import pl.edu.agh.eis.datasource.Profile;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyProfileArrayAdapter extends ArrayAdapter<Profile> {
    private final Context context;
    private final ArrayList<Profile> values;
    Typeface titleFont;
    Typeface descriptionFont;

    public static class ViewHolder{
        protected  TextView text;
        protected ImageView icon;
    }

    public MyProfileArrayAdapter(Context context, ArrayList<Profile> values) {
        super(context,R.layout.profile_row, values);
        this.context = context;
        this.values = values;
    }
//    public void updateProfiles(ArrayList<Profile> profiles) {
//        this.clear();
//        this.addAll(profiles);
//        this.notifyDataSetChanged();
//    }
    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.profile_row, null);

            viewHolder = new ViewHolder();
            viewHolder.text = (TextView)rowView.findViewById(R.id.profile_title);


            viewHolder.icon = (ImageView) rowView.findViewById(R.id.profile_icon_image);

            rowView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) rowView.getTag();
        }

        titleFont = Typeface.createFromAsset(getContext().getAssets(), "ChampagneLimousinesBold.ttf");
        descriptionFont = Typeface.createFromAsset(getContext().getAssets(), "ChampagneLimousines.ttf");
        viewHolder.text.setTypeface(titleFont);
        viewHolder.text.setText(values.get(position).getName());
        viewHolder.icon.setImageResource(values.get(position).getIconResourceID());

        return rowView;

    }


}
