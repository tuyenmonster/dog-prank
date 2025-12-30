package com.hlt.dog_prank.data.local

import com.hlt.dog_prank.R
import com.hlt.dog_prank.domain.model.SoundItem

object CatSoundData {

    val list: List<SoundItem> = listOf(

        // ===== BASIC =====
        SoundItem(R.drawable.ic_cat_hello, R.string.hello, R.raw.cat_hello),
        SoundItem(R.drawable.ic_cat_i_love_u, R.string.i_love_you, R.raw.cat_how_are_you),
        SoundItem(R.drawable.ic_cat_come_here, R.string.come_here, R.raw.cat_come_here),
        SoundItem(R.drawable.ic_cat_fine, R.string.fine, R.raw.cat_fine),
        SoundItem(R.drawable.ic_cat_angry, R.string.angry, R.raw.cat_fine),

        // ===== EMOTION =====
        SoundItem(R.drawable.ic_cat_happy, R.string.happy, R.raw.cat_yes),
        SoundItem(R.drawable.ic_cat_tired, R.string.tired, R.raw.cat_leave_me_alone),
        SoundItem(R.drawable.ic_cat_sleep, R.string.sleep, R.raw.cat_what_have_you_done),
        SoundItem(R.drawable.ic_cat_anxious_moan, R.string.anxious, R.raw.cat_anxious_moan),
        SoundItem(R.drawable.ic_cat_pain_meow, R.string.pain, R.raw.cat_pain_meow),

        // ===== HISS / FIGHT =====
        SoundItem(R.drawable.ic_cat_fighting, R.string.fighting, R.raw.cat_should_not),
        SoundItem(R.drawable.ic_cat_fighting_hiss, R.string.fight_hiss, R.raw.cat_fight_hiss),
        SoundItem(R.drawable.ic_cat_defensive_hiss, R.string.defensive_hiss, R.raw.cat_defensive_hiss),
        SoundItem(R.drawable.ic_cat_bossy_hiss, R.string.bossy_hiss, R.raw.cat_bossy_hiss),

        // ===== PURR =====
        SoundItem(R.drawable.ic_cat_deep_purring, R.string.deep_purr, R.raw.cat_deep_purring),
        SoundItem(R.drawable.ic_cat_soft_purr, R.string.soft_purr, R.raw.cat_soft_purr),
        SoundItem(R.drawable.ic_cat_low_purring, R.string.low_purr, R.raw.cat_low_purr),

        // ===== MEOW =====
        SoundItem(R.drawable.ic_cat_bass_meow, R.string.bass_meow, R.raw.cat_bass_meow),
        SoundItem(R.drawable.ic_cat_grating_meow, R.string.grating_meow, R.raw.cat_grating_meow),
        SoundItem(R.drawable.ic_cat_shrill_meow, R.string.shrill_meow, R.raw.cat_shrill_meow),
        SoundItem(R.drawable.ic_cat_soft_chirp, R.string.soft_chirp, R.raw.cat_soft_chirp),
        SoundItem(R.drawable.ic_cat_sweet_meow, R.string.sweet_meow, R.raw.cat_sweet_meow),

        // ===== OTHER =====
        SoundItem(R.drawable.ic_cat_whiny_yowl, R.string.whiny, R.raw.cat_whiny_yowl),
        SoundItem(R.drawable.ic_cat_short_yowl, R.string.short_yowl, R.raw.cat_short_yowl),
        SoundItem(R.drawable.ic_cat_weak_mew, R.string.weak_meow, R.raw.cat_weak_mew)
    )
}
