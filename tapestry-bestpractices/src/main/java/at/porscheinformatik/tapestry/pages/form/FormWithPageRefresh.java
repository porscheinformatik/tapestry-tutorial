package at.porscheinformatik.tapestry.pages.form;

import java.util.Collection;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.LinkSubmit;
import org.apache.tapestry5.corelib.components.TextField;

import at.porscheinformatik.tapestry.components.SessionComponent;
import at.porscheinformatik.tapestry.dto.DataStore;
import at.porscheinformatik.tapestry.dto.DetailDataDTO;

/**
 * Attention: Seems that we do not have any object in the session - but we have one: Validation tracker of error
 * component to keep the input data on error
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class FormWithPageRefresh
{
    @Component
    private Form form;

    @Component(parameters = {"value=data.name", "validate=maxLength=4"})
    private TextField input;

    @Component
    private LinkSubmit save;

    @Component
    private SessionComponent sessionComponent;

    @Component
    private Errors errors;
    
    @Component(parameters="source=dataGridSource")
    private Grid dataGrid;

    @ActivationRequestParameter
    private Long dataId;

    void onValidateFromForm()
    {
        sessionComponent.render();
    }

    void onSuccessFromForm()
    {
        dataId = DataStore.save(getData());
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
