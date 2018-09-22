import java.net.URL

object APICall{
    fun getTemp(lat: Double, lng:Double):Double {
        var ural = "http://api.openweathermap.org/data/2.5/weather?lat="
        val ural2 = "&lon="
        val ural3 = "&units=imperial&type=accurate&mode=xml&APPID=66bf8bca4952f3cb9d0b08273919dc35"
        ural = ural + lat.toString() + ural2 + lng.toString() + ural3

        var temp = "70.0"

        val result = URL(ural).readText()

        var output = result.substring(result.indexOf("temperature value"), result.indexOf("temperature value") + 24)
        output = output.substring(output.indexOf("\""), output.indexOf("\"", output.indexOf("\"") + 1) + 1)

        temp = output.substring(1, output.length - 1)

        return temp.toDouble()
    }
}