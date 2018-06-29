package id.codeapin.samplejetpack.injection.module;


import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import id.codeapin.samplejetpack.data.db.AppDb;
import id.codeapin.samplejetpack.injection.annotation.ApplicationContext;
import id.codeapin.samplejetpack.injection.annotation.PerApplication;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    Application provideApplication() {
        return application;
    }

    @Provides
    @PerApplication
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @PerApplication
    AppDb provideAppDb(){
        return Room.databaseBuilder(application.getApplicationContext(), AppDb.class, "HealthBooking")
                .fallbackToDestructiveMigration()
                .build();
    }
}
