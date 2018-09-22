import w.nick.safecar.NetClientGet

<<<<<<< HEAD
class DogOrChild {
    var geoXPos: Double = 0.0
    var geoYPos: Double = 0.0
    var timeStart: Double = 0.0
    var timeElapsed: Double = 0.0
    var name: String = "name"
    var age: Double = 0.0
    var mobility: Double = 0.0
    var threshold: Double = 150.0 //150 ft away
    var angry: Boolean = false

    fun calcMobility(Age: Double) {
        var effectiveAge: Double = 0.0
        if (Age > 6)
=======
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

    fun calcMobility(){
        var effectiveAge: Double = age
        if(age > 6)
>>>>>>> e1fa1de75120c0e264a701d1c7527e1596a0e713
            effectiveAge = 6.0
        mobility = effectiveAge / 6
    }

    //Checks a variety of bad conditions for DogOrChild
<<<<<<< HEAD
    fun badness(momXGeoPos: Double, momYGeoPos: Double) {
        if (eDist(momXGeoPos, momYGeoPos, geoXPos, geoYPos) > threshold || hotness(NetClientGet.getTemp(momXGeoPos, momYGeoPos)))//NetClientGet is Seperate Code
=======
    fun badness(momXGeoPos: Double, momYGeoPos: Double){
        if(eDist(momXGeoPos, momYGeoPos, geoXPos, geoYPos) > threshold || hotness(APICall.getTemp(geoXPos,geoYPos)))
>>>>>>> e1fa1de75120c0e264a701d1c7527e1596a0e713
            angry = true
    }

    //Euclidean distance
<<<<<<< HEAD
    fun eDist(x1: Double, y1: Double, x2: Double, y2: Double) :Double {
        return degToFeet(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))
    }

    //Converts degree to feet
    fun degToFeet(x: Double): Double {
=======
    fun eDist(x1: Double, y1: Double, x2: Double, y2: Double): Double{
        return degToFeet(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)))
    }

    //Converts degree to feet
    fun degToFeet(x: Double): Double{
>>>>>>> e1fa1de75120c0e264a701d1c7527e1596a0e713
        var ft: Double = 0.0
        ft = x * 365214.6
        return ft
    }

    //Updates entity
    fun update(time: Double, XGeoPos: Double, YGeoPos: Double) {
        timeElapsed = time - timeStart;
        badness(XGeoPos, YGeoPos)
    }

    //Checks if too hot
<<<<<<< HEAD
    fun hotness(temp: Double): Boolean {
        return if (temp > 100)
            true
        else if (temp >= 93 && timeElapsed > 5)
            true
        else if (temp >= 85 && timeElapsed > 10)
            true
        else if (timeElapsed > 20)
            true
=======
    fun hotness(temp: Double): Boolean{
        if(temp > 100)
            return true
        else if(temp >=93 && timeElapsed > 5)
            return true
        else if(temp >= 85 && timeElapsed > 10)
            return true
        else if(timeElapsed > 20)
            return true
>>>>>>> e1fa1de75120c0e264a701d1c7527e1596a0e713
        else
            false
    }
}