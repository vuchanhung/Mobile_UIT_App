package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.ChatAdapter;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class Chat extends AppCompatActivity {
    private RecyclerView recyclerViewChat;
    private EditText editTextMessage;
    private ImageButton buttonSendMessage;
    private ChatAdapter chatAdapter;
    private FirebaseFirestore db;
    private CollectionReference messagesCollection;
    private ListenerRegistration messagesListenerRegistration;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");
        String idGroupchat = getIntent().getStringExtra("idGroupchat");

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        // Initialize RecyclerView
        chatAdapter = new ChatAdapter();  // Update with your ChatAdapter implementation
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        db = FirebaseFirestore.getInstance();
        messagesCollection = db.collection("GroupChat").document(idGroupchat).collection("messages");
        setupFirestoreListener(idGroupchat);

        // Kiểm tra nếu subcollection "messages" chưa tồn tại, thì tạo mới
        checkIfMessagesCollectionExists(idGroupchat);

        // Set up click listener for the send button
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(mssv);
            }
        });

    }

    private void setupFirestoreListener(String idGroupchat) {
        messagesListenerRegistration = messagesCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("ChatActivity", "Listen failed.", e);
                    return;
                }

                if (queryDocumentSnapshots != null) {
                    List<DocumentSnapshot> newMessages = queryDocumentSnapshots.getDocuments();
                    chatAdapter.setMessages(newMessages);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo huỷ đăng ký listener khi Activity bị huỷ
        if (messagesListenerRegistration != null) {
            messagesListenerRegistration.remove();
        }
    }

    private void checkIfMessagesCollectionExists(String idGroupchat) {
        db.collection("GroupChat").document(idGroupchat).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // Kiểm tra xem subcollection "messages" đã tồn tại chưa
                                if (!document.contains("messages")) {
                                    // Nếu chưa tồn tại, tạo mới subcollection "messages"
                                    createMessagesCollection(idGroupchat);
                                }
                            }
                        } else {
                            Log.e("ChatActivity", "Error checking messages collection: " + task.getException());
                        }
                    }
                });
    }

    private void createMessagesCollection(String idGroupchat) {
        Map<String, Object> data = new HashMap<>();
        data.put("messages", "");  // Thêm một trường tạm để tạo subcollection

        db.collection("GroupChat").document(idGroupchat).set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("ChatActivity", "Messages collection created successfully");
                        } else {
                            Log.e("ChatActivity", "Error creating messages collection: " + task.getException());
                        }
                    }
                });
    }

    private void sendMessage(String mssv) {
        String messageContent = editTextMessage.getText().toString().trim();

        // Check if the message content is not empty
        if (!messageContent.isEmpty()) {
            // Create a Map to represent the message data
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("senderId", mssv);
            messageData.put("content", messageContent);
            messageData.put("timestamp", System.currentTimeMillis());

            // Add the message to the Firestore collection
            messagesCollection.add(messageData)
                    .addOnSuccessListener(documentReference -> {
                        Log.d("ChatActivity", "Message sent successfully");
                        // Clear the editText after sending the message
                        editTextMessage.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Log.e("ChatActivity", "Error sending message: " + e.getMessage());
                    });
        }
    }
}
