/**
 * @file    DatabaseSync.java
 * @author  Jacob Olson <jholson@andrew.cmu.edu>
 * @date    4/25/2012
 * @class   15-437
 */

package util;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.lang.Integer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.genericdao.Transaction;

import databean.Provider;
import model.Model;
import model.ProviderDAO;
import scripts.ParseXMLTV;
import util.CommandUtil;

public class DatabaseSync {
    public static synchronized void generateProviders(Model model,
                                         File tempDir,
                                         String contextPath,
                                         String zipcode) {
        try {
            /* Generate the file with all of the providers in it
            (called mc2xml_out.txt). */
            CommandUtil.doCommand(new String[] {
                                contextPath + "WEB-INF/xmltv/get_providers.sh",
                                contextPath + "WEB-INF/xmltv/mc2xml",
                                zipcode},
                                tempDir);

            File inFile = new File(tempDir, "mc2xml_out.txt");
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            String line;
            List<Provider> providerList = new ArrayList<Provider>();

            Transaction.begin();

            ProviderDAO providerDAO = model.getProviderDAO();

            while ((line = br.readLine()) != null) {
                if (!line.matches("^[ ]*[0-9]+:.*$")) {
                    continue;
                }

                String providerName =
                    line.replaceAll("^[ ]*[0-9]+: ", "");
                
                if (providerDAO.readByNameAndZipcode(providerName,
                        zipcode) == null) {
                    Provider provider = new Provider();
                    provider.setName(providerName);
                    provider.setZipcode(zipcode);
                    Calendar now = new GregorianCalendar();
                    now.setTime(new Date());
                    now.add(Calendar.YEAR, -100);
                    provider.setLastSync(now.getTime());

                    providerList.add(provider);
                }

            }

            for (Provider p : providerList) {
                providerDAO.create(p);
            }

            Transaction.commit();

            CommandUtil.doCommand(new String[] {
                                "rm",
                                "mc2xml_out.txt"},
                                tempDir);
        } catch (Exception e) {
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }

    }

    public static synchronized void syncAirings(Model model,
                                                File tempDir,
                                                String contextPath,
                                                String zipcode,
                                                String providerName,
                                                int hoursFromNow,
                                                int hoursDuration) {
        Provider provider = null;
        ProviderDAO providerDAO;

        try {
            providerDAO = model.getProviderDAO();
            provider =
                (Provider)providerDAO.readByNameAndZipcode(providerName, zipcode);

            /* Get the provider list for this zipcode into a file called
               mc2xml_out.txt */
            CommandUtil.doCommand(new String[] {
                      contextPath + "WEB-INF/xmltv/get_providers.sh",
                      contextPath + "WEB-INF/xmltv/mc2xml",
                      zipcode},
                      tempDir);

            /* Run this script which parses mc2xml_out.txt for a particular
               provider name, and writes the number of that selection to a file
               called "input" */
            CommandUtil.doCommand(new String[] {
                      contextPath + "WEB-INF/xmltv/get_selection.sh",
                      providerName},
                      tempDir);

            /* Run this script which runs mc2xml with the given zipcode, taking
               the "input" file as input. This generates the xmltv.xml file. */
            CommandUtil.doCommand(new String[] {
                      contextPath + "WEB-INF/xmltv/get_airings.sh",
                      contextPath + "WEB-INF/xmltv/mc2xml",
                      zipcode,
                      Integer.toString(hoursFromNow),
                      Integer.toString(hoursDuration)},
                      tempDir);

            /* Parse the xml file into the database. */
            ParseXMLTV.parse(tempDir, model, provider.getId());

            /* Update the provider with a new "time last parsed" value. */
            provider.setLastSync(new Date());
            providerDAO.update(provider);

            /* Clean up */
            CommandUtil.doCommand(
                    new String[] {
                    "rm",
                    "mc2xml_out.txt",
                    "input",
                    "mc2xml.dat",
                    "xmltv.xml"},
                    tempDir);
        } catch (Exception e) {
        }
    }
}
