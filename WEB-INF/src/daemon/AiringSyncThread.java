/**
 * @file    AiringSyncThread.java
 * @author  Jacob Olson <jholson@andrew.cmu.edu>
 * @date    4/25/2012
 * @class   15-437
 */

package daemon;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.lang.Runtime;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class AiringSyncThread implements ServletContextListener {
    private Timer timer = null;
    private SyncThread thread;
    private File tempDir;
    private String contextPath;

    public void contextInitialized(ServletContextEvent sce) {
        if (timer == null) {
            timer = new Timer();
            thread = new SyncThread();
            tempDir = (File)sce.getServletContext().
                      getAttribute("javax.servlet.context.tempdir");
            contextPath = sce.getServletContext().getRealPath("/");

            /* Schedule the airing synchronizer to run at midnight each
               night. Note that this is not particularly robust to DST or
               things of that nature. */
            GregorianCalendar midnight = new GregorianCalendar();
            midnight.set(Calendar.MILLISECOND, 0);
            midnight.set(Calendar.SECOND, 0);
            midnight.set(Calendar.MINUTE, 0);
            midnight.set(Calendar.HOUR_OF_DAY, 0);
            midnight.roll(Calendar.DATE, true);
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

    public synchronized void syncAirings(String zipcode, String providerName) {
        try {
            /* Get the provider list for this zipcode into a file called
               mc2xml_out.txt */
            doCommand(contextPath + "WEB-INF/xmltv/get_providers.sh " +
                      zipcode,
                      tempDir);

            /* Run this script which parses mc2xml_out.txt for a particular
               provider name, and writes the number of that selection to a file
               called "input" */
            doCommand(contextPath +
                      "WEB-INF/xmltv/get_selection.sh '" + providerName + "'",
                      tempDir);

            /* Run this script which runs mc2xml with the given zipcode, taking
               the "input" file as input. This generates the xmltv.xml file. */
            doCommand(contextPath + "WEB-INF/xmltv/get_airings.sh " + zipcode,
                      tempDir);
        } catch (Exception e) {
        }
    }

    private void doCommand(String command, File workingDir) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command, null, workingDir);

        gobbleStream(process.getInputStream());
        gobbleStream(process.getErrorStream());

        process.waitFor();
    }

    private void gobbleStream(InputStream is) {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            while (br.readLine() != null) {}
        } catch (Exception e) {
        }
    }

    private class SyncThread extends TimerTask {
        public SyncThread() {
        }

        public void run() {
            syncAirings("15213", "USA, DIRECTV (SAT)");
        }
    }
}
