package at.porscheinformatik.tapestry.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Database simulator
 * 
 * @author Gerold Glaser (gla)
 * @since 14.05.2013
 */
public class DataStore
{
    private static final Map<Long, DetailDataDTO> store = new HashMap<Long, DetailDataDTO>();

    public static Long save(DetailDataDTO detail)
    {
        if (detail.getId() == null)
        {
            detail.setId(System.nanoTime());
        }

        DataStore.store.put(detail.getId(), detail);

        return detail.getId();
    }

    public static DetailDataDTO findById(Long id)
    {
        if (id == null)
        {
            return null;
        }

        return DataStore.store.get(id);
    }
    
    public static Collection<DetailDataDTO> findAll()
    {
        return DataStore.store.values();
    }
}
