package id.revze.objectbox_crud;

import android.app.Application;

import id.revze.objectbox_crud.model.MyObjectBox;
import io.objectbox.BoxStore;

public class CoreApplication  extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public void setBoxStore(BoxStore boxStore) {
        this.boxStore = boxStore;
    }
}
