package id.codeapin.samplejetpack.utils;


import javax.inject.Inject;

import id.codeapin.samplejetpack.injection.annotation.PerApplication;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@PerApplication
public class RxBus {

    private PublishSubject<Object> bus = PublishSubject.create();

    @Inject
    public RxBus(){

    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
