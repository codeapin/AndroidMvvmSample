package id.codeapin.samplejetpack.injection.component;


import android.content.Context;

import dagger.Subcomponent;
import id.codeapin.samplejetpack.injection.annotation.ActivityContext;
import id.codeapin.samplejetpack.injection.annotation.PerActivity;
import id.codeapin.samplejetpack.injection.module.ActivityModule;
import id.codeapin.samplejetpack.ui.main.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    @ActivityContext
    Context getContext(); // required if we wan't to inject context anywhere

    void inject(MainActivity mainActivity);
}

