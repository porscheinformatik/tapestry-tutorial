package at.porscheinformatik.tapestry.services;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

import at.porscheinformatik.tapestry.dto.ResultContext;
import at.porscheinformatik.tapestry.pages.resultprocessor.BusinessPartnerDetail;
import at.porscheinformatik.tapestry.pages.resultprocessor.VehicleDetail;

public class ResultContextProcessor implements ComponentEventResultProcessor<ResultContext>
{
    private final PageRenderLinkSource pageRenderLinkSource;
    private final Response response;

    public ResultContextProcessor(PageRenderLinkSource pageRenderLinkSource, Response response)
    {
        super();
        this.pageRenderLinkSource = pageRenderLinkSource;
        this.response = response;
    }

    public void processResultValue(ResultContext value) throws IOException
    {
        ResultContext param = (ResultContext) value;

        Link link;
        if (param.contextId % 2 == 0)
        {
            link = pageRenderLinkSource.createPageRenderLinkWithContext(VehicleDetail.class, param.contextId);
        }
        else
        {
            link =
                pageRenderLinkSource.createPageRenderLinkWithContext(BusinessPartnerDetail.class, param.contextId);
        }

        response.sendRedirect(link);
    }
}
