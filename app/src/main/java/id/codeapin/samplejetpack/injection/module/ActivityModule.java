package id.codeapin.samplejetpack.injection.module;


import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import id.codeapin.samplejetpack.injection.annotation.ActivityContext;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }
}
