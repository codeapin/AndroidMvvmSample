package id.codeapin.samplejetpack.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import id.codeapin.samplejetpack.data.db.converter.DateTypeConverter;
import id.codeapin.samplejetpack.data.db.dao.SampleDao;
import id.codeapin.samplejetpack.data.db.model.SampleEntity;

@Database(entities = {SampleEntity.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDb extends RoomDatabase {

    public abstract SampleDao reservasiDao();

}