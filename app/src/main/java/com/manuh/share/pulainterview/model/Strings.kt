package com.manuh.share.pulainterview.model

import com.google.gson.annotations.SerializedName

data class Strings(
    @SerializedName("en")
    var en: En?
) {
    constructor() : this(null)
}