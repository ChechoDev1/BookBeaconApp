package com.example.projectbookbeaconapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class MainFragmentDirections private constructor() {
  public companion object {
    public fun actionFirstFragmentToSecondFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_firstFragment_to_secondFragment)
  }
}
