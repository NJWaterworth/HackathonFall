package w.nick.safecar

/*object DogOrChild(){
    private var geoXPos: Double = 0.0
    private var geoYPos: Double = 0.0
    private var timeStart: Double = 0.0
    private var timeElapsed: Double = 0.0
    private lateinit var name: String
    private var age: Double = 0.0
    private var mobility: Double = 0.0
    private var threshold: Double = 150.0 //150 ft away
    private var angry: Boolean = false

    fun calcMobility(){
        val effectiveAge: Double
        if(age > 6)
            effectiveAge = 6.0
        mobility = effectiveAge/6
    }

    //Checks a variety of bad conditions for DogOrChild
    fun badness(momXGeoPos: Double, momYGeoPos: Double){
        if(eDist(momXGeoPos, momYGeoPos, geoXPos, geoYPos) > threshold || hotness(NetClientGet.getTemp(momXGeoPos,momYGeoPos)))
            angry = true
    }

    //Euclidean distance
    fun eDist(x1: Double, y1: Double, x2: Double, y2: Double){
        return degToFeet(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)))
    }

    //Converts degree to feet
    fun degToFeet(x: Double){
        ft: Double = 0
        ft = x * 365214.6
        return ft
    }

    //Updates entity
    fun update(time: Double, XGeoPos: Double,YGeoPos: Double){
        timeElapsed = time - timeStart;
        badness(XGeoPos,YGeoPos)
    }

    //Checks if too hot
    fun hotness(temp: Double){
        if(temp > 100)
            return true
        else if(temp >=93 && timeElapsed > 5)
            return true
        else if(temp >= 85 && timeElapsed > 10)
            return true
        else if(timer > 20)
            return true
        else
            return false
    }
}*/