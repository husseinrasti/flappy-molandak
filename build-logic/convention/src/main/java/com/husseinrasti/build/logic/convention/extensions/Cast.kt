package com.husseinrasti.build.logic.convention.extensions


@Suppress("unchecked_cast", "nothing_to_inline")
internal
inline fun <T> uncheckedCast(obj: Any?): T =
    obj as T
