package com.example.projectbookbeaconapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class RegisterFragment3Directions private constructor() {
  public companion object {
    public fun actionFourthFragmentToThirdFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_fourthFragment_to_thirdFragment)
  }
}
