package com.hlt.dog_prank.data.local

import com.hlt.dog_prank.R
import com.hlt.dog_prank.domain.model.SoundItem

object DogSoundData {

    val list: List<SoundItem> = listOf(

        // ===== BASIC =====
        SoundItem(R.drawable.ic_dog_curious, R.string.curious, R.raw.dog_curious_sniff),
        SoundItem(R.drawable.ic_dog_let_play, R.string.lets_play, R.raw.dog_playful_squeak),
        SoundItem(R.drawable.ic_dog_angry, R.string.angry, R.raw.dog_angry),
        SoundItem(R.drawable.ic_dog_friendly, R.string.friendly, R.raw.dog_friendly_greet),
        SoundItem(R.drawable.ic_dog_happy, R.string.happy, R.raw.dog_happy),
        SoundItem(R.drawable.ic_dog_cry, R.string.cry, R.raw.dog_cry),

        // ===== ACTION =====
        SoundItem(R.drawable.ic_dog_agree, R.string.agree, R.raw.dog_agree),
        SoundItem(R.drawable.ic_dog_begging, R.string.begging, R.raw.dog_begging),
        SoundItem(R.drawable.ic_dog_dance, R.string.dance, R.raw.dog_dance),
        SoundItem(R.drawable.ic_dog_handclap, R.string.hand_clap, R.raw.dog_handclap),
        SoundItem(R.drawable.ic_dog_raise_hand, R.string.raise_hand, R.raw.dog_raise_hand),
        SoundItem(R.drawable.ic_dog_pet, R.string.pet, R.raw.dog_pet),

        // ===== EMOTION =====
        SoundItem(R.drawable.ic_dog_happy_cry, R.string.happy_cry, R.raw.dog_happy_cry),
        SoundItem(R.drawable.ic_dog_sad, R.string.sad, R.raw.dog_sad),
        SoundItem(R.drawable.ic_dog_soft_angry, R.string.soft_angry, R.raw.dog_soft_angry),
        SoundItem(R.drawable.ic_dog_super_angry, R.string.super_angry, R.raw.dog_super_angry),
        SoundItem(R.drawable.ic_dog_shy, R.string.shy, R.raw.dog_shy),
        SoundItem(R.drawable.ic_dog_wonder, R.string.wonder, R.raw.dog_wonder),
        SoundItem(R.drawable.ic_dog_wow, R.string.wow, R.raw.dog_wow),

        // ===== GREETING =====
        SoundItem(R.drawable.ic_dog_hi, R.string.hi, R.raw.dog_hi),
        SoundItem(R.drawable.ic_dog_hi_fence, R.string.hi_fence, R.raw.dog_hi_fence),
        SoundItem(R.drawable.ic_dog_yes, R.string.yes, R.raw.dog_yes),
        SoundItem(R.drawable.ic_dog_no, R.string.no, R.raw.dog_no),
        SoundItem(R.drawable.ic_dog_yeah, R.string.yeah, R.raw.dog_yeah),

        // ===== LOVE =====
        SoundItem(R.drawable.ic_dog_love, R.string.love, R.raw.dog_love),
        SoundItem(R.drawable.ic_dog_adoring_chatter, R.string.adoring_chatter, R.raw.dog_adoring_chatter),
        SoundItem(R.drawable.ic_dog_affectionate_barking, R.string.affectionate_barking, R.raw.dog_affectionate_barking),
        SoundItem(R.drawable.ic_dog_affectionate_howl, R.string.affectionate_howl, R.raw.dog_affectionate_howl),
        SoundItem(R.drawable.ic_dog_affectionate_lick, R.string.affectionate_lick, R.raw.dog_affectionate_lick),
        SoundItem(R.drawable.ic_dog_affectionate_nuzzle, R.string.affectionate_nuzzle, R.raw.dog_affectionate_nuzzle),

        // ===== PLAYFUL =====
        SoundItem(R.drawable.ic_dog_playful_bleat, R.string.playful_bleat, R.raw.dog_playful_bleat),
        SoundItem(R.drawable.ic_dog_playful_chirping, R.string.playful_chirping, R.raw.dog_playful_chirping),
        SoundItem(R.drawable.ic_dog_playful_squeak, R.string.playful_squeak, R.raw.dog_playful_squeak),
        SoundItem(R.drawable.ic_dog_playful_yip, R.string.playful_yip, R.raw.dog_playful_yip),

        // ===== EXCITED =====
        SoundItem(R.drawable.ic_dog_excited_woof, R.string.excited_woof, R.raw.dog_excited_woof),
        SoundItem(R.drawable.ic_dog_ecstatic_barking, R.string.ecstatic_barking, R.raw.dog_ecstatic_barking),
        SoundItem(R.drawable.ic_dog_ecstatic_peep, R.string.ecstatic_peep, R.raw.dog_ecstatic_peep),
        SoundItem(R.drawable.ic_dog_thrilled_braying, R.string.thrilled, R.raw.dog_thrilled_braying),

        // ===== RELAX =====
        SoundItem(R.drawable.ic_dog_relaxed_purr, R.string.relaxed, R.raw.dog_relaxed_purr),
        SoundItem(R.drawable.ic_dog_sleepy_sigh, R.string.sleepy, R.raw.dog_sleepy_sigh),
        SoundItem(R.drawable.ic_dog_exhausted, R.string.exhausted, R.raw.dog_exhausted),

        // ===== OTHER =====
        SoundItem(R.drawable.ic_dog_thirsty_lap, R.string.thirsty, R.raw.dog_thirsty_lap),
        SoundItem(R.drawable.ic_dog_hungry_chew, R.string.hungry, R.raw.dog_hungry_chew),
        SoundItem(R.drawable.ic_dog_alert_bark, R.string.alert, R.raw.dog_alert_bark)
    )
}
