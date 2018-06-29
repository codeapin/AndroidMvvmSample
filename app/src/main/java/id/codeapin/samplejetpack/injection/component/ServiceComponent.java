package id.codeapin.samplejetpack.injection.component;


import android.content.Context;

import dagger.Component;
import id.codeapin.samplejetpack.injection.annotation.PerService;
import id.codeapin.samplejetpack.injection.annotation.ServiceContext;
import id.codeapin.samplejetpack.injection.module.ServiceModule;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    @ServiceContext
    Context getContext();

}
