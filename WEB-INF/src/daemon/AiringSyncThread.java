/**
 * @file    AiringSyncThread.java
 * @author  Jacob Olson <jholson@andrew.cmu.edu>
 * @date    4/25/2012
 * @class   15-437
 */

package daemon;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class AiringSyncThread implements ServletContextListener {
    private Timer timer = null;
    private SyncThread thread = null;

    public void contextInitialized(ServletContextEvent sce) {
        if (timer == null) {
            timer = new Timer();
            thread = new SyncThread();

            /* Schedule the airing synchronizer to run at midnight each
               night. Note that this is not particularly robust to DST or
               things of that nature. */
            GregorianCalendar midnight = new GregorianCalendar();
            midnight.set(Calendar.MILLISECOND, 0);
            midnight.set(Calendar.SECOND, 0);
            midnight.set(Calendar.MINUTE, 0);
            midnight.set(Calendar.HOUR_OF_DAY, 0);
            midnight.roll(Calendar.DATE, true);
            timer.schedule(thread, midnight.getTime(), 1000*60*60*24);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        /* Kill the timer thread. */
        try {
            timer.cancel();
            timer = null;
            thread = null;
        } catch (Exception e) {
        }
    }

    private class SyncThread extends TimerTask {
        public SyncThread() {
        }

        public void run() {
        }
    }
}
