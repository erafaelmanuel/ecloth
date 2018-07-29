package com.rem.cs.rest.client.resource.item;

import com.rem.cs.rest.client.resource.BaseService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

@Service("itemRestClientService")
public class ItemService extends BaseService {

    public PagedResources<Item> findAll(String search, Number page, Number size, String sort) {
        final String url = "http://localhost:8080/api/items";
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        if (!StringUtils.isEmpty(search)) {
            builder.queryParam("search", search);
        }
        if (page != null && page.intValue() > 0) {
            builder.queryParam("page", page);
        }
        if (size != null && size.intValue() > 0) {
            builder.queryParam("size", size);
        }
        if (!StringUtils.isEmpty(sort)) {
            builder.queryParam("sort", sort);
        }
        return restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference
                        <PagedResources<Item>>() {
                }).getBody();
    }

    public Item getById(String id) {
        return restTemplate.getForObject("http://localhost:8080/api/items/" + id, Item.class);
    }
}