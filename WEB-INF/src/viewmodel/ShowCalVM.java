/**
 * @file   ShowCalVM.java
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/27/2012
 * @class  15-437
 */

package viewmodel;

import databean.Show;
import databean.Airing;

public class ShowCalVM
{
    private int showId;
    private String showName;
    private Airing[] mondayAirings;
    private Airing[] tuesdayAirings;
    private Airing[] wednesdayAirings;
    private Airing[] thursdayAirings;
    private Airing[] fridayAirings;
    private Airing[] saturdayAirings;
    private Airing[] sundayAirings;

    public int getShowId()                 { return showId; }
    public String getShowName()            { return showName; }
    public Airing[] getMondayAirings()     { return mondayAirings; }
    public Airing[] getTuesdayAirings()    { return tuesdayAirings; }
    public Airing[] getWednesdayAirings()  { return wednesdayAirings; }
    public Airing[] getThursdayAirings()   { return thursdayAirings; }
    public Airing[] getFridayAirings()     { return fridayAirings; }
    public Airing[] getSaturdayAirings()   { return saturdayAirings; }
    public Airing[] getSundayAirings()     { return sundayAirings; }

    public ShowCalVM(Show show, Airing[] mondayAirings, Airing[] tuesdayAirings,
                     Airing[] wednesdayAirings, Airing[] thursdayAirings, 
                     Airing[] fridayAirings, Airing[] saturdayAirings, 
                     Airing[] sundayAirings)
    {
        this.showId = show.getId();
        this.showName = show.getShowName();
        this.mondayAirings = mondayAirings;
        this.tuesdayAirings = tuesdayAirings;
        this.wednesdayAirings = wednesdayAirings;
        this.thursdayAirings = thursdayAirings;
        this.fridayAirings = fridayAirings;
        this.saturdayAirings = saturdayAirings;
        this.sundayAirings = sundayAirings;
    }
}
