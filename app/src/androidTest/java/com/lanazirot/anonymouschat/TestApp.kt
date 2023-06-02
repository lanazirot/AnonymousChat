package com.lanazirot.anonymouschat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidTest


@HiltAndroidTest
class MyApplication : Application()

//@CustomTestApplication(MyApplication::class)
//interface HiltTestApplication