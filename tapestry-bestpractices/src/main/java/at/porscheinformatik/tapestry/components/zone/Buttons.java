package at.porscheinformatik.tapestry.components.zone;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 * Demonstrate event bubbling with ajax response renderer
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class Buttons
{
    @Property
    @Parameter(required = true, allowNull = false)
    private Long counter;

    @Component(parameters = "id=buttonZone")
    private Zone buttonZone;

    // context is needed - due to none page refresh
    @Component(parameters = {"zone=counterZone", "context=counter"})
    private ActionLink decreaseAjax;

    @Component(parameters = {"zone=counterZone", "context=counter"})
    private ActionLink increaseAjax;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    // return value is possible, but not needed - return true would stop event bubbling
    void onActionFromIncreaseAjax(Long counter)
    {
        this.counter = ++counter;
        ajaxResponseRenderer.addRender(buttonZone);
    }

    void onActionFromDecreaseAjax(Long counter)
    {
        this.counter = --counter;
        ajaxResponseRenderer.addRender(buttonZone);
    }

}
