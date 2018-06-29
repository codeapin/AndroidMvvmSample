package id.codeapin.samplejetpack.ui.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Throwable> error = new MutableLiveData<>();
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    protected void setIsLoading(Boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public LiveData<Throwable> getError() {
        return error;
    }

    protected void setError(Throwable error) {
        this.error.setValue(error);
    }
}
