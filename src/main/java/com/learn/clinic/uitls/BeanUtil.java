package com.learn.clinic.uitls;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * 类属性赋值工具
 *
 * @author Milk
 * @version 2023/12/26 20:46
 */
@NoArgsConstructor(access =  AccessLevel.PRIVATE)
public class BeanUtil {

    protected static Mapper BEAN_MAPPER_BUILDER;

    static{
        BEAN_MAPPER_BUILDER = DozerBeanMapperBuilder.buildDefault();
    }

    /**
     * 属性复值
     *
     * @param source 源类
     * @param target 目标类
     * @return 结果
     */
    public static <T, S> T convert(S source, T target){
        Optional.ofNullable(source)
                .ifPresent(each -> BEAN_MAPPER_BUILDER.map(each, target));
        return target;
    }

    /**
     * 属性复值, 根据给定的 Class
     *
     * @param source 源类
     * @param clazz 目标类
     * @return 结果
     */
    public static<T, S> T convert(S source, Class<T> clazz){
        return Optional.ofNullable(source)
                .map(each -> BEAN_MAPPER_BUILDER.map(source, clazz))
                .orElse(null);
    }

}
