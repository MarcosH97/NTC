package com.mahostudios.netc

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout


class Usage : Fragment() {

    lateinit var info_btn : ImageButton
    lateinit var telegram0 : LinearLayout
    lateinit var telegram1 : LinearLayout
    lateinit var img0 : ImageButton
    lateinit var img1 : ImageButton
    lateinit var img2 : ImageButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_usage2, container, false)
        info_btn = v.findViewById(R.id.info_btn)
        info_btn.setOnClickListener{
            val diag = Dialog(requireContext())
            diag.setContentView(R.layout.info_dialog)
            diag.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            diag.setCancelable(true)
            diag.create()
            diag.show()
        }
        telegram0 = v.findViewById(R.id.telegram_channel)
        telegram1 = v.findViewById(R.id.telegram_group)
        telegram0.setOnClickListener{
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/maho_studios"))
            startActivity(telegram)
        }
        telegram1.setOnClickListener{
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/mahocomments"))
            startActivity(telegram)
        }

        img0 = v.findViewById(R.id.img0)
        img0.setOnClickListener{
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apklis.cu/application/com.mahostudios.sitioslibres"))
            startActivity(telegram)
        }
        img1 = v.findViewById(R.id.img1)
        img1.setOnClickListener{
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apklis.cu/application/com.mahostudios.sitioscu"))
            startActivity(telegram)
        }
        img2 = v.findViewById(R.id.img2)
        img2.setOnClickListener{
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apklis.cu/application/com.mahostudios.compracubapro"))
            startActivity(telegram)
        }
        return v
    }


}