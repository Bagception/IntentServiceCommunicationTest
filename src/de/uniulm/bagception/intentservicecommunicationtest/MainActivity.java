package de.uniulm.bagception.intentservicecommunicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;
import de.uniulm.bagception.intentservicecommunication.MyResultReceiver;
import de.uniulm.bagception.intentservicecommunication.MyResultReceiver.Receiver;

public class MainActivity extends Activity implements Receiver {

	private MyResultReceiver mResultreceiver;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mResultreceiver = new MyResultReceiver(new Handler());
		mResultreceiver.setReceiver(this);
		
	}

	
	public void onGoButtonClicked(View v){
		String serviceString = "de.uniulm.bagception.intentservicecommunication.IntentServiceCommunicationService";
		
		Intent i = new Intent(serviceString);
		i.putExtra("origin","A" );
		i.putExtra("receiverTag", mResultreceiver);
		startService(i);
		
	}
	

	
	@Override
	public void onReceiveResult(final int resultCode, final Bundle resultData) {
		Toast.makeText(this, resultCode+": "+resultData.getString("payload"), Toast.LENGTH_SHORT).show();
		
		
	}

}
