/**
 * @file   UpdateProviderForm.java
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/27/2012
 * @class  15-437
 */

package formbean;

import org.mybeans.form.FormBean;

public class UpdateProviderForm extends FormBean
{
    private String providerIdAsString;

    public String getProviderIdAsString() { return providerIdAsString; }

    public void setProviderIdAsString(String s) 
                                    { providerIdAsString = s.trim(); }
}
