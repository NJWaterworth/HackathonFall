package w.nick.safecar

object DogOrChild{
    private var geoXPos: Double = 0.0
    private var geoYPos: Double = 0.0
    private var timeStart: Double = 0.0
    private var timeElapsed: Double = 0.0
    private lateinit var name: String
    private var age: Double = 0.0
    private var mobility: Double = 0.0
    private var threshold: Double = 150.0 //150 ft away
    private var angry: Boolean = false
    private val temp: Double = 0.0

    fun calcMobility(){
        var effectiveAge: Double = age
        if(age > 6)
            effectiveAge = 6.0
        mobility = effectiveAge/6
    }

    //Checks a variety of bad conditions for DogOrChild
    fun badness(momXGeoPos: Double, momYGeoPos: Double){
        if(eDist(momXGeoPos, momYGeoPos, geoXPos, geoYPos) > threshold || hotness(temp))
            angry = true
    }

    //Euclidean distance
    fun eDist(x1: Double, y1: Double, x2: Double, y2: Double): Double{
        return degToFeet(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)))
    }

    fun init(xPos: Double, yPos: Double){
        geoXPos = xPos
        geoYPos = yPos
        temp = APICall.getTemp(geoXPos,geoYPos)
    }

    //Converts degree to feet
    fun degToFeet(x: Double): Double{
        var ft: Double = 0.0
        ft = x * 365214.6
        return ft
    }

    //Updates entity
    fun update(time: Double, XGeoPos: Double,YGeoPos: Double){
        timeElapsed = time - timeStart;
        badness(XGeoPos,YGeoPos)
    }

    //Checks if too hot
    fun hotness(temp: Double): Boolean{
        if(temp > 100)
            return true
        else if(temp >=93 && timeElapsed > 5)
            return true
        else if(temp >= 85 && timeElapsed > 10)
            return true
        else if(timeElapsed > 20)
            return true
        else
            return false
    }
}