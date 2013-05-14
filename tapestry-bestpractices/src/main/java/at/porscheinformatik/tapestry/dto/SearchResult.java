package at.porscheinformatik.tapestry.dto;

public class SearchResult
{
    public final Long id;

    public final String summary;

    public SearchResult(Long id, String summary)
    {
        super();
        this.id = id;
        this.summary = summary;
    }

}
