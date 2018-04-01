package ru.lantimat.worksearch;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

/**
 * Created by fanis0990 on 08.09.17.
 */

public class About {
    public Dialog onCreateDialog(final Context context) {
        String versionName = BuildConfig.VERSION_NAME;
        String text = "Приложение для поиска работы! \n" +
                "Версия: " + versionName;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text)
                .setTitle(R.string.drawer_item_about)
                .setNegativeButton("Группа Вк", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club153198454"));
                        context.startActivity(browserIntent);
                        //TODO: ссылка на группу вк
                    }
                })
                .setPositiveButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}