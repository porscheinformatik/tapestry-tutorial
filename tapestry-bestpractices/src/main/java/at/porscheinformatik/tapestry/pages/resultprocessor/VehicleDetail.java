package at.porscheinformatik.tapestry.pages.resultprocessor;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;

import at.porscheinformatik.tapestry.components.Layout;

public class VehicleDetail
{
    @Component
    private Layout layout;
    
    @Property(write = false)
    private Long vehicleId;

    void onActivate(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    Long onPassivate()
    {
        return this.vehicleId;
    }
}
