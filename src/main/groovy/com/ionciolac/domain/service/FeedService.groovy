package com.ionciolac.domain.service

import com.ionciolac.common.model.PostMeta
import com.ionciolac.domain.mapper.FeedServiceMapper
import com.ionciolac.domain.model.Feed
import com.ionciolac.port.inputs.FeedInPort
import com.ionciolac.port.outputs.CommentOutPort
import com.ionciolac.port.outputs.FollowerOutPort
import com.ionciolac.port.outputs.LikeOutPort
import com.ionciolac.port.outputs.PostOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class FeedService implements FeedInPort {

    FeedServiceMapper feedServiceMapper
    CommentOutPort commentOutPort
    FollowerOutPort followerOutPort
    LikeOutPort likeOutPort
    PostOutPort postOutPort

    FeedService(FeedServiceMapper feedServiceMapper, CommentOutPort commentOutPort, FollowerOutPort followerOutPort,
                LikeOutPort likeOutPort, PostOutPort postOutPort) {
        this.feedServiceMapper = feedServiceMapper
        this.commentOutPort = commentOutPort
        this.followerOutPort = followerOutPort
        this.likeOutPort = likeOutPort
        this.postOutPort = postOutPort
    }


    @Override
    Page<Feed> getMyFeed(String authorizedUserId, Pageable pageable) {
        return getUserFeed(authorizedUserId, pageable)
    }

    @Override
    Page<Feed> getUserMyFeed(String authorizedUserId, String userId, Pageable pageable) {
        return getUserFeed(userId, pageable)
    }

    def getUserFeed(String userId, Pageable pageable) {
        def userIds = followerOutPort.getUserFollowers(userId)
        return postOutPort.getPostsByUsersId(userIds, pageable)
                .map { feedServiceMapper.toFeed(it) }
                .map {
                    {
                        PostMeta postMeta = PostMeta.builder()
                                .commentCount(commentOutPort.getPostCommentsCount(it.getId()))
                                .likeCount(likeOutPort.getPostLikesCount(it.getId()))
                                .build()
                        it.setPostMeta(postMeta)
                        return it
                    }
                }
    }
}
