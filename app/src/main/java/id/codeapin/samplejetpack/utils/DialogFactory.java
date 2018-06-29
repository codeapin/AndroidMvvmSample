package id.codeapin.samplejetpack.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;


import java.util.Calendar;
import java.util.Date;

import id.codeapin.samplejetpack.R;


public final class DialogFactory {

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_action_ok, listener);
        return alertDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource,
                                                   DialogInterface.OnClickListener listener) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource),
                listener);
    }

    public static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setPositiveButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context, String message, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_action_ok, clickListener);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

    public static Dialog createListDialog(Context context, @ArrayRes int arrayResource, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_list_title)
                .setNegativeButton(context.getResources().getString(R.string.dialog_button_cancel),
                        (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .setItems(arrayResource, clickListener);

        return builder.create();
    }

    public static Dialog createYesNoDialog(Context context, String title, String message, DialogInterface.OnClickListener yesClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.dialog_button_cancel, null)
                .setPositiveButton(R.string.dialog_action_ok, yesClickListener);
        return alertDialog.create();
    }

    public static DatePickerDialog createDatePickerDialog(Context context, Date defaultDate, DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(defaultDate);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(context, listener, y, m, d);
    }
}
