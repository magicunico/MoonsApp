package com.example.zadanie1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.GregorianCalendar
import java.util.Calendar


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var method=1



    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        pelnieRok.setOnClickListener{
            val intent= Intent(this,years::class.java)
            startActivity(intent)
        }



//        var img=simple(2019,4,20)
        var img=simple(current.year,current.monthValue,current.dayOfMonth)
        var proc=img.toInt()*100/30
        if(proc<=5){
            moonImg.setImageResource(R.drawable.newmoon)
        }else if(proc>=90){
            moonImg.setImageResource(R.drawable.newmoon)
        }else if(proc>46 && proc<54){
            moonImg.setImageResource((R.drawable.fullmoon))
        }
        else if((proc>5 && proc<35)||(proc>65 && proc<90)){
            moonImg.setImageResource(R.drawable.kwadra)
        }else{
            moonImg.setImageResource(R.drawable.partmoon)
        }

        dzis.text= "Dziś: $proc% "
        nastepnaPelnia.text="Następna pełnia: "+simpleNextMoon(current.year,current.monthValue,current.dayOfMonth).format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        poprzedniNow.text="Poprzedni nów: "+simplePrevMoon(current.year,current.monthValue,current.dayOfMonth).format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
//        print(simple(2019,3,21).toString())
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.simpleAlg->{
              if(item.isChecked)
                  item.isChecked=false
              else
                  item.isChecked=true
              method=1
              var proc=simple(current.year,current.monthValue,current.dayOfMonth).toInt()*100/30
//              if(proc>50){
//                  proc=proc-50
//              }
              dzis.text="Dziś: "+proc.toString()+" % "
              nastepnaPelnia.text="Następna pełnia: "+simpleNextMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              poprzedniNow.text="Poprzedni nów: "+simplePrevMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              return true
          }
          R.id.conwayAlg->{
              if(item.isChecked)
                  item.isChecked=false
              else
                  item.isChecked=true
              method=2
              var proc2=conway(current.year,current.monthValue,current.dayOfMonth).toInt()*100/30
//              if(proc2>50){
//                  proc2=proc2-50
//              }
              dzis.text="Dziś: "+proc2.toString()+" % "
              nastepnaPelnia.text="Następna pełnia: "+conwayNextMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              poprzedniNow.text="Poprzedni nów: "+conwayPrevMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              return true
          }
          R.id.trig1Alg->{
              if(item.isChecked)
                  item.isChecked=false
              else
                  item.isChecked=true
              method=3
              var proc3=trig1(current.year,current.monthValue,current.dayOfMonth).toInt()*100/30
//              if(proc3>50){
//                  proc3=proc3-50
//              }
              dzis.text="Dziś: "+proc3.toString()+" % "
              nastepnaPelnia.text="Następna pełnia: "+trig1NextMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              poprzedniNow.text="Poprzedni nów: "+trig1PrevMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              return true
          }
          R.id.trig2Alg->{
              if(item.isChecked)
                  item.isChecked=false
              else
                  item.isChecked=true
              method=4
              var proc4=trig2(current.year,current.monthValue,current.dayOfMonth).toInt()*100/30
//              if(proc4>50){
//                  proc4=proc4-50
//              }
              dzis.text="Dziś: "+proc4.toString()+" % "
              nastepnaPelnia.text="Następna pełnia: "+trig2NextMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              poprzedniNow.text="Poprzedni nów: "+trig2PrevMoon(current.year,current.monthValue,current.dayOfMonth).format(
                  DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
              return true
          }
          else -> return super.onOptionsItemSelected(item)
      }
    }


    fun julday(year: Int, month: Int,day: Int):Double{
        var year=year
        var ja: Double

        if(year<0) {
            year=year+1;
        }
        var jy = year
        var jm = month+1

        if(month<=2){
            jy=jy-1;
            jm=jm+12;
        }
        var jul=Math.floor(365.25*jy)+Math.floor(30.6001 * jm) + day + 1720995;
        if(day+31*(month+12*year)>=(15+31*(10+12*1582))){
            ja=Math.floor(0.01*jy)
            jul=jul+2-ja+Math.floor(0.25*ja);
        }
        return jul;

    }


    fun GetFrac(fr:Double):Double{
        return (fr-Math.floor(fr));
    }

    fun simple(year: Int, month: Int, day: Int): Double {
        val lp = 2551443


        val now : Calendar = GregorianCalendar(year, month!! - 1, day!!, 20, 35, 0)
        val newmoon: Calendar = GregorianCalendar(1970, 0, 7, 20, 35, 0)
        val phase : Long
        if(year>=1970){
             phase = (now.timeInMillis-newmoon.timeInMillis) / 1000 % lp}
        else{
            phase=(newmoon.timeInMillis-now.timeInMillis) / 1000 % lp
        }
        val xx=Math.floor((phase / (24 * 3600)).toDouble()) + 1
        return xx
    }

    fun conway(year: Int, month: Int, day: Int) : Double {
      var r = year.toDouble() % 100
        r=r%19
        if(r>9){
            r=r-19
        }
        r=((r*11)%30)+month+day
        if(month<3){
            r=r+2
        }
        if(year<2000){
            r=r-4
        }
        else{
            r= r-8.3
        }
        r= Math.floor(r+0.5)%30
        if(r<0){
            return r+30
        }
        else{
            return r
        }


    }

    fun trig1(year:Int,month:Int,day:Int):Double{
        var oldJ: Double
        oldJ = 0.0
        var thisJD = julday(year,month,day)
        var degToRad = 3.14159265 / 180
        var K0 = Math.floor((year-1900)*12.3685)
        var T = (year-1899.5) / 100;
        var T2 = T*T
        var T3 = T*T*T
        var J0 = 2415020 + 29*K0
        var F0 = 0.0001178*T2 - 0.000000155*T3 + (0.75933 + 0.53058868*K0) - (0.000837*T + 0.000335*T2)
        var M0 = 360*(GetFrac(K0*0.08084821133)) + 359.2242 - 0.0000333*T2 - 0.00000347*T3
        var M1 = 360*(GetFrac(K0*0.07171366128)) + 306.0253 + 0.0107306*T2 + 0.00001236*T3
        var B1 = 360*(GetFrac(K0*0.08519585128)) + 21.2964 - (0.0016528*T2) - (0.00000239*T3)
        var phase = 0
        var jday = 0.0
        while(jday<thisJD){
            var F = F0 + 1.530588*phase
            var M5 = (M0 + phase*29.10535608)*degToRad
            var M6 = (M1 + phase*385.81691806)*degToRad
            var B6 = (B1 + phase*390.67050646)*degToRad
            F -= 0.4068*Math.sin(M6) + (0.1734 - 0.000393*T)*Math.sin(M5)
            F += 0.0161*Math.sin(2*M6) + 0.0104*Math.sin(2*B6)
            F -= 0.0074*Math.sin(M5 - M6) - 0.0051*Math.sin(M5 + M6)
            F += 0.0021*Math.sin(2*M5) + 0.0010*Math.sin(2*B6-M6)
            F += 0.5 / 1440;
            oldJ=jday
            jday = J0 + 28*phase + Math.floor(F)
            phase++
        }
        return (thisJD-oldJ)%30;
    }

    fun trig2(year: Int,month: Int,day: Int) : Double{
        var n=Math.floor(12.37*(year-1900+((1.0*month-0.5)/12.0)))
        var RAD=3.14159265/180.0;
        var t = n/1236.85;
        var t2=t*t;
        var ass = 359.2242 + 29.105356 * n;
        var am = 306.0253 + 385.816918 * n + 0.010730 * t2;
        var xtra = 0.75933 + 1.53058868 * n + ((1.178e-4) - (1.55e-7) * t) * t2;
        xtra += (0.1734 - 3.93e-4 * t) * Math.sin(RAD * ass) - 0.4068 * Math.sin(RAD * am);
        var i:Double;
        if(xtra>0.0){
            i=Math.floor(xtra);
        }
        else {
            i = Math.ceil(xtra - 1.0);
        }
        var j1 = julday(year,month,day);
        var jd = (2415020 + 28 * n) + i;
        return (j1-jd + 30)%30;
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun simpleNextMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var nextDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=simple(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        while(res != 15.0){
            nextDate=nextDate.plusDays(1)
            res=simple(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        }
        return nextDate

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun simplePrevMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var prevDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=simple(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        while(res != 1.0){
            prevDate=prevDate.minusDays(1)
            res=simple(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        }
        return prevDate

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun conwayNextMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var nextDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=conway(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        while(res != 15.0){
            nextDate=nextDate.plusDays(1)
            res=conway(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        }
        return nextDate

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun conwayPrevMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var prevDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=conway(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        while(res != 1.0){
            prevDate=prevDate.minusDays(1)
            res=conway(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        }
        return prevDate

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun trig1NextMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var nextDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=trig1(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        while(res != 15.0){
            nextDate=nextDate.plusDays(1)
            res=trig1(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        }
        return nextDate

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun trig1PrevMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var prevDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=trig1(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        while(res != 1.0){
            prevDate=prevDate.minusDays(1)
            res=trig1(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        }
        return prevDate

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun trig2NextMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var nextDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=trig2(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        while(res != 15.0){
            nextDate=nextDate.plusDays(1)
            res=trig2(nextDate.year,nextDate.monthValue,nextDate.dayOfMonth)
        }
        return nextDate

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun trig2PrevMoon(year:Int, month: Int, day: Int):LocalDateTime{
        var prevDate=LocalDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day)
        var res : Double
        res=trig2(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        while(res != 1.0){
            prevDate=prevDate.minusDays(1)
            res=trig2(prevDate.year,prevDate.monthValue,prevDate.dayOfMonth)
        }
        return prevDate

    }



}
