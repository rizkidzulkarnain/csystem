package co.ejjv.ccms_mobile.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

public class AlertDialogCustom {
    AlertDialog _alertDialog;

    public AlertDialogCustom(final Context icontext) { //tipe 1 save
        _alertDialog = new AlertDialog.Builder(icontext).create();
        _alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }
        );
    }

    /*UNTUK MAIN VIEW*/
    public AlertDialogCustom(final Context icontext, final _MainContract.MainView ilistener, final String itag) { //tipe 1 save
        _alertDialog = new AlertDialog.Builder(icontext).create();
        _alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        _alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DONE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ilistener.onPostAlertDialogAction(itag);
                dialog.dismiss();
            }
        });
    }

    /*UNTUK MODAL WITH LAYOUT PARAM*/
    public AlertDialogCustom(final Context icontext, View iview, final _MainContract.MainView ilistener, final String itag) { //tipe 1 save
        _alertDialog = new AlertDialog.Builder(icontext).create();
        _alertDialog.setView(iview);
        _alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        _alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "CONTINUE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ilistener.onPostAlertDialogAction(itag);
                dialog.dismiss();
            }
        });
    }

    public void showAlertDialog(String imsg, int itipe) {
        SpannableStringBuilder aStringBuilder = changeColorAlert(itipe);

        _alertDialog.setTitle(aStringBuilder);
        _alertDialog.setMessage(imsg);
        _alertDialog.setCanceledOnTouchOutside(false);
        _alertDialog.show();
    }

    public void hideAlertDialog() {
        _alertDialog.hide();
    }

    public static SpannableStringBuilder changeColorAlert(int icolor) {
        int acolor;
        String atitle;
        //1 red, 2 green, 3 blue
        switch (icolor) {
            case 1:
                atitle = "Error";
                acolor = Color.rgb(241, 3, 4);
                break;
            case 2:
                atitle = "Success";
                acolor = Color.rgb(27, 160, 225);
                break;
            default:
                atitle = "Info";
                acolor = Color.BLACK;
                break;
        }

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(acolor);
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(atitle);
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                atitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return ssBuilder;
    }
}
