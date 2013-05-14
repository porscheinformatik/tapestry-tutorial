package at.porscheinformatik.tapestry.pages.resultprocessor;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;

import at.porscheinformatik.tapestry.components.Layout;

public class BusinessPartnerDetail
{
    @Component
    private Layout layout;

    @Property(write = false)
    private Long partnerId;

    void onActivate(Long partnerId)
    {
        this.partnerId = partnerId;
    }

    Long onPassivate()
    {
        return this.partnerId;
    }
}
