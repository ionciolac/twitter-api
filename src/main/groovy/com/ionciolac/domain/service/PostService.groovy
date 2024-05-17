package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.Post
import com.ionciolac.port.inputs.PostInPort
import com.ionciolac.port.outputs.PostOutPort
import org.apache.coyote.BadRequestException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService implements PostInPort {

    PostOutPort postOutPort

    PostService(PostOutPort postOutPort) {
        this.postOutPort = postOutPort
    }

    @Override
    Post createPost(String authorizedUserId, Post post) {
        return postOutPort.upsertPost(post)
    }

    @Override
    Post updatePost(String authorizedUserId, Post post) {
        def id = post.getId()
        def dbPost = getDBPost(id)
        if (authorizedUserId == dbPost.getUserId()) {
            dbPost.setPost(post.getPost())
            return postOutPort.upsertPost(dbPost)
        } else {
            String msg = String.format(CommonMessage.FOREIGN_MESSAGE, CommonMessage.EDIT, CommonMessage.POST)
            throw new BadRequestException(msg)
        }
    }

    @Override
    void deletePost(String authorizedUserId, String id) {
        def post = getDBPost(id)
        if (post.getUserId() != authorizedUserId) {
            String msg = String.format(CommonMessage.FOREIGN_MESSAGE, CommonMessage.DELETE, CommonMessage.POST)
            throw new BadRequestException(msg)
        }
        postOutPort.deletePost(id)
    }

    @Override
    Post getPost(String authorizedUserId, String id) {
        return getDBPost(id)
    }

    @Override
    Page<Post> getPostUser(String authorizedUserId, Pageable pageable) {
        return postOutPort.getPost(authorizedUserId, pageable)
    }

    private def getDBPost(String id) {
        def post = postOutPort.getPost(id)
        if (post.isPresent()) {
            return post.get()
        } else {
            String msg = String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.POST, id)
            throw new ObjectNotFoundException(msg)
        }
    }
}
