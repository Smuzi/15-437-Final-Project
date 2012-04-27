package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import scripts.ParseXMLTV;

public class ParseTestAction extends Action
{
    private Model model;

    public ParseTestAction(Model model)
    {
        this.model = model;
    } 

    public String getName() { return "parsetest.do"; }

    public String perform(HttpServletRequest request)
    {
        ParseXMLTV.parse(null, model);

        return "home.jsp";
    }
}
