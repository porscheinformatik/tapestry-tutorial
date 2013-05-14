package at.porscheinformatik.tapestry.pages.resultprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Grid;

import at.porscheinformatik.tapestry.dto.ResultContext;
import at.porscheinformatik.tapestry.dto.SearchResult;

/**
 * Demonstration of page result processing
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class PageResultProcessorDemo
{
    @Component(parameters = {"source=vehilceBusinessPartners", "row=currentResult"})
    private Grid vehicleBusinessPartnerGrid;

    @Component(parameters = "context=currentResult.id")
    private ActionLink jumpToObjectDetail;

    @Property
    private SearchResult currentResult;

    ResultContext onActionFromJumpToObjectDetail(Long resultId)
    {
        return new ResultContext(resultId);
    }

    @Cached
    public List<SearchResult> getVehilceBusinessPartners()
    {
        List<SearchResult> result = new ArrayList<SearchResult>(10);
        for (long i = 0; i < 10; i++)
        {
            result.add(new SearchResult(i, (i % 2 == 0) ? "Vehicle Summary" : "Business Partner Summary"));
        }
        return result;
    }
}
