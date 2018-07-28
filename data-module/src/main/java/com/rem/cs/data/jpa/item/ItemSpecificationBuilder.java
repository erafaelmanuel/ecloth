package com.rem.cs.data.jpa.item;

import com.rem.cs.data.jpa.domain.SearchCriteria;
import com.rem.cs.data.jpa.entity.Item;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ItemSpecificationBuilder {

    private List<SearchCriteria> params;

    public ItemSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public ItemSpecificationBuilder with(String key, String operation, String value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Item> build() {
        if (params.size() == 0) {
            return null;
        }
        List<Specification<Item>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new ItemSpecification(param));
        }

        Specification<Item> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = result.and(specs.get(i));
        }
        return result;
    }

    public int getParamSize() {
        return params.size();
    }
}
