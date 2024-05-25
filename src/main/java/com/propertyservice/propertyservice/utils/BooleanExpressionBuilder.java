package com.propertyservice.propertyservice.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BooleanExpressionBuilder {
    public BooleanBuilder orBooleanBuilder(BooleanExpression... conditions) {
        BooleanBuilder builder = new BooleanBuilder();
        for (BooleanExpression condition : conditions) {
            if (condition != null) {
                builder.or(condition);
            }
        }
        return builder;
    }

    public BooleanBuilder andBooleanBuilder(BooleanExpression... conditions) {
        BooleanBuilder builder = new BooleanBuilder();
        for (BooleanExpression condition : conditions) {
            if (condition != null) {
                builder.and(condition);
            }
        }
        return builder;
    }
}
