package id.codeapin.samplejetpack.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codeapin.samplejetpack.R;
import id.codeapin.samplejetpack.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.progress_loading)
    ProgressBar progressLoading;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Inject
    ViewModelProvider.Factory vFactory;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        activityComponent().inject(this);

        viewModel = ViewModelProviders.of(this, vFactory).get(MainViewModel.class);
        btnSubmit.setOnClickListener(v -> viewModel.doSomeWork());

        viewModel.getError().observe(this, this::showError);
        viewModel.getIsLoading().observe(this, this::showLoading);
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow) {
            progressLoading.setVisibility(View.VISIBLE);
        } else {
            progressLoading.setVisibility(View.GONE);
        }
    }
}
