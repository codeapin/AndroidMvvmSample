package id.codeapin.samplejetpack;

import android.app.Application;
import android.content.Context;

import id.codeapin.samplejetpack.injection.component.ApplicationComponent;
import id.codeapin.samplejetpack.injection.component.DaggerApplicationComponent;
import id.codeapin.samplejetpack.injection.module.ApplicationModule;
import timber.log.Timber;

public class App extends Application {

    private ApplicationComponent applicationComponent;
    private static App instanceApp;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        instanceApp = this;

        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(App.get(this)))
                    .build();
        }
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

    public static synchronized App getInstance(){
        return instanceApp;
    }
}
