package com.noveogroup.tulupov.addressbook.util;

import com.noveogroup.tulupov.addressbook.model.ValueModel;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;

/**
 * Model property editor.
 *
 * @param <T> the value model
 */
@Slf4j
public class ValueModelPropertyEditor<T extends ValueModel> extends PropertyEditorSupport {

    private Class<T> clazz;

    public ValueModelPropertyEditor(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getAsText() {
        final Object value = getValue();

        if (clazz.isInstance(value)) {
            final T model = clazz.cast(value);
            if (model.getId() != null) {
                return model.getValue();
            }
        }

        return "";
    }

    @Override
    public void setAsText(final String value) {
        try {
            final T obj = clazz.newInstance();
            obj.setValue(value);
            setValue(obj);
        } catch (Exception e) {
            log.error("Cannot convert string to model", e);
        }

    }
}
