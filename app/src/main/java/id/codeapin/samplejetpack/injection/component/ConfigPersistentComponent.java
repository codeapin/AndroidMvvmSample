package id.codeapin.samplejetpack.injection.component;


import dagger.Component;
import id.codeapin.samplejetpack.injection.annotation.ConfigPersistent;
import id.codeapin.samplejetpack.injection.module.ActivityModule;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);
}
