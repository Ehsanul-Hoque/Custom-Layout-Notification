package com.coderpanda.customlayoutnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.coderpanda.customlayoutnotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Unicons by <a href="https://iconscout.com/">Iconscout</a>

    private lateinit var binding: ActivityMainBinding
    private val channelId = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        createNotificationChannel()
        initListeners()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "default"
            val descriptionText = "Default channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun initListeners() {
        binding.buttonShowDefaultNotification.setOnClickListener {
            showNotification(
                custom = false
            )
        }

        binding.buttonShowCustomCollapsedNotification.setOnClickListener {
            showNotification(
                custom = true,
                collapsible = true,
                expandable = false
            )
        }

        binding.buttonShowCustomExpandedNotification.setOnClickListener {
            showNotification(
                custom = true,
                collapsible = false,
                expandable = true
            )
        }

        binding.buttonShowCustomResizableNotification.setOnClickListener {
            showNotification(
                custom = true,
                collapsible = true,
                expandable = true
            )
        }
    }

    private fun showNotification(custom: Boolean, collapsible: Boolean = true,
                                 expandable: Boolean = true) {
        val title = "Hi Ruby!"
        val content = "How are you? Hope you are doing well. I miss you. I miss you a lot." +
                " I often see dreams about you. Come soon."

        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (custom) {
            val customNotificationView: RemoteViews = new RemoteViews(packageName, )
        } else {
            builder = builder
                .setContentTitle(title)
                .setContentText(content)
        }

        NotificationManagerCompat.from(this).notify(0, builder.build())
    }
}