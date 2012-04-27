/**
 * @file   ImageUploadAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/26/2012
 * @class  15-437
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.Transaction;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.User;
import databean.Image;
import databean.Show;
import model.Model;
import model.ImageDAO;
import model.ShowDAO;
import formbean.ImageUploadForm;

public class ImageUploadAction extends Action {
    private FormBeanFactory<ImageUploadForm> formBeanFactory =
            FormBeanFactory.getInstance(ImageUploadForm.class);

    private ImageDAO imageDAO;
    private ShowDAO showDAO;

    public ImageUploadAction(Model model) {
        showDAO = model.getShowDAO();
        imageDAO = model.getImageDAO();
    }

    public String getName() { return "imageupload.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        /* If the user is not logged in, redirect to login screen. */
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "login.do";
        }

        /* TODO check for admin. */

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            Transaction.begin();

            ImageUploadForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            /* Retrieve the relevant show from the database. */
            Show show = showDAO.read(form.getShowIdAsInt());
            if (show == null) {
                errors.add("Show " + form.getShowId() +
                           " not found in database.");
                return "error.jsp";
            }
            request.setAttribute("show", show);

            /* If no file was passed, assume that this is the first time
               the form's being shown. */
            if (form.getAction().equals("toForm")) {
                return "imageupload.jsp";
            }

            /* See if there are any validation errors in the form. */
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "imageupload.jsp";
            }

            /* Upload the image into the database, overwriting the previous
               one if applicable. */
            Image image = new Image();
            image.setBytes(form.getImage().getBytes());
            image.setContentType(form.getImage().getContentType());
            if (show.getImageId() != 0) {
                image.setId(show.getImageId());
                imageDAO.update(image);
            } else {
                imageDAO.create(image);
                show.setImageId(image.getId());
                showDAO.update(show);
            }

            Transaction.commit();

            return "imageupload.jsp";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }
}
