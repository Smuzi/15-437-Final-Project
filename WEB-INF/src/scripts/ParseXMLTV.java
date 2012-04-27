/**
 * @file    ParseXMLTV.java
 * @author  Robert Liu <rql@andrew.cmu.edu>
 * @date    4/25/2012
 * @class   15-437
 */

package scripts;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

import java.util.HashMap;
import java.util.Date;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import org.genericdao.RollbackException;

import model.Model;
import model.AiringDAO;
import model.ShowDAO;
import databean.Airing;
import databean.Show;

public class ParseXMLTV
{
    public static void parse(File tempDir, Model model)
    {
        ShowDAO showDAO = model.getShowDAO();
        AiringDAO airingDAO = model.getAiringDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss Z");
        ParsePosition pp = new ParsePosition(0);

        // Maps channel numbers to the channel string
        // to generate Airing Databeans
        HashMap<Integer, String> channelNumToName = 
            new HashMap<Integer, String>();
        // Map from xmltv channel id (e.g. "I4.2861372.microsoft.com") to number
        // which we can then use to get the channel name
        HashMap<String, Integer> channelIdToNum = 
            new HashMap<String, Integer>();

        // This is the File we're parsing
        //File xmlFile = new File(tempDir, "xmltv.xml");
        File xmlFile = new File("/home/robert/shared/4Year/15437/project/repo/WEB-INF/xmltv/xmltv.xml");

        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc;

        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            System.out.println("foo");
            doc = dBuilder.parse(xmlFile);
            System.out.println("foo1");
            // Not really sure why this is important, but you can read docs
            // online
            doc.getDocumentElement().normalize();
        } catch(Exception e)
        {
            // TODO: Better error handling...
            e.printStackTrace();
            // Exit because we can't parse the xml without a document builder
            return;
        }

        /* Generate the channels list and channel map */
        NodeList channelNodeList = doc.getElementsByTagName("channel");

        for (int i = 0; i < channelNodeList.getLength(); i++)
        {
            // There are a lot of node types in DOM. I think ELEMENT is the
            // correct node type for <channel>s. Allows us to cast to Element
            // without errors
            if (channelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element currChanElem = (Element) channelNodeList.item(i);

                String currChanId = currChanElem.getAttribute("id");

                NodeList displayNameNodeList = 
                    currChanElem.getElementsByTagName("display-name");

                // I assume that the second item is the # and the third
                // item is the channel name
                Integer currChanNumber = Integer.parseInt(
                        displayNameNodeList.item(1).getTextContent());
                String currChanName = 
                    displayNameNodeList.item(2).getTextContent();

                channelNumToName.put(currChanNumber, currChanName);
                channelIdToNum.put(currChanId, currChanNumber);
            }
        }

        System.out.println("Done generating channels");

        /* Add airings. They're called <programme>s */
        NodeList airingNodeList = doc.getElementsByTagName("programme");

        for (int i = 0; i < airingNodeList.getLength(); i++)
        {
            /* Create the airing data bean */
            Airing newAiring = new Airing();

            if (airingNodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element currProgElem = (Element) airingNodeList.item(i);

                // Grab start and stop Date
                String startDateAsString = currProgElem.getAttribute("start");
                String stopDateAsString = currProgElem.getAttribute("stop");
                try
                {
                    pp.setIndex(0);
                    newAiring.setStartTime(df.parse(startDateAsString, pp));
                    pp.setIndex(0);
                    newAiring.setStopTime(df.parse(stopDateAsString, pp));
                } catch(NullPointerException e)
                {
                    // TODO: Better error handling...
                    e.printStackTrace();
                    // If we can't parse a date then we don't need this airing
                    continue;
                }

                // Set channel number and name
                String channelId = currProgElem.getAttribute("channel");
                Integer channelNum = channelIdToNum.get(channelId);
                String channelName = channelNumToName.get(channelNum);
                newAiring.setChannelNumber(channelNum);
                newAiring.setChannelName(channelName);

                // Get episode code
                // TODO: For now I just try the first <episode-num>
                // tag and check if it has system attribute "onscreen". We can
                // do something nicer later
                NodeList episodeNodeList = 
                    currProgElem.getElementsByTagName("episode-num");

                if (episodeNodeList.getLength() > 0)
                {
                    if (episodeNodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element episodeElem = (Element) episodeNodeList.item(0);

                        if (episodeElem.getAttribute("system").equals("onscreen"))
                        {
                            newAiring.setEpisodeCode(episodeElem.getTextContent());
                        }
                    }
                }

                // Get showId from our database
                // First get the show name
                NodeList titleNodeList =
                    currProgElem.getElementsByTagName("title");

                // The dtd for xmltv requires <title> to occur at least once.
                // Just grab the first one
                if (episodeNodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
                {
                    Element titleNode = (Element) titleNodeList.item(0);

                    String showName = titleNode.getTextContent();

                    NodeList descNodeList = currProgElem.getElementsByTagName("desc");

                    String showDescription = null;
                    if (descNodeList.getLength() > 0)
                    {
                        if (descNodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
                        {
                            showDescription = 
                                (((Element) descNodeList.item(0)).getTextContent());
                        }
                    }

                    // Set providerId to whatever was passed in
                    // TODO: change to param
                    newAiring.setProviderId(0);

                    // Try and get the showId or generate a new show if
                    // necessary. If any of this fails then we just drop this
                    // airing and move on to the next one. Hence, we wrap this
                    // all in one try block for easy clean up
                    Show matchedShow;
                    try
                    {
                        matchedShow = showDAO.readByShowName(showName);

                        if (matchedShow != null)
                        {
                            newAiring.setShowId(matchedShow.getId());
                        }
                        else // Make a new show
                        {
                            Show newShow = new Show();
                            newShow.setShowName(showName);
                            newShow.setDescription(showDescription);

                            // Add to database
                            showDAO.createAutoIncrement(newShow);

                            // createAutoIncrement() modifies the bean
                            newAiring.setShowId(newShow.getId());
                        }

                        airingDAO.createAutoIncrement(newAiring);
                    } catch (RollbackException e)
                    {
                        // TODO: Better error handling...
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
