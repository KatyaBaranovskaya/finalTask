package by.baranovskaya.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MessageTag extends TagSupport {
    private String messageError;

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer stringBuf = new StringBuffer();
            if (messageError != null && !messageError.isEmpty()) {
                stringBuf.append("<div class=\"alert-danger alert tag\">");
                stringBuf.append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\">×</a>");
                stringBuf.append(this.messageError);
                stringBuf.append("</div>");
            }
            pageContext.getOut().print(stringBuf);
        } catch (IOException e) {
            throw new JspException("Error message tag: " + e.getMessage());
        }

        return SKIP_BODY;
    }
}
