package pl.edu.agh.eis.listview;


import pl.edu.agh.eis.amlprofile.R;
import pl.edu.agh.eis.datasource.Profile;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfileArrayAdapter extends ArrayAdapter<Profile> {
  private final Context context;
  private final Profile[] values;
  Typeface titleFont;
  Typeface descriptionFont;
  
  public static class ViewHolder{
	  /**
	   * Consider using ViewHolder pattern
	   */
  }
  
  public MyProfileArrayAdapter(Context context, Profile[] values) {
    super(context,R.layout.profile_row, values);
    this.context = context;
    this.values = values;


  }

  @Override
  public View getView(int position, View rowView, ViewGroup parent) {    
    if (rowView == null) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	rowView = inflater.inflate(R.layout.profile_row, null);
    }
    
    titleFont = Typeface.createFromAsset(getContext().getAssets(), "ChampagneLimousinesBold.ttf");
    descriptionFont = Typeface.createFromAsset(getContext().getAssets(), "ChampagneLimousines.ttf");
    
    TextView profileTitle = (TextView) rowView.findViewById(R.id.profile_title);
    profileTitle.setTypeface(titleFont);
    profileTitle.setText(values[position].getName());
    
    ImageView icon = (ImageView) rowView.findViewById(R.id.profile_icon_image);
    icon.setImageResource(values[position].getIconResourceID());
    
    
    return rowView;
  }


} 