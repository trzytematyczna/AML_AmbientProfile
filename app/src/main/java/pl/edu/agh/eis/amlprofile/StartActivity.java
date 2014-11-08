package pl.edu.agh.eis.amlprofile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import pl.edu.agh.eis.database.AmbientDbHelper;
import pl.edu.agh.eis.database.TableEntry;
import pl.edu.agh.eis.datasource.NewProfile;
import pl.edu.agh.eis.datasource.Profile;
import pl.edu.agh.eis.listview.MyProfileArrayAdapter;
import pl.edu.agh.eis.tracker.MyService;

import android.app.ActionBar;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class StartActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	static String TAG = "startActivity";
	
	public static final int NO_OF_TABS = 3;
    private static AmbientDbHelper mDBHelper;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

    public void onSaveClick(String name, boolean checked1, boolean checked2, boolean checked3, boolean checked4, boolean checked5, boolean checked6, boolean checked7) {
        saveToDB(name,checked1, checked2, checked3, checked4, checked5, checked6, checked7);

        //updating ProfilesSection list
        ProfilesSectionFragment.adapter.add(new Profile(R.drawable.agh, name));
        ProfilesSectionFragment.adapter.notifyDataSetChanged();
    }

    public void saveToDB(String name, boolean checked1, boolean checked2, boolean checked3, boolean checked4, boolean checked5, boolean checked6, boolean checked7){
        mDBHelper = new AmbientDbHelper(this.getApplicationContext());
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(TableEntry.ProfileEntry.COLUMN_NAME, name);
        val.put(TableEntry.ProfileEntry.COLUMN_VOLUME_ON, checked1);
        val.put(TableEntry.ProfileEntry.COLUMN_VOLUME_LEVEL, checked2);
        val.put(TableEntry.ProfileEntry.COLUMN_BRIGHTNESS_LEVEL, checked3);
        val.put(TableEntry.ProfileEntry.COLUMN_VIBRATIONS_ON, checked4);
        val.put(TableEntry.ProfileEntry.COLUMN_AIRPLANE_ON, checked5);
        val.put(TableEntry.ProfileEntry.COLUMN_NOTIFICATION_SOUND, checked6);
        val.put(TableEntry.ProfileEntry.COLUMN_SCREEN_TIMEOUT_ON, checked7);

        long id = db.insert(TableEntry.ProfileEntry.TABLE_NAME, null,val);

        db.close();
        mDBHelper.close();

    }

    public void onCancelClick() {
    }

    /**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch(position){
			case 0:
				fragment = new StatusSectionFragment();
				break;
			case 1:
				fragment = new ProfilesSectionFragment();
				break;
			case 2:
				fragment = new MapSectionFragment();
				break;
			}
			return fragment;

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return NO_OF_TABS;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_start).toUpperCase(l);
			case 1:
				return getString(R.string.title_profiles).toUpperCase(l);
			case 2:
				return getString(R.string.title_map).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A status fragment representing a section of the app responsible for displaying
	 * currently active profile.
	 */
	public static class StatusSectionFragment extends Fragment {

		Switch sw1;
		Switch sw2;
		public StatusSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_status_activity,
					container, false);

			sw2 = (Switch)rootView.findViewById(R.id.tracking_switch);

			sw2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked) {
				      Log.d(TAG, "onClick: starting srvice");
//				      Intent intent = new Intent(getActivity().getApplicationContext(), MyService.class);
				      getActivity().startService(new Intent(getActivity().getApplicationContext(), MyService.class));
					}
				    else{
				      Log.d(TAG, "onClick: stopping srvice");
				      getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyService.class));
				    }
				}

			});


			return rootView;
		}
	}

	/**
	 * A status fragment representing a section of the app responsible for displaying 
	 * currently active profile.
	 */
	public static class ProfilesSectionFragment extends ListFragment implements AdapterView.OnItemClickListener{
        ArrayAdapter<String> arrayAdapter;
        static MyProfileArrayAdapter adapter;
        ArrayList <Profile> profiles;
        public ProfilesSectionFragment() {
		}


		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_profiles_activity,container, false);
            profiles= getAll();
            adapter = new MyProfileArrayAdapter(getActivity().getApplicationContext(), profiles);
            setListAdapter(adapter);

            return rootView;
		}

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActCreated");
//            arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, getAll());
//            arrayAdapter.notifyDataSetChanged();
//            setListAdapter(arrayAdapter);

//            getListView().setOnItemClickListener(this);
        }

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT)
                    .show();

        }
        private ArrayList<Profile> getAll() {
            String selectQuery = "SELECT  * FROM " +  TableEntry.ProfileEntry.TABLE_NAME;
            mDBHelper = new AmbientDbHelper(getActivity());
            SQLiteDatabase db = mDBHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(selectQuery, null);
            ArrayList<Profile> profiles = new ArrayList<Profile>();
            int i=0;
            try {
                if (cursor.moveToFirst()) {
                    do {
                         if(i%3==1){
                             profiles.add(i, new Profile(R.drawable.day, cursor.getString(1)));
                        }
                        else if(i%2==1){
                             profiles.add(i, new Profile(R.drawable.night, cursor.getString(1)));
                        }
                        else {
                             profiles.add(i, new Profile(R.drawable.agh, cursor.getString(1)));
                        }
                    } while (cursor.moveToNext());
                }
            }
            finally{
                cursor.close();
                db.close();
                mDBHelper.close();
            }
            return profiles;
        }
	}
	
	/**
	 * A status fragment representing a section of the app responsible for displaying 
	 * currently active profile.
	 */
	public static class MapSectionFragment extends Fragment {

		public MapSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map_activity,
					container, false);
	
			return rootView;
		}
	}
	
	public void onButtonProfileClick(View view){
		Log.d(TAG, "buttonClick");
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = new NewProfile();
        dialog.show(fm, "DIALOG new profile");
	}

}
