package id.codeapin.samplejetpack.ui.main;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import id.codeapin.samplejetpack.data.db.AppDbHelper;
import id.codeapin.samplejetpack.ui.base.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private final AppDbHelper dbHelper;

    @Inject
    public MainViewModel(AppDbHelper dbHelper) {
        this.dbHelper= dbHelper;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void doSomeWork() {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    setIsLoading(true);
                })
                .doFinally(() -> setIsLoading(false))
                .subscribe(
                        aLong -> {
                        },
                        throwable -> setError(throwable),
                        () -> {
                        });
    }
}
