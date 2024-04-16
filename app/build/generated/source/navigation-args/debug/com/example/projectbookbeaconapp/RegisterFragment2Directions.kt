package com.example.projectbookbeaconapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class RegisterFragment2Directions private constructor() {
  public companion object {
    public fun actionThirdFragmentToSecondFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_thirdFragment_to_secondFragment)

    public fun actionThirdFragmentToFourthFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_thirdFragment_to_fourthFragment)
  }
}
