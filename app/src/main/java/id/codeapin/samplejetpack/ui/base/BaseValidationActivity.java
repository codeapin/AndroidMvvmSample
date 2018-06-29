package id.codeapin.samplejetpack.ui.base;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.adapter.ViewDataAdapter;

import java.util.List;


public abstract class BaseValidationActivity extends BaseActivity implements Validator.ValidationListener {
    protected Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.registerAdapter(TextInputLayout.class, new TextInputLayoutAdapter());
        validator.setViewValidatedAction(view -> {
            if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError("");
                ((TextInputLayout) view).setErrorEnabled(false);

            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> list) {
        for (ValidationError error : list) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else if(view instanceof TextInputLayout) {
                ((TextInputLayout)view).setError(message);
                ((TextInputLayout) view).setErrorEnabled(true);
            }
            else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public class TextInputLayoutAdapter implements ViewDataAdapter<TextInputLayout, String> {

        @Override
        public String getData(final TextInputLayout til) {
            return getText(til);
        }

        private String getText(TextInputLayout til) {
            return til.getEditText().getText().toString();
        }
    }
}
