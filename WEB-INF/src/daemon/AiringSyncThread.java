/**
 * @file    AiringSyncThread.java
 * @author  Jacob Olson <jholson@andrew.cmu.edu>
 * @date    4/25/2012
 * @class   15-437
 */

package daemon;

import java.io.File;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import model.Model;
import util.CommandUtil;
import util.DatabaseSync;

public class AiringSyncThread implements ServletContextListener {
    private Timer timer = null;
    private SyncThread thread;
    private File tempDir;
    private String contextPath;
    private Model model;
    private ServletContext context;

    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
        tempDir = (File)context.
                    getAttribute("javax.servlet.context.tempdir");
        contextPath = context.getRealPath("/");

        try {
            CommandUtil.doCommand(new String[] {
                      "/bin/cp",
                      contextPath + "WEB-INF/xmltv/xmltv.dtd",
                      "./"},
                      tempDir);
        } catch (Exception e) {
        }

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
            midnight.add(Calendar.DATE, 1);
            /*
            timer.scheduleAtFixedRate(thread,
                                      midnight.getTime(),
                                      1000*60*60*24);
                                      */
            GregorianCalendar now =
                new GregorianCalendar();
            now.add(Calendar.SECOND, 1);
            timer.schedule(thread, now.getTime());
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
            Model model;

            do {
                model = (Model)context.getAttribute("model");
            } while (model == null);

            DatabaseSync.generateProviders(model, tempDir, contextPath, "15213");
        }
    }
}
