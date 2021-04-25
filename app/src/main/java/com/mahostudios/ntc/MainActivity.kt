package com.mahostudios.ntc


import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahostudios.ntc.fragments.MainFragment
import com.mahostudios.ntc.fragments.UsageFragment
import cu.uci.apklischeckpayment.Verify

class MainActivity : AppCompatActivity() {

    private val PREF_NAME = "firstime"
    private val bundle : Bundle = Bundle()
    private lateinit var preferences : SharedPreferences

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navCont = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navCont)

        bottomNavigationView.selectedItemId = R.id.table


        if(!BuildConfig.DEBUG){
            checkPaid()
        }

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun DialogMaker(case : Int){
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        var diag : androidx.appcompat.app.AlertDialog
        when(case){
            0 -> {
                dialog.setTitle("Actualizaciones")
                        .setMessage("No se encontraron actualizacinoes")
                        .setPositiveButton("Aceptar"){_,_->

                        }
            }
            1 -> {
                var pos : Int = 0
                dialog.setTitle("Apklis no encontrada")
                        .setMessage("Apklis no se encuentra instalado. Por favor descargue e instale la app para comprobar la compra")
                        .setPositiveButton("Cerrar"){_,_->
                            finishAndRemoveTask()
                        }
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setCancelable(false)
            }
            2 -> {
                dialog.setTitle("Usuario no encontrado")
                        .setMessage("No se ha autenticado en la aplicación de Apklis. Para evitar problemas con la compra y actualizaciones autentíquece lo antes posible")
                        .setPositiveButton("Abrir APKLIS"){_,_->
                            val intent = packageManager.getLaunchIntentForPackage("cu.uci.android.apklis")
                            val chooser = Intent.createChooser(intent, "Launch Apklis")
                            startActivity(chooser)
                        }
                        .setNeutralButton("Cerrar"){_,_->
                            finishAndRemoveTask()
                        }
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setCancelable(false)
            }
            3 -> {
                dialog.setTitle("AVISO")
                        .setMessage("La aplicación no ha sido comprada! Se cerrará al salir de este mensaje. \nApoye a los desarrolladores con su compra.")
                        .setNeutralButton("Abrir APKLIS"){_,_->
                            val intent = packageManager.getLaunchIntentForPackage("cu.uci.android.apklis")
                            val chooser = Intent.createChooser(intent, "Launch Apklis")
                            startActivity(chooser)
                        }
                        .setPositiveButton("Cerrar"){_,_->
                            finishAndRemoveTask()
                        }
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setCancelable(false)
            }
            4 -> {
                dialog.setTitle("BIENVENIDO")
                        .setMessage("Gracias por comprar nuestra app. Esperamos la disfrute. \n Ante cualquier problema no dude en contactar con los desarrolladores")
                        .setPositiveButton("Aceptar", null)
                        .setCancelable(true)
            }
            5 -> {

            }
        }
        diag = dialog.create()
        diag.show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun checkPaid(){
        val response = Verify.isPurchased(this, "${applicationContext.packageName}")
//        val res = Verify.Companion.isPurchased(this, "${applicationContext.packageName}")
        var user : String?
        var ispaid : Boolean = false
        if(!checkApklis()){
            DialogMaker(1)
        }
        else {
            user = response.second
            if (user.equals(null) || user.equals("")) {
                DialogMaker(2)
            }else{
                ispaid = response.first
                //@TODO:Wildcard
                val vips = listOf("marielainfante", "MarcosH", "epsilon", "chopper-kun", "BLNrt", "AntoineAnigma","Winter_Wolf","winter_wolf","Masi","masi")
                if (vips.contains(user) || ispaid){
                    if(firstTime()){
                        val ed: SharedPreferences.Editor = preferences.edit()
                        ed.putBoolean("firstime", false)
                        ed.commit()
                        DialogMaker(4)
                    }
                }else{
                    DialogMaker(3)
                }
            }
        }
    }
    fun checkApklis(): Boolean{
        val packageManager = applicationContext.packageManager
        try{
            packageManager.getPackageInfo("cu.uci.android.apklis", PackageManager.GET_ACTIVITIES)
            return true
        }catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
            return false
        }
    }
    fun firstTime(): Boolean{
        val ran : Boolean = preferences.getBoolean("firstime", true)
        return ran
    }




}