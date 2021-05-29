package com.jarvis.tab.model

import android.telephony.cdma.CdmaCellLocation
import androidx.annotation.StringRes

data class ListItemLocal(
    val imgResource  : Int,
    val nameItem:String,
    val originLocation : String,
    val descItem : String

)
