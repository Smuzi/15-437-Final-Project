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
    private String provider;

    public String getProvider() { return provider; }

    public void setProvider(String s) { provider = s.trim(); }
}
