package com.rem.cs.rest.client.item;

import com.rem.cs.rest.client.BaseService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

@Component("restClientItemService")
public class ItemService extends BaseService {

    public ItemService() {}

    public PagedResources<Item> getAll() {
        return getAll(0, null);
    }

    public PagedResources<Item> getAll(int page, String search) {
        final String url = "http://localhost:8080/api/items";
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        if (!StringUtils.isEmpty(search)) {
            builder.queryParam("search", search);
        }
        if (page > 0) {
            builder.queryParam("page", page);
        }
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResources<Item>>() {
                }).getBody();
    }

    public Item getById(String id) {
        return restTemplate.getForObject("http://localhost:8080/api/items/" + id, Item.class);
    }
}
