package com.itqpleyva.querydsl.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserModel is a Querydsl query type for UserModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserModel extends EntityPathBase<UserModel> {

    private static final long serialVersionUID = -2080028327L;

    public static final QUserModel userModel = new QUserModel("userModel");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QUserModel(String variable) {
        super(UserModel.class, forVariable(variable));
    }

    public QUserModel(Path<? extends UserModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserModel(PathMetadata metadata) {
        super(UserModel.class, metadata);
    }

}

