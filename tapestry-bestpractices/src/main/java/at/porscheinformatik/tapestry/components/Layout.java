package at.porscheinformatik.tapestry.components;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.corelib.components.PageLink;

@Import(stylesheet = "context:bootstrap/css/bootstrap.css")
public class Layout
{
    @Component(parameters = "page=Start")
    private PageLink home;
}
