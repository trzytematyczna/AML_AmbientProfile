//package pl.edu.agh.eis.tracker;
//
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//
//public class GPSTracker extends Activity implements OnClickListener {
//	  private static final String TAG = "Tracking Service";
//	  
//	  Button buttonStart, buttonStop;
//
//	  @Override
//	  public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.activity_gpstracker);
//
//	    buttonStart = (Button) findViewById(R.id.buttonStart);
//	    buttonStop = (Button) findViewById(R.id.buttonStop);
//
//	    buttonStart.setOnClickListener(this);
//	    buttonStop.setOnClickListener(this);
//	  }
//	  
//	
//
//	  public void onClick(View src) {
//	    switch (src.getId()) {
//	    case R.id.buttonStart:
//	      Log.d(TAG, "onClick: starting srvice");
//	      startService(new Intent(this, MyService.class));
//	      break;
//	    case R.id.buttonStop:
//	      Log.d(TAG, "onClick: stopping srvice");
//	      stopService(new Intent(this, MyService.class));
//	      break;
//
//	    }
//	  }
//}
