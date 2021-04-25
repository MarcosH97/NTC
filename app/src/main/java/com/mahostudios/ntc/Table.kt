package com.mahostudios.ntc

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import kotlin.math.roundToInt
import com.mahostudios.ntc.R.array.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Table.newInstance] factory method to
 * create an instance of this fragment.
 */
class Table : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TableAdapter
    var nameList= ArrayList<String>()
    var speedList= ArrayList<String>()
    var timeList= ArrayList<String>()

    private var selected_cap : Int = 0
    private var selected_speed : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val capacities = resources.getStringArray(capacities)
        val speeds = resources.getStringArray(speeds)
        val con_speed = resources.getStringArray(con_speed)
        var lista = ArrayList<String>()
        val context : Context = this.requireContext()
        var v : View = inflater.inflate(R.layout.fragment_table2, container, false)
        // Inflate the layout for this fragment
        setRecycler(v)

        LowerSpinner(context, v, speeds)
        CalcButton(context, v, capacities)
        val button : Button = v.findViewById(R.id.calc_button)
        var texto : String
        var text : String
        var spd : Double = 1.0
        var cap : Double = 0.0

        button.setOnClickListener{
            texto = v.findViewById<TextView>(R.id.filesize_text).text.toString()
            text = v.findViewById<TextView>(R.id.speed_text).text.toString()
            if (!texto.isBlank()) {
                timeList.clear()
                var txt: TextView = v.findViewById(R.id.custom_speed)
                cap = texto.toDouble()
                when(selected_cap){
                    0 -> cap*=1

                    1 -> cap*=1024

                    2 -> cap*=1048576

                    else-> Toast.makeText(requireContext(),"Didnt work", Toast.LENGTH_SHORT)
                }
                if (!text.isBlank()) {
                    spd = text.toDouble()
                    when(selected_speed){
                        0 -> spd*=1

                        1 -> spd*=1000

                        2 -> spd*=1000000

                        else-> Toast.makeText(requireContext(),"Didnt work", Toast.LENGTH_SHORT)
                    }
                    Toast.makeText(requireContext(), ""+spd+"", Toast.LENGTH_SHORT).show()
                    txt.text = CalculateTime(spd, cap)
                } else spd = 1.0



                for (i in con_speed.indices){
                    var speed : Double = con_speed[i].toDouble()
                    lista.add(CalculateTime(speed,cap))
                }
                timeList = lista

                setRecycler(v)
            }else Toast.makeText(context, "Introduzca una capacidad", Toast.LENGTH_SHORT).show()
//            timeList.clear()
        }
        return v
    }

    private fun CalculateTime(speed : Double, size : Double): String{
        var h : Int = 0
        var m : Int = 0
        var s : Int

        s = (size/(speed/8)).roundToInt()
        while (s > 60){
            if(m>60){
                h++
                m -= 60
            }
            m++
            s -= 60
        }
        if(h<10 && m<10 && s<10){
            return ("0$h:0$m:0$s")
        } else if(h<10 && m<10 && s>=10){
            return ("0$h:0$m:$s")
        } else if(h<10 && m>=10 && s<10){
            return ("0$h:$m:0$s")
        } else if(h<10 && m>=10 && s>=10){
            return ("0$h:$m:$s")
        } else if(h>=10 && m<10 && s<10){
            return ("$h:0$m:0$s")
        } else if(h>=10 && m<10 && s>=10){
            return ("$h:0$m:$s")
        } else if(h>=10 && m>=10 && s<10){
            return ("$h:$m:0$s")
        }else {
            return ("$h:$m:$s")
        }
    }
    fun setRecycler(v : View){
        recyclerView = v.findViewById(R.id.tb_recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        getList()
        adapter = TableAdapter(this.requireContext(), timeList,nameList,speedList)
        recyclerView.adapter = adapter
    }
    fun getList(){
        val names = resources.getStringArray(R.array.c_type)
        val speeds = resources.getStringArray(R.array.c_speed)
        for (i in names.indices) {
            nameList.add(names[i])
            speedList.add(speeds[i])
//            if(timeList.size>1)
////                Toast.makeText(requireContext(), timeList.size.toString(), Toast.LENGTH_SHORT).show()
////                timeList.add(timeList[i])
//            else
            timeList.add("")
        }
    }

    fun CalcButton(context: Context, v: View, capacities: Array<String>){
        val spinner : Spinner = v.findViewById(R.id.capacity_spinner)
        val adapter = ArrayAdapter(context, R.layout.spinner_item, capacities)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_cap = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    fun LowerSpinner(context: Context, v: View, speeds: Array<String>){
        val spin : Spinner = v.findViewById(R.id.speed_spinner)
        val sadapter = ArrayAdapter(context, R.layout.spinner_item, speeds)
        spin.adapter = sadapter
        spin.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_speed = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}