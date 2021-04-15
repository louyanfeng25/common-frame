package com.baiyan.common.base.utils;

import com.baiyan.common.base.exception.ValidationException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 校验工具类
 *
 * @author baiyan
 * @date 2020/11/13
 */
public class ValidationUtil {

    public static void sneakyThrow(String code, Object... params) {
        throw new ValidationException(code, params);
    }

    public static void isTrue(boolean expect, String code, Object... params) {
        if (!expect) {
            throw ValidationException.of(code, params);
        }
    }

    public static void isFalse(boolean expect, String code, Object... params) {
        isTrue(!expect, code, params);
    }

    public static void wasTrue(Boolean expect, String code, Object... params) {
        isTrue(BooleanUtils.isTrue(expect), code, params);
    }

    public static void wasFalse(Boolean expect, String code, Object... params) {
        isFalse(BooleanUtils.isTrue(expect), code, params);
    }

    public static void equals(String first, String second, String code, Object... params) {
        isTrue(first.equals(second), code, params);
    }

    public static void nil(Object object, String code, Object... params) {
        isTrue(Objects.isNull(object), code, params);
    }

    public static void notNull(Object object, String code, Object... params) {
        isTrue(Objects.nonNull(object), code, params);
    }

    public static void equals(Object first, Object second, String code, Object... params) {
        isTrue(Objects.equals(first, second), code, params);
    }

    public static void notEquals(Object first, Object second, String code, Object... params) {
        isTrue(!Objects.equals(first, second), code, params);
    }

    public static void empty(Collection collection, String code, Object... params) {
        isTrue(CollectionUtils.isEmpty(collection), code, params);
    }

    public static void notEmpty(Collection collection, String code, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(collection), code, params);
    }

    public static <T> void empty(T[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static <T> void notEmpty(T[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static <T> void empty(boolean[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static <T> void notEmpty(boolean[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(byte[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(byte[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(short[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(short[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(char[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(char[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(int[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(int[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(float[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(float[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(long[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(long[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void empty(double[] array, String code, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), code, params);
    }

    public static void notEmpty(double[] array, String code, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), code, params);
    }

    public static void blank(String str, String code, Object... params) {
        isTrue(StringUtils.isBlank(str), code, params);
    }

    public static void notBlank(String str, String code, Object... params) {
        isTrue(StringUtils.isNotBlank(str), code, params);
    }

}
