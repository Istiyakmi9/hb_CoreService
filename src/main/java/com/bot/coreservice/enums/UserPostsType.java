package com.bot.coreservice.enums;

public enum UserPostsType {
    USERPOSTS_FILTER("sp_userposts_filter"),
    OWN_POSTS("sp_userposts_own_posts"),
    APPLIED_JOBS("sp_userposts_applied_jobs"),
    SAVED_JOBS("sp_userposts_saved_jobs");

    private final String value;

    UserPostsType(String spName) {
        this.value = spName;
    }

    public String getStoredProcedureName() {
        return value;
    }
}
