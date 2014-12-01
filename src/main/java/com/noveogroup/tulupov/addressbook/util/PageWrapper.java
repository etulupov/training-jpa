package com.noveogroup.tulupov.addressbook.util;


import com.noveogroup.tulupov.addressbook.model.PageItemModel;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Page wrapper.
 */
public final class PageWrapper {
    @Getter
    @SuppressWarnings("all")
    private List<PageItemModel> items;

    public PageWrapper(final Page<?> page) {
        if (page.getTotalElements() != 0) {
            items = new ArrayList<>();

            final int pageCount = page.getTotalPages();
            final int current = page.getNumber();

            items.add(PageItemModel.builder()
                    .number(Math.max(current - 1, 0))
                    .title("&laquo;")
                    .active(false)
                    .disabled(current == 0)
                    .build());

            for (int i = 0; i < pageCount; i++) {
                items.add(PageItemModel.builder()
                        .number(i)
                        .title(String.valueOf(i + 1))
                        .active(i == current)
                        .build());
            }

            items.add(PageItemModel.builder()
                    .number(Math.min(current + 1, pageCount - 1))
                    .title("&raquo;")
                    .active(false)
                    .disabled(current == pageCount - 1)
                    .build());
        }
    }
}
