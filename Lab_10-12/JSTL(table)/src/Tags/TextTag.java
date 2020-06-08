package Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class TextTag extends SimpleTagSupport {

    String name;
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        out.print(name);
    }
}
