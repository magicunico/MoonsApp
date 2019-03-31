package com.example.zadanie1

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_years.*
import kotlinx.android.synthetic.main.content_years.*
import kotlinx.android.synthetic.main.content_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class years : AppCompatActivity() {
    var yearCheck : Int = 1900
    var yearMinus : Int = 0
    var yearPlus : Int = 0
    var method =1
    var tables=ArrayList<TextView>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_years)
        setSupportActionBar(toolbar)

        tables.add(year1)
        tables.add(year2)
        tables.add(year3)
        tables.add(year4)
        tables.add(year5)
        tables.add(year6)
        tables.add(year7)
        tables.add(year8)
        tables.add(year9)
        tables.add(year10)
        tables.add(year11)
        tables.add(year12)


        confirm.setOnClickListener {
            if(enterYear.text.toString().isNullOrEmpty()){
                year1.text="WRONG DATE"
            }else {
                yearCheck = enterYear.text.toString().toInt()
            }
            clearTable()
            countFulls(yearCheck)
        }

        minusYear.setOnClickListener {
            clearTable()
            if(enterYear.text.toString().isNullOrEmpty()){
                year1.text="WRONG DATE"
            }else {
                yearCheck = enterYear.text.toString().toInt()
            }
            yearMinus=yearCheck.minus(1)
            if(yearMinus<1900){
                year1.setText("WYBIERZ ROK POMIĘDZY 1900 A 2200")
            }else{
            enterYear.setText(yearMinus.toString())
            countFulls(yearMinus)}

        }
        addYear.setOnClickListener {
            clearTable()
            if(enterYear.text.toString().isNullOrEmpty()){
                year1.text="WRONG DATE"
            }else {
                yearCheck = enterYear.text.toString().toInt()
            }
            yearPlus=yearCheck.plus(1)
            if(yearPlus>2200){
                year1.setText("WYBIERZ ROK POMIĘDZY 1900 A 2200")
            }else {
                enterYear.setText(yearPlus.toString())
                countFulls(yearPlus)
            }
        }

    }

    fun clearTable(){
        for (index in tables){
            index.text = ""
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        yearCheck=enterYear.text.toString().toInt()
               when(item.itemId){
            R.id.simpleAlg->{
                if(item.isChecked)
                    item.isChecked=false
                else
                    item.isChecked=true
                method=1
                countFulls(yearCheck)
                return true
            }
            R.id.conwayAlg->{
                if(item.isChecked)
                    item.isChecked=false
                else
                    item.isChecked=true
                method=2
                countFulls(yearCheck)
                return true
            }
            R.id.trig1Alg->{
                if(item.isChecked)
                    item.isChecked=false
                else
                    item.isChecked=true
                method=3
                countFulls(yearCheck)

                return true
            }
            R.id.trig2Alg->{
                if(item.isChecked)
                    item.isChecked=false
                else
                    item.isChecked=true
                method=4
                countFulls(yearCheck)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun countFulls(yearCheck : Int){
        var fulls = java.util.ArrayList<String>()

        if(method==1){
            fulls=simpleFulls(yearCheck)
        }else if(method==2){
            fulls=conwayFulls(yearCheck)
        }else if(method==3){
            fulls=trig1Fulls(yearCheck)
        }else if(method==4){
            fulls=trig2Fulls(yearCheck)
        }

        for (index in 0 until 12){
            if(fulls[index].isNullOrEmpty()){
                break
            }
            tables[index].text = (index+1).toString()+". "+fulls[index]
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun simpleFulls(year: Int): ArrayList<String> {
        var fullMoons = ArrayList<String>()
        var indexYear = year
        var fullMoon: LocalDateTime
        var inspectedDate = LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1)
        while (true) {
            // dostan date nastepnego full Moona
            fullMoon = MainActivity().simpleNextMoon(inspectedDate.year, inspectedDate.monthValue, inspectedDate.dayOfMonth)
            if(fullMoon.year == indexYear+1 ){
                break
            }
            // zapisz ja jako string do fullMoons
            fullMoons.add(fullMoon.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            // ustaw ta date jako kolejna do sprawdzenia
            inspectedDate = fullMoon.plusDays(1)
        }
        return fullMoons
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun conwayFulls(year: Int):ArrayList<String>{
        var fullMoons = ArrayList<String>()
        var indexYear = year
        var fullMoon: LocalDateTime
        var inspectedDate = LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1)
        while (true) {
            // dostan date nastepnego full Moona
            fullMoon = MainActivity().conwayNextMoon(inspectedDate.year, inspectedDate.monthValue, inspectedDate.dayOfMonth)
            if(fullMoon.year == indexYear+1 ){
                break
            }
            // zapisz ja jako string do fullMoons
            fullMoons.add(fullMoon.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            // ustaw ta date jako kolejna do sprawdzenia
            inspectedDate = fullMoon.plusDays(1)
        }
        return fullMoons


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun trig1Fulls(year: Int):ArrayList<String>{
        var fullMoons = ArrayList<String>()
        var indexYear = year
        var fullMoon: LocalDateTime
        var inspectedDate = LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1)
        while (true) {
            // dostan date nastepnego full Moona
            fullMoon = MainActivity().trig1NextMoon(inspectedDate.year, inspectedDate.monthValue, inspectedDate.dayOfMonth)
            if(fullMoon.year == indexYear+1 ){
                break
            }
            // zapisz ja jako string do fullMoons
            fullMoons.add(fullMoon.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            // ustaw ta date jako kolejna do sprawdzenia
            inspectedDate = fullMoon.plusDays(1)
        }
        return fullMoons

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun trig2Fulls(year: Int):ArrayList<String>{
        var fullMoons = ArrayList<String>()
        var indexYear = year
        var fullMoon: LocalDateTime
        var inspectedDate = LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1)
        while (true) {
            // dostan date nastepnego full Moona
            fullMoon = MainActivity().trig2NextMoon(inspectedDate.year, inspectedDate.monthValue, inspectedDate.dayOfMonth)
            if(fullMoon.year == indexYear+1 ){
                break
            }
            // zapisz ja jako string do fullMoons
            fullMoons.add(fullMoon.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            // ustaw ta date jako kolejna do sprawdzenia
            inspectedDate = fullMoon.plusDays(1)
        }
        return fullMoons
    }



}
