package com.noveogroup.tulupov.addressbook.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract convertor.
 *
 * @param <E> the entity
 * @param <M> the model
 */
public abstract class AbstractConvertor<E, M> {
    private final GenericConversionService conversionService;
    private final Class<E> entityClass;
    private final Class<M> modelClass;

    public AbstractConvertor(final Class<E> entityClass, final Class<M> modelClass) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;

        conversionService = new GenericConversionService();
        conversionService.addConverter(entityClass, modelClass, new EntityToModelConvertor());
        conversionService.addConverter(modelClass, entityClass, new ModelToEntityConvertor());
    }

    public M convertEntityToModel(final E entity) {
        return conversionService.convert(entity, modelClass);
    }

    public E convertModelToEntity(final M model) {
        return conversionService.convert(model, entityClass);
    }

    public List<M> convertEntitiesToModels(final Collection<E> entities) {
        return convert(entities, modelClass);
    }

    public List<E> convertModelsToEntities(final Collection<M> models) {
        return convert(models, entityClass);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <T> List<T> convert(final Collection<?> sourceList, final Class<T> targetClass) {
        final List<Object> targetList = new ArrayList();

        for (final Object sourceObject : sourceList) {
            final Object targetObject = conversionService.convert(sourceObject, targetClass);
            targetList.add(targetObject);
        }

        return (List<T>) targetList;
    }


    protected abstract M convertEntity(E entity);

    protected abstract E convertModel(M model);

    /**
     * Entity to model convertor.
     */
    private class EntityToModelConvertor implements Converter<E, M> {
        @Override
        public M convert(final E entity) {
            return convertEntity(entity);
        }
    }

    /**
     * Model to entity convertor.
     */
    private class ModelToEntityConvertor implements Converter<M, E> {
        @Override
        public E convert(final M model) {
            return convertModel(model);
        }
    }
}
