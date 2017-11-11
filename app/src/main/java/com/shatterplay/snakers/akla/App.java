package com.shatterplay.snakers.akla;

import android.app.Application;
import android.content.Context;

/**
 * Created by snakers on 10/20/2017.
 */

public class App extends Application {
    private Users users;
   public int globalVariabl=0;

    public void setUsers(Users users) {
        this.users = users;
    }

    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
    }
    public static App getInstance(){
        if(app ==null){
            app=new App();
        }
    return app;
    }

}
