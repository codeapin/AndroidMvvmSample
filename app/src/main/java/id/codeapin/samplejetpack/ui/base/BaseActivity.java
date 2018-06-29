package id.codeapin.samplejetpack.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import id.codeapin.samplejetpack.App;
import id.codeapin.samplejetpack.R;
import id.codeapin.samplejetpack.injection.component.ActivityComponent;
import id.codeapin.samplejetpack.injection.component.ConfigPersistentComponent;
import id.codeapin.samplejetpack.injection.component.DaggerConfigPersistentComponent;
import id.codeapin.samplejetpack.injection.module.ActivityModule;
import timber.log.Timber;


public class BaseActivity extends AppCompatActivity{
    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> componentsMap = new HashMap<>();

    private ActivityComponent activityComponent;
    private long activityId;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                activityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent;
        if (!componentsMap.containsKey(activityId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d ", activityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(App.get(this).getComponent())
                    .build();
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d ", activityId);
            configPersistentComponent = componentsMap.get(activityId);
        }
        activityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    }

    public ActivityComponent activityComponent() {
        return activityComponent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId);
            componentsMap.remove(activityId);
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // ViewContract Implementation --------------------------------------------------------------------------------

    public void showLoading(boolean isShow) {
    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(@StringRes int messageRes) {
        showToast(getResources().getString(messageRes));
    }

    public void showError(Throwable error) {

        Timber.e(error);
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

        showToast(R.string.error_message_default);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

