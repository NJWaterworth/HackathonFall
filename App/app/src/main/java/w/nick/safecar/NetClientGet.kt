package w.nick.safecar

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object NetClientGet {

    fun main(args: Array<String>) {
        System.out.println("Temperature: " + getTemp(36.08, -94.17))
    }

    // http://localhost:8080/RESTfulExample/json/product/get
    fun getTemp(lat: Double, lng: Double): Double {
        var ural = "http://api.openweathermap.org/data/2.5/weather?lat="
        val ural2 = "&lon="
        val ural3 = "&units=imperial&type=accurate&mode=xml&APPID=66bf8bca4952f3cb9d0b08273919dc35"
        ural = ural + lat.toString() + ural2 + lng.toString() + ural3

        var temp = "70.0"
        try {

            val url = URL(ural)
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestMethod("GET")
            conn.setRequestProperty("Accept", "application/json")

            if (conn.getResponseCode() !== 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.getResponseCode())
            }

            val br = BufferedReader(InputStreamReader(
                    conn.getInputStream()))

            var outline: String = ""
            var output = ""
            while (outline != null) {
                outline = br.readLine()
                output += outline
            }
            output = output.substring(output.indexOf("temperature value"), output.indexOf("temperature value") + 24)
            output = output.substring(output.indexOf("\""), output.indexOf("\"", output.indexOf("\"") + 1) + 1)

            temp = output.substring(1, output.length - 1)

            conn.disconnect()

        } catch (e: MalformedURLException) {

            e.printStackTrace()

        } catch (e: IOException) {

            e.printStackTrace()

        }

        return temp.toDouble()
    }

}