package tw.chikuo.lockersmap;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Chi on 2016/10/23.
 */
public class Application extends android.app.Application {

    public static int DEVICE_DENSITY_DPI ;

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        DEVICE_DENSITY_DPI = metrics.densityDpi;
    }
}
