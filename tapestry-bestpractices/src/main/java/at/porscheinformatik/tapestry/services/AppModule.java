package at.porscheinformatik.tapestry.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ObjectLocator;
import org.apache.tapestry5.services.ComponentEventResultProcessor;

import at.porscheinformatik.tapestry.dto.ResultContext;

public class AppModule
{

    private final ObjectLocator locator;

    /**
     * @param locator .
     */
    public AppModule(final ObjectLocator locator)
    {
        this.locator = locator;
    }

    /**
     * Contribute an component event result processor
     * 
     * @param configuration .
     */
    public void contributeComponentEventResultProcessor(
        final MappedConfiguration<Class<?>, ComponentEventResultProcessor<?>> configuration)
    {
        configuration.add(ResultContext.class, locator.autobuild(ResultContextProcessor.class));
    }
}
