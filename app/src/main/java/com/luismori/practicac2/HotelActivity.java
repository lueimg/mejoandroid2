package com.luismori.practicac2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;


import com.luismori.practicac2.R;

public class HotelActivity extends Activity implements SendDataDialogFragment.DialogListener {


    private boolean favorite = false;

    public void toggleClicked(View v){
        Log.e("TAG","toggle");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        //cuando se haga touch en un icon del menu
        switch (item.getItemId()){
            case R.id.action_fav:

                Drawable icon = null;
                if(favorite){
                    // obtener los elementos como recursos
                    icon = getResources().getDrawable(R.drawable.rating_not_important);
                }else{
                    icon = getResources().getDrawable(R.drawable.rating_important);
                }

                favorite = !favorite;
                item.setIcon(icon); // se actualiza la nuevo icono

                return true;
            case R.id.action_share:

                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);

                String msg = getResources().getString(R.string.msg_share);
                share.putExtra(Intent.EXTRA_TEXT,msg);

                Uri img_res = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + R.drawable.hotel1);
                share.putExtra(Intent.EXTRA_STREAM, img_res);

                share.setType("image/jpeg");

                startActivity(Intent.createChooser(share,"Compartir"));

               return true;
            case R.id.action_dialog:
                SendDataDialogFragment f = new SendDataDialogFragment();
                f.show(getFragmentManager(),"Dialogo");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e("TAG","dijo que si");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}


class SendDataDialogFragment extends DialogFragment{

    public interface DialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    //uso la interface declarada
    DialogListener listener;

    //on attach cuando el fragmenteo se adjuanta
    // on created dialog cuando se



    //cuando el fragmento se adjunta a la interface
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            //que el objeto listener sea la actividad
            listener = (DialogListener)activity;
        }
        catch (ClassCastException e){

        }
    }

    //CUANDO EL DIALOGO ES CREADO
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Titulo")
               .setSingleChoiceItems(R.array.dialog_otions,-1,null)
               .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       listener.onDialogPositiveClick(SendDataDialogFragment.this);
                   }
               });

        return builder.create();

    }
}