package id.codeapin.samplejetpack.injection.module;

import android.content.Context;


import dagger.Module;
import dagger.Provides;
import id.codeapin.samplejetpack.injection.annotation.ServiceContext;

@Module
public class ServiceModule {

    private final Context context;

    public ServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    @ServiceContext
    Context providesContext() {
        return context;
    }
}
