/*
 * StarDict implementation for Android.
 * Copyright (C) 2022  Bohdan Kolvakh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.acmpo6ou.stardict.utils

import android.content.Context
import android.media.AudioManager
import android.widget.Toast
import splitties.systemservices.audioManager

/**
 * Displays a toast telling the user that volume is off if the volume is off.
 */
fun Context.checkVolume() {
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    if (volumeLevel == 0)
        Toast
            .makeText(this, "Volume is off", Toast.LENGTH_SHORT)
            .show()
}

