package id.codeapin.samplejetpack.data.db;


import javax.inject.Inject;

import id.codeapin.samplejetpack.injection.annotation.PerApplication;

@PerApplication
public class AppDbHelper {

    private final AppDb db;

    @Inject
    public AppDbHelper(AppDb db) {
        this.db = db;
    }
}
