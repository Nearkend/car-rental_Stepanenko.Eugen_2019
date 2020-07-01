package ua.nure.stepanenko.thesis.web.tags;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.constant.Role;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PermitTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(PermitTag.class);

    private Role role;

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    @Override
    public int doStartTag()
            throws JspException {

        LOG.debug("Tag starts");

        HttpSession session = pageContext.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null || !(user.getRole().ordinal() >= role.ordinal()) || user.isBlocked()) {

            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
            session.setAttribute("error", new AppException("Access denied"));

            try {
                response.sendRedirect(Paths.ERROR_SERVLET);
            } catch (IOException e) {
                LOG.error("Failed when redirect to error page", e);
            }
        }

        LOG.debug("Tag finished");
        return super.doStartTag();
    }
}
