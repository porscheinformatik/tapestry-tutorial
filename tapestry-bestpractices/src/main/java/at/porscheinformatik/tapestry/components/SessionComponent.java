package at.porscheinformatik.tapestry.components;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Loop;
import org.apache.tapestry5.services.Request;

/**
 * Renders the content of the current session
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class SessionComponent
{
    @Component(parameters = {"source=SessionAttributes", "value=currentAttribute"})
    private Loop<String> attributes;

    @Inject
    private Request request;

    @Property
    private String currentAttribute;

    @Cached
    public List<String> getSessionAttributes()
    {
        return request.getSession(false).getAttributeNames();
    }

    @Cached(watch = "currentAttribute")
    public Object getCurrentAttributeValue()
    {
        return request.getSession(false).getAttribute(currentAttribute).toString();
    }

    public void render()
    {
        for (String attribute : getSessionAttributes())
        {
            System.out.println(attribute + " = " + request.getSession(false).getAttribute(attribute).toString());
        }
    }

}
