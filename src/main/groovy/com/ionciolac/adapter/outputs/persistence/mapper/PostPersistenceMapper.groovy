package com.ionciolac.adapter.outputs.persistence.mapper

import com.ionciolac.adapter.outputs.persistence.entity.PostEntity
import com.ionciolac.common.model.Post

interface PostPersistenceMapper {

    Post toPost(PostEntity postEntity)

    PostEntity toPostEntity(Post post)
}