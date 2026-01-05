package com.hlt.dog_prank.data.local

import com.hlt.dog_prank.R

object FakePetTranslateData {

    // ===== DOG =====
    private val dogAll = listOf(
        R.raw.dog_hi,
        R.raw.dog_happy,
        R.raw.dog_yes,
        R.raw.dog_wow
    )
    private val dogCache = LinkedHashMap<String, Int>()
    private val dogPool = dogAll.toMutableList()

    // ===== CAT =====
    private val catAll = listOf(
        R.raw.cat_hello,
        R.raw.cat_soft_chirp,
        R.raw.cat_yes,
        R.raw.cat_friendly_meow
    )
    private val catCache = LinkedHashMap<String, Int>()
    private val catPool = catAll.toMutableList()

    fun getSound(target: String, key: String): Int {
        return if (target == "cat") {
            getFromPool(key, catCache, catPool, catAll)
        } else {
            getFromPool(key, dogCache, dogPool, dogAll)
        }
    }

    private fun getFromPool(
        key: String,
        cache: LinkedHashMap<String, Int>,
        pool: MutableList<Int>,
        all: List<Int>
    ): Int {
        cache[key]?.let { return it }

        if (pool.isEmpty()) pool.addAll(all)

        val sound = pool.random()
        pool.remove(sound)
        cache[key] = sound
        return sound
    }
}
