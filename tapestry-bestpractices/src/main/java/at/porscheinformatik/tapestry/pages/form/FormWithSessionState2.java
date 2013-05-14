package at.porscheinformatik.tapestry.pages.form;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.LinkSubmit;
import org.apache.tapestry5.corelib.components.TextField;

import at.porscheinformatik.tapestry.components.SessionComponent;
import at.porscheinformatik.tapestry.dto.DataStore;
import at.porscheinformatik.tapestry.dto.DetailDataDTO;
import at.porscheinformatik.tapestry.pages.Start;

public class FormWithSessionState2
{
    @Component
    private Form form;

    @Component(parameters = {"value=data.name", "validate=maxLength=4"})
    private TextField input;

    @Component
    private LinkSubmit save;

    @Component
    private SessionComponent sessionComponent;

    @Component(parameters = "source=dataGridSource")
    private Grid dataGrid;

    @Component
    private Errors errors;

    @Inject
    private ComponentResources componentResources;

    // must be created on previous page
    @SessionState(create = false)
    @Property
    private DetailDataDTO data;

    void onValidateFromForm()
    {
        sessionComponent.render();
    }

    // discard if it could save successfully
    //    @DiscardAfter
    Object onSuccessFromForm()
    {
        // object will be saved
        Long id = DataStore.save(data);
        data = DataStore.findById(id);
        // does not work
//        componentResources.discardPersistentFieldChanges();
        // session entry  must be cleaned up manually
        data = null;
        return Start.class;
    }

    @Cached
    public Collection<DetailDataDTO> getDataGridSource()
    {
        return DataStore.findAll();
    }
}
