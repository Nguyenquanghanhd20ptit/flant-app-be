
package com.example.plantapp.commons.data.model.paging;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class PageableParamParser {
    public PageableParamParser() {
    }

    public Class<PageableCustom> type() {
        return PageableCustom.class;
    }

    public static PageableCustom parser(Map<String, String[]> parameters) {
        PageableCustom pageable = new PageableCustom();
        int page = PageableCustom.DEFAULT_PAGE;
        if (parameters.containsKey("page") && !isEmpty(parameters.get("page")[0])) {
            page = NumberUtils.toInt(parameters.get("page")[0], PageableCustom.DEFAULT_PAGE);
        }
        pageable.setPage(page);

        int pageSize = PageableCustom.DEFAULT_PAGE_SIZE;
        if (parameters.containsKey("page_size") && !isEmpty(parameters.get("page_size")[0])) {
            pageSize = NumberUtils.toInt(parameters.get("page_size")[0], PageableCustom.DEFAULT_PAGE_SIZE);
        }
        pageSize = pageSize < 0 ? PageableCustom.MAXIMUM_PAGE_SIZE : pageSize;
        pageable.setPageSize(pageSize);


        String offset = null;
        if (parameters.containsKey("offset") && !isEmpty(parameters.get("offset")[0])) {
            offset = parameters.get("offset")[0];
        }
        pageable.setOffset(NumberUtils.toInt(offset, (page - 1) * pageSize));
        if (parameters.containsKey("sort") && !isEmpty(parameters.get("sort")[0])) {
            List<OrderCustom> orders = getOrder(parameters.get("sort"));
            if (!orders.isEmpty()) pageable.setSort(orders);
        }
        return pageable;
    }

    private static List<OrderCustom> getOrder(String[] orders) {
        return orders == null ?
                null :
                Stream.of(orders)
                        .filter(Objects::nonNull)
                        .map(PageableParamParser::getOrder)
                        .filter(Objects::nonNull)
                        .collect(toList());
    }

    private static OrderCustom getOrder(String order) {
        String[] arr = order.split(",");
        if (arr.length == 1) {
            return new OrderCustom(arr[0], OrderCustom.Direction.desc.name());
        } else if (arr.length != 2) {
            return null;
        } else {
            return new OrderCustom(arr[0], arr[1].toLowerCase());
        }
    }
}