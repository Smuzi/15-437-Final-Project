/**
 * @file   ImageAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import controller.Action;
import databean.Image;
import formbean.IdForm;
import model.Model;
import model.ImageDAO;

public class ImageAction extends Action {
    private FormBeanFactory<IdForm> formBeanFactory =
            FormBeanFactory.getInstance(IdForm.class);

    private ImageDAO imageDAO;

    public ImageAction(Model model) {
        imageDAO = model.getImageDAO();
    }

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            IdForm form = formBeanFactory.create(request);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }

            if (form.getIdAsInt() == 0) {
                return "default_image.png";
            }

            Image image = imageDAO.read(form.getIdAsInt());
            request.setAttribute("image", image);
            
            return "image";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
