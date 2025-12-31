package com.hlt.dog_prank.data.local

import android.content.Context
import com.hlt.dog_prank.R
import com.hlt.dog_prank.domain.model.WhistleStepData

object WhistleStepProvider {

    fun getSteps(context: Context): List<WhistleStepData> {
        return listOf(

            // STEP 1
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "Prepare some treats and put your dog on a long leash. Blow the whistle in unique ways to signal what you want the dog to do. For example, give a single long blast or a series of three short blasts for “Come”.",
                iconRes = R.drawable.w_step1,
                dotActiveRes = R.drawable.dot_w1
            ),

            // STEP 2
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "Gently pull on the leash in your direction or lure your dog with a treat. Reward them with a treat.",
                iconRes = R.drawable.w_step2,
                dotActiveRes = R.drawable.dot_w2
            ),

            // STEP 3
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "Repeat this a few times during a walk and in the next few days.",
                iconRes = R.drawable.w_step3,
                dotActiveRes = R.drawable.dot_w3
            ),

            // STEP 4
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "Gradually increase the distance between you and your dog. Always follow the pattern: give a whistle blast, wait for your dog to come to you, and reward them with a treat.",
                iconRes = R.drawable.w_step4,
                dotActiveRes = R.drawable.dot_w4
            ),

            // STEP 5
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "When teaching another command, choose another combination of blasts. For example, it can be one blast for “Sit”. Give a blast and lure your dog to sit. Say a marker word “Yes” and give your pet a treat.",
                iconRes = R.drawable.w_step5,
                dotActiveRes = R.drawable.dot_w5
            ),

            // STEP 6
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "Repeat this five times a day during the week. In the next stage, give the whistle signal, wait for your dog to sit, say a marker word “Yes” and give your dog a treat.",
                iconRes = R.drawable.w_step6,
                dotActiveRes = R.drawable.dot_w6
            ),

            // STEP 7
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "When your dog learns well that they should sit when you give them the whistle signal, don’t reward them with treats every time they sit.",
                iconRes = R.drawable.w_step7,
                dotActiveRes = R.drawable.dot_w7
            ),

            // STEP 8
            WhistleStepData(
                title = "Train your dog to respond to a whistle\nTeach “Come” and “Sit” commands",
                description = "In the next stage, don’t reward your dog with treats. Your dog should simply react to the whistle and sit.",
                iconRes = R.drawable.w_step8,
                dotActiveRes = R.drawable.dot_w8
            )
        )
    }
}
