package com.noveogroup.tulupov.addressbook.convertor.field;

import java.util.ArrayList;
import java.util.List;

/**
 * Field convertor factory.
 */
public class ChainFieldConvertorFactory {
    public static FieldConverter createChain() {
        final List<Class<? extends ChainFieldConverter>> classes =
                new ArrayList<Class<? extends ChainFieldConverter>>() {
                    {
                        add(DateFieldConvertor.class);
                        add(EmailFieldConvertor.class);
                        add(LinkFieldConvertor.class);
                        add(NumberFieldConvertor.class);
                        add(TextFieldConvertor.class);
                    }
                };

        FieldConverter converter = null;

        try {
            for (final Class<? extends ChainFieldConverter> clazz : classes) {
                final ChainFieldConverter newConvertor = clazz.newInstance();
                newConvertor.setNext(converter);
                converter = newConvertor;
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot create convertors chain", e);
        }

        return converter;
    }
}
