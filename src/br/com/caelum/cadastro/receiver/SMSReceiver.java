package br.com.caelum.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;

public class SMSReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		
		DAOHelper helper = new DAOHelper(context);
		AlunoDAO alunoDAO = new AlunoDAO(helper);
		
		byte[] message = (byte[]) messages[0];
		
		SmsMessage sms = SmsMessage.createFromPdu( message );
		
		String numero = sms.getDisplayOriginatingAddress();
		
		MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
		mp.start();
		
		if(alunoDAO.isAluno(numero)){
			Toast.makeText(context, "Chegou um SMS do numero " + numero, Toast.LENGTH_LONG).show();
		}
		helper.close();
	}
	
}
