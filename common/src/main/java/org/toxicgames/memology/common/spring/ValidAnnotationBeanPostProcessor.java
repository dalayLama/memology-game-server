package org.toxicgames.memology.common.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.toxicgames.memology.common.annotations.Valid;
import org.toxicgames.memology.common.components.validation.AppValidator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    private AppValidator validator;

    private final Map<String, List<ValidContext>> beans = new HashMap<>();

    private final Map<String, Class<?>> beanClasses = new HashMap<>();

    public ValidAnnotationBeanPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods()).forEach(m -> {
            Parameter[] parameters = m.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isAnnotationPresent(Valid.class)) {
                    beanClasses.put(beanName, bean.getClass());
                    addValidContext(beanName, m, i, parameters[i]);
                }
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!beans.containsKey(beanName)) {
            return bean;
        }
        Class<?> beanClass = beanClasses.get(beanName);
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
            List<ValidContext> validContexts = beans.get(beanName);
            validContexts.stream()
                    .filter(c -> isSameMethod(c.getMethod(), method))
                    .forEach(validContext -> {
                        Object arg = args[validContext.getIndex()];
                        getValidator().validate(arg, validContext.getObjectName());
                    });
            return method.invoke(bean, args);
        });
    }

    private void addValidContext(String beanName, Method method, int index, Parameter parameter) {
        Valid annotation = parameter.getAnnotation(Valid.class);
        String objectName = StringUtils.hasText(annotation.objectName()) ?
                annotation.objectName() : parameter.getName();
        ValidContext validContext = new ValidContext(method, index,  parameter, objectName);
        beans.merge(beanName, List.of(validContext), (validContexts, validContexts2) ->
                Stream.concat(validContexts.stream(), validContexts2.stream())
                        .collect(Collectors.toList())
        );
    }

    private AppValidator getValidator() {
        if (validator == null) {
            validator = context.getBean(AppValidator.class);
        }
        return validator;
    }

    private boolean isSameMethod(Method source, Method target) {
        if (!source.getName().equals(target.getName())) {
            return false;
        }
        if (source.getParameterCount() != target.getParameterCount()) {
            return false;
        }
        for (int i = 0; i < source.getParameters().length; i++) {
            Parameter sourceParameter = source.getParameters()[i];
            Parameter targetParameter = target.getParameters()[i];
            if (!isSameParameter(sourceParameter, targetParameter)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameParameter(Parameter source, Parameter target) {
        if (!source.getName().equals(target.getName())) {
            return false;
        }
        return source.getType().equals(target.getType());
    }

    @AllArgsConstructor
    @Getter
    private static class ValidContext {

        private final Method method;

        private final int index;

        private final Parameter parameter;

        private final String objectName;

    }

}
