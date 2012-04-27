/**
 * @file    CommandUtil.java
 * @author  Jacob Olson <jholson@andrew.cmu.edu>
 * @date    4/27/2012
 * @class   15-437
 */

package util;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.lang.Runtime;
import java.lang.InterruptedException;

public class CommandUtil {
    public static void doCommand(String[] args, File workingDir)
        throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(args, null, workingDir);

        try {
            gobbleStream(process.getInputStream());
            gobbleStream(process.getErrorStream());
        } catch (IOException e) {
        }

        process.waitFor();
    }

    private static void gobbleStream(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (br.readLine() != null) {}
    }
}
