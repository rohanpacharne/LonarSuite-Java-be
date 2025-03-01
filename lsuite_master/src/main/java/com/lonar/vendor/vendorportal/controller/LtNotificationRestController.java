package com.lonar.vendor.vendorportal.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.auth.oauth2.GoogleCredentials;

@RestController
@RequestMapping(value = "/API/notificationController")
public class LtNotificationRestController {
	
	 // Initialize Firebase with your service account credentials
    public static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = 
            new FileInputStream("D:/Lexa/firebase/lonarsuite-mobile-application-firebase-adminsdk-fbsvc-b771f3037e.json"); // Path to your Firebase JSON credentials

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        FirebaseApp.initializeApp(options);
    }
	
	 // Initialize Firebase once when the application starts
    static {
        try {
        	initializeFirebase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//  Endpoint to send notifications
    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam String token, 
                                   @RequestParam String title, 
                                   @RequestParam String body) {
        try {
            String response = sendNotificationToUser(token, title, body);
            return "Notification sent successfully. Response: " + response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send notification: " + e.getMessage();
        }
    }
    
 // Send notification using FCM
    public static String sendNotificationToUser(String token, String title, String body) throws Exception {
    	
    	 Notification notification = Notification.builder()
    	            .setTitle(title)
    	            .setBody(body)
    	            .build();
    	
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        return response;
    }

}

