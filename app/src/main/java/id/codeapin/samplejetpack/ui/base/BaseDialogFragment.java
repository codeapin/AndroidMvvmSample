package id.codeapin.samplejetpack.ui.base;


import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import id.codeapin.samplejetpack.R;
import id.codeapin.samplejetpack.injection.component.ActivityComponent;

public class BaseDialogFragment extends DialogFragment{
    private Toast toast;

    protected ActivityComponent activityComponent() {
        return ((BaseActivity) getActivity()).activityComponent();
    }

    ///////////////////////////////////////////////////////////////////////////
    // ViewContractImplementation
    ///////////////////////////////////////////////////////////////////////////

    public void showLoading(boolean isShow) {

    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(@StringRes int messageRes) {
        showToast(getResources().getString(messageRes));
    }

    public void showError(Throwable error) {
        if (error instanceof ConnectException) {
            showToast(R.string.error_message_connection_failed);
            return;
        }
        if (error instanceof SocketTimeoutException) {
            showToast(R.string.error_message_time_out);
            return;
        }
        if (error instanceof IOException) {
            showToast(R.string.error_message_io);
            return;
        }

        showToast(error.getMessage());
    }
}
