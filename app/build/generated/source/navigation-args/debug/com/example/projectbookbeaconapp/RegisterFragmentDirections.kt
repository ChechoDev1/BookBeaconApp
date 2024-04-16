package com.example.projectbookbeaconapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class RegisterFragmentDirections private constructor() {
  public companion object {
    public fun actionSecondFragmentToThirdFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_secondFragment_to_thirdFragment)

    public fun actionSecondFragmentToFirstFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_secondFragment_to_firstFragment)
  }
}
