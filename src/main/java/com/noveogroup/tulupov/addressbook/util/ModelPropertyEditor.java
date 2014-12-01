package com.noveogroup.tulupov.addressbook.util;

import com.noveogroup.tulupov.addressbook.model.Model;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;

/**
 * Model property editor.
 *
 * @param <T> the model
 */
@Slf4j
public class ModelPropertyEditor<T extends Model> extends PropertyEditorSupport {

    private final Class<T> clazz;

    public ModelPropertyEditor(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getAsText() {
        final Object value = getValue();

        if (clazz.isInstance(value)) {
            final T model = clazz.cast(value);
            if (model.getId() != null) {
                return String.valueOf(model.getId());
            }
        }

        return "";
    }

    @Override
    public void setAsText(final String value) {
        try {
            final T obj = clazz.newInstance();
            obj.setId(Integer.valueOf(value));
            setValue(obj);
        } catch (Exception e) {
            log.error("Cannot convert string to model", e);
        }

    }
}
