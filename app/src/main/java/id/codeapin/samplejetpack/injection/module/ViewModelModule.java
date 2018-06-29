package id.codeapin.samplejetpack.injection.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import id.codeapin.samplejetpack.injection.annotation.ViewModelKey;
import id.codeapin.samplejetpack.ui.main.MainViewModel;
import id.codeapin.samplejetpack.utils.CustomViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    // TODO: 6/29/2018 Every View Models must be registered here

    @Binds
    abstract ViewModelProvider.Factory bindFactory(CustomViewModelFactory factory);
}
