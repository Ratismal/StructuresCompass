/*
 * Copyright (c) 2022 Samarium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 */
package io.github.samarium150.minecraft.mod.structures_compass.util

import net.minecraft.client.MinecraftClient

const val MOD_ID = "structures_compass"

const val prefix = "string.${MOD_ID}."

val minecraftClient: MinecraftClient
    get() = MinecraftClient.getInstance()

@SafeVarargs
fun <T> swap(vararg args: T): T {
    return args[0]
}

data class Rect(
    var left: Int,
    var top: Int,
    var right: Int,
    var bottom: Int
) {
    fun sanitize() : Rect {
        if (left < right)
            left = swap(right, left.also { right = it })
        if (top < bottom)
            top = swap(bottom, top.also { bottom = it })
        return this
    }
}

fun String.convertToRegex(): Regex {
    val regex = StringBuilder("^")
    for (i in this.indices) {
        when (val c = this[i]) {
            '*' -> regex.append(".*")
            '?' -> regex.append(".")
            '.' -> regex.append("\\.")
            else -> regex.append(c)
        }
    }
    regex.append("$")
    return regex.toString().toRegex()
}