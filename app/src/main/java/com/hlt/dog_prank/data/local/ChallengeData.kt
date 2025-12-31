package com.hlt.dog_prank.data.local

import android.content.Context
import com.hlt.dog_prank.R
import com.hlt.dog_prank.domain.model.ChallengeItem

object ChallengeData {

    fun getList(context: Context): List<ChallengeItem> = listOf(
        ChallengeItem(0, context.getString(R.string.get_your_dog_attention_with_this_sound),
            "https://system.merryblue.vn/dog/audio/dog_sound_Get your dog attention with this sound.mp3"),

        ChallengeItem(1, context.getString(R.string.only_dog_can_here_this_sound_try_it),
            "https://system.merryblue.vn/dog/audio/dog_sound_Only dog can here this sound, try it.mp3"),

        ChallengeItem(2, context.getString(R.string.play_this_song_to_see_your_dog_reaction),
            "https://system.merryblue.vn/dog/audio/dog_sound_Play this song to see your dog reaction.mp3"),

        ChallengeItem(3, context.getString(R.string.play_this_sound_to_make_your_dog_relax),
            "https://system.merryblue.vn/dog/audio/dog_sound_Play this sound to make your dog relax.mp3"),

        ChallengeItem(4, context.getString(R.string.play_this_sound_to_puts_your_dog_to_sleep),
            "https://system.merryblue.vn/dog/audio/dog_sound_Play this sound to puts your dog to sleep.mp3"),

        ChallengeItem(5, context.getString(R.string.play_this_sound_to_see_if_your_dog_will_help_puppy),
            "https://system.merryblue.vn/dog/audio/dog_sound_Play this sound to see if your dog will help puppy.mp3"),

        ChallengeItem(6, context.getString(R.string.play_this_sound_to_see_if_your_dog_will_help_puppy),
            "https://system.merryblue.vn/dog/audio/dog_sound_Play this sound to see your dog reaction.mp3"),

        ChallengeItem(7, context.getString(R.string.test_your_dog_with_this_sound),
            "https://system.merryblue.vn/dog/audio/dog_sound_Test your dog with this sound.mp3"),

        ChallengeItem(8, context.getString(R.string.this_sound_will_make_your_dog_come_cuddle_with_you),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog come cuddle with you.mp3"),

        ChallengeItem(9, context.getString(R.string.this_sound_will_make_your_dog_confused),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog confused.mp3"),

        ChallengeItem(10, context.getString(R.string.this_sound_will_make_your_dog_ecxited),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog ecxited.mp3"),

        ChallengeItem(11, context.getString(R.string.this_sound_will_make_your_dog_go_crazy),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog go crazy.mp3"),

        ChallengeItem(12, context.getString(R.string.this_sound_will_make_your_dog_howl),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog howl.mp3"),

        ChallengeItem(13, context.getString(R.string.this_sound_will_make_your_dog_tilt_their_head),
            "https://system.merryblue.vn/dog/audio/dog_sound_This sound will make your dog tilt their head.mp3")
    )
}
