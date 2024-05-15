package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.Post
import com.ionciolac.port.inputs.PostInPort
import com.ionciolac.port.outputs.PostOutPort
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
        String id = post.getId()
        Optional<Post> optionalPost = postOutPort.getPost(id)
        if (optionalPost.isPresent()) {
            Post dbPost = optionalPost.get()
            dbPost.setPost(post.getPost())
            return postOutPort.upsertPost(dbPost)
        } else
            throw new ObjectNotFoundException(String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.POST, id))
    }

    @Override
    void deletePost(String authorizedUserId, String id) {
        postOutPort.deletePost(id)
    }

    @Override
    Post getPost(String authorizedUserId, String id) {
        Optional<Post> postFromDB = postOutPort.getPost(id)
        if (postFromDB.isPresent())
            return postFromDB.get()
        else
            throw new ObjectNotFoundException(String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.POST, id))
    }

    @Override
    Page<Post> getPostUser(String authorizedUserId, Pageable pageable) {
        return postOutPort.getPost(authorizedUserId, pageable)
    }
}
