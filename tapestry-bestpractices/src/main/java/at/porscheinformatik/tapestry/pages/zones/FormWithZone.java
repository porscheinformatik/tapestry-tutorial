package at.porscheinformatik.tapestry.pages.zones;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.LinkSubmit;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import at.porscheinformatik.tapestry.components.SessionComponent;
import at.porscheinformatik.tapestry.dto.DataStore;
import at.porscheinformatik.tapestry.dto.DetailDataDTO;

/**
 * Demonstrates to clean up the session
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class FormWithZone
{
    @Component(parameters = "zone=formZone")
    private Form form;

    @Component(parameters = {"value=data.name", "validate=maxLength=4"})
    private TextField input;

    @Component
    private LinkSubmit save;

    @Component
    private SessionComponent sessionComponent;

    @Component(parameters = "source=dataGridSource")
    private Grid dataGrid;

    @Component(parameters = "id=formZone")
    private Zone formZone;

    @Component(parameters = "id=gridZone")
    private Zone gridZone;

    @Component
    private Errors errors;

    @Inject
    private ComponentResources componentResources;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @ActivationRequestParameter
    private Long dataId;

    // discard if it could save successfully
    void onSuccessFromForm()
    {
        dataId = DataStore.save(getData());
        // reload the grid an the form
        ajaxResponseRenderer.addRender(formZone).addRender(gridZone);
    }
    
    void onFailureFromForm()
    {
        // show the errors
        ajaxResponseRenderer.addRender(formZone);
    }

    // load objects when they are needed
    @Cached
    public DetailDataDTO getData()
    {
        if (dataId != null)
        {
            return DataStore.findById(dataId);
        }
        return new DetailDataDTO();
    }

    @Cached
    public Collection<DetailDataDTO> getDataGridSource()
    {
        return DataStore.findAll();
    }
}
