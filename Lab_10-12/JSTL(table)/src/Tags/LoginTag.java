package Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {
    String value;
    public void setValue(String value){
        this.value = value;
    }
    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        out.print("<input type=\"submit\" value=\""+value+"\">");
    }
}
