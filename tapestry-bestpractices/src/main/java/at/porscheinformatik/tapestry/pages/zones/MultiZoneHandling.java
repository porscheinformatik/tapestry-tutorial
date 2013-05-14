package at.porscheinformatik.tapestry.pages.zones;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import at.porscheinformatik.tapestry.components.zone.Buttons;

/**
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class MultiZoneHandling
{
    @Component
    private ActionLink decrease;

    @Component
    private ActionLink increase;
    
    @Component(parameters="counter=counter")
    private Buttons buttons;

    // ALWAYS provide an id
    @Component(parameters = "id=counterZone")
    private Zone counterZone;
    
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @ActivationRequestParameter
    @Property
    private Long counter;

    void beginRender()
    {
        if (counter == null)
        {
            counter = 0L;
        }
    }

    void onActionFromIncrease()
    {
        counter++;
    }

    void onActionFromDecrease()
    {
        counter--;
    }
    
    // listen on every event triggered from the component buttons
    public void onActionFromButtons()
    {
        // refresh the counter
        ajaxResponseRenderer.addRender(counterZone);
    }
}
