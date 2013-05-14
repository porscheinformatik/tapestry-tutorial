package at.porscheinformatik.tapestry.pages.form;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.LinkSubmit;
import org.apache.tapestry5.corelib.components.TextField;

import at.porscheinformatik.tapestry.components.SessionComponent;
import at.porscheinformatik.tapestry.dto.DataStore;
import at.porscheinformatik.tapestry.dto.DetailDataDTO;
import at.porscheinformatik.tapestry.pages.Start;

/**
 * Demonstrates to clean up the session
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class FormWithDiscarding
{
    @Component
    private Form form;

    @Component(parameters = {"value=data.name", "validate=maxLength=4"})
    private TextField input;

    @Component
    private LinkSubmit save;

    @Component
    private SessionComponent sessionComponent;
    
    @Component(parameters="source=dataGridSource")
    private Grid dataGrid;

    @Component
    private Errors errors;
    
    @Inject
    private ComponentResources componentResources;

    @Persist
    @Property
    private DetailDataDTO data;

    void beginRender()
    {
        if (data == null)
        {
            data = new DetailDataDTO();
        }
    }

    void onValidateFromForm()
    {
        sessionComponent.render();
    }

    // discard if it could save successfully
//    @DiscardAfter
    Object onSuccessFromForm()
    {
        Long id = DataStore.save(data);
        data = DataStore.findById(id);
        componentResources.discardPersistentFieldChanges();
        return Start.class;
    }
    
    @Cached
    public Collection<DetailDataDTO> getDataGridSource()
    {
        return DataStore.findAll();
    }
}
