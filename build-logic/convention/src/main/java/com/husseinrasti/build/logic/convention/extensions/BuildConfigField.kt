package com.husseinrasti.build.logic.convention.extensions

import com.android.builder.model.ClassField

fun String.pairOfBuildConfigField(type: String, value: String): Pair<String, ClassField> =
    Pair(
        this,
        object : ClassField {
            override fun getType(): String {
                return type
            }

            override fun getName(): String {
                return this@pairOfBuildConfigField
            }

            override fun getValue(): String {
                return value
            }

            override fun getDocumentation(): String {
                return ""
            }

            override fun getAnnotations(): MutableSet<String> {
                return mutableSetOf()
            }
        }
    )