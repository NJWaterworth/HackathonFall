package w.nick.safecar

import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.app.PendingIntent
import android.content.Context


class NotificationAlert
{
    lateinit var ctx: Context

    fun createNotificationStuff(con: Context){
        ctx=con
        val name = "SafeCar"
        val description = "Check on your child!"    //This is the message the user sees
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("SafeCarChannel", name, importance)
        channel.description = description
        channel.enableVibration(true)

        val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager//NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
    }

    fun alert(){
        val intent = Intent(ctx,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(ctx,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder = NotificationCompat.Builder(ctx,"SafeCarChannel")
                .setContentTitle("SafeCar Alert")
                .setContentText("Check on your child!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.resources,R.drawable.ic_launcher))
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)



        val notificationManager = NotificationManagerCompat.from(ctx)

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(10110101, mBuilder.build())
    }
}


