package id.codeapin.samplejetpack.injection.component;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.google.gson.Gson;

import dagger.Component;
import id.codeapin.samplejetpack.data.db.AppDb;
import id.codeapin.samplejetpack.injection.annotation.ApplicationContext;
import id.codeapin.samplejetpack.injection.annotation.PerApplication;
import id.codeapin.samplejetpack.injection.module.ApplicationModule;
import id.codeapin.samplejetpack.injection.module.NetworkModule;
import id.codeapin.samplejetpack.injection.module.ViewModelModule;
import id.codeapin.samplejetpack.utils.DateTimeUtil;
import id.codeapin.samplejetpack.utils.RxBus;

@PerApplication
@Component(modules = {ApplicationModule.class, ViewModelModule.class, NetworkModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    DateTimeUtil getDateTimeUtil();

    RxBus getRxBus();

    AppDb getAppDb();

    Gson getGson();

    ViewModelProvider.Factory getFactory();

}
