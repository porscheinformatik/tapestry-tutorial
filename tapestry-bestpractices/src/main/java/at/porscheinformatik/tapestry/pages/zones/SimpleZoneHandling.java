package at.porscheinformatik.tapestry.pages.zones;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Zone;

/**
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class SimpleZoneHandling
{
    @Component
    private ActionLink decrease;

    @Component
    private ActionLink increase;

    // ALWAYS provide an id
    @Component(parameters = "id=counterZone")
    private Zone counterZone;

    // context is needed - due to none page refresh
    @Component(parameters = {"zone=counterZone", "context=counter"})
    private ActionLink decreaseAjax;

    @Component(parameters = {"zone=counterZone", "context=counter"})
    private ActionLink increaseAjax;

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
    
    Object onActionFromIncreaseAjax(Long counter)
    {
        this.counter = ++counter;
        return counterZone.getBody();
    }

    Object onActionFromDecreaseAjax(Long counter)
    {
        this.counter = --counter;
        return counterZone.getBody();
    }
}
