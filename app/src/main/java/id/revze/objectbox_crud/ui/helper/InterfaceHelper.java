package id.revze.objectbox_crud.ui.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class InterfaceHelper {
    private static final InterfaceHelper INSTANCE = new InterfaceHelper();

    public static InterfaceHelper getInstance() {
        return INSTANCE;
    }

    public void showConfirmationDialog(Context context, String message, DialogInterface.OnClickListener positiveBtnClickListener) {
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
        alerBuilder.setMessage(message)
                    .setPositiveButton("Ok", positiveBtnClickListener)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        AlertDialog alertDialog = alerBuilder.create();
        alertDialog.show();
    }

    public void showAlertDialog(Context context, String message) {
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
        alerBuilder.setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alerBuilder.create();
        alertDialog.show();
    }
}
