import java.net.URL

object APICall{
    fun getTemp(lat: Double, lng:Double):Double {
        var ural = "http://api.openweathermap.org/data/2.5/weather?lat="
        val ural2 = "&lon="
        val ural3 = "&units=imperial&type=accurate&mode=xml&APPID=66bf8bca4952f3cb9d0b08273919dc35"
        ural = ural + lat.toString() + ural2 + lng.toString() + ural3

        var temp = "70.0"
        temp = ""

        val result = URL(ural).readText()
        //println(result.length)
        println(result)
        println(result.indexOf("temperature value"))
        println(result.indexOf("\""))
        if(result.length != 0)
        {
            var output = result.substring(result.indexOf("temperature value") + 19, result.indexOf("temperature value") + 24)
            /* println("Output: "+output)
            for(int i = 0; i < output.length; i++)
            {
                if(output[i] != "\"")
                    temp.append(output[i])
            }
            /* output = output.substring(output.indexOf("\""), output.indexOf("\"", output.indexOf("\"")))
            println(output) */
            // temp = output.substring(1, output.length) */

            println(output)
            return output.toDouble()
        }
        else {
            return 0.0
        }
    }
}