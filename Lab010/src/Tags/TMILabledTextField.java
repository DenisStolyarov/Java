package Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TMILabledTextField extends SimpleTagSupport {

    String label;

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void doTag() throws JspException {

        try {
            getJspContext().getOut().print("dsad");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }


    }
}