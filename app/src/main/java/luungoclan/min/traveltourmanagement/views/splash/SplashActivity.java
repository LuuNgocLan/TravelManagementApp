package luungoclan.min.traveltourmanagement.views.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.presenters.login.LoginPresenter;
import luungoclan.min.traveltourmanagement.views.login.ILoginActivity;
import luungoclan.min.traveltourmanagement.views.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private boolean isLoggingIn;
    private String emailSaved, passwordSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CheckThread checkThread = new CheckThread();
        checkThread.start();
    }

    public class CheckThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                // NOTE: Sleep 2s to take slow down checkLogin for show Splash
                sleep(1000);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * check user have logged out yet
     *
     * @return
     */
    private boolean isLoginAlready() {
        if (isLoggingIn) {
            return true;
        }
        return false;
    }
}
