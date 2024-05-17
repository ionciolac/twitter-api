package com.ionciolac.common.util

class CommonMessage {
    static final def COMMENT = "Comment"
    static final def LIKE = "Like"
    static final def POST = "Post"
    static final def USER = "User"
    static final def DELETE = "Delete"
    static final def EDIT = "Edit"
    static final def VIEW = "View"
    static final def USER_ALREADY_IS_REGISTERED = "User with same username, email or phone already is registered"
    static final def NOT_FOUND_MESSAGE = "%s was not found in DB by id %s"
    static final def USER_ALREADY_FOLLOWING_USER = "User %s already following this user %s"
    static final def USER_WAS_NOT_FOUND_BY_USERNAME = "User with this %s username was not found in DB"
    static final def USER_ALREADY_LIKED_POST = "User %s already liked post %s"
    static final def FOREIGN_MESSAGE = "You can't %s foreign %s."
    static final def CANNOT_FOLLOW_YOUR_SELF = "You can't follow yourself."
}
