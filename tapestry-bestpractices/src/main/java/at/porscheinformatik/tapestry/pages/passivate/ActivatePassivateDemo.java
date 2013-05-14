package at.porscheinformatik.tapestry.pages.passivate;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.slf4j.Logger;

/**
 * Demonstartion for activate/passivate handling
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class ActivatePassivateDemo
{
    @Component
    private ActionLink reloadPage;

    @Inject
    private Logger logger;

    @Property
    private Long paramId;

    // activate and see what happens
    @ActivationRequestParameter
    private Long internalId;

    void onActivate()
    {
        logger.info("Calling onActivate()");
    }

    // change to void an see what happens
    boolean onActivate(Long paramId)
    {
        logger.info(String.format("Calling onActivate(%s)", paramId));
        this.paramId = paramId;
        //this.internalId = this.paramId;
        return true;
    }

    // remove this function and see what happens
    Long onPassivate()
    {
        logger.info(String.format("Calling onPassivate() with return value: %s", paramId));
        return paramId;
    }

    Object onActionFromReloadPage()
    {
        return this;
    }
}
