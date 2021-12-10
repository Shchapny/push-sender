package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)
/* Отправка уведомления по лайку
    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()
*/
/* Отправка уведомления при создании нового поста*/
    val message = Message.builder()
        .putData("action", "NEW_Post")
        .putData("content", """{
          "userName": "Vasiliy",
          "content": "Спасибо преподавателям, за то что смотрите на мой стиль кода и ставите за это зачёт!"
        }""".trimIndent())
        .setToken(token)
        .build()

/* На случай, если отсутствует action
    val message = Message.builder()
        .putData("action", "NEW")
        .putData("content", """{
          "userName": "Vasiliy",
          "content": "Спасибо преподавателям, за то что смотрите на мой стиль кода и ставите за это зачёт!"
        }""".trimIndent())
        .setToken(token)
        .build()

 */
    FirebaseMessaging.getInstance().send(message)
}