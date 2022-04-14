package com.example.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    val conversationList= listOf(
        Message("Abhi","The Quick Brown Fox"),
        Message("Dinesh","The Quick Brown Fox"),
        Message("Ganesh","The Quick Brown Fox"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Column {
                    MessageCard(msg = Message("Jetpack Compose ", "single Card"))
                    Conversation(messages = conversationList)
                }
            }

        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.test),
                contentDescription = "test picture",
                // Set image size to 40 dp
                modifier = Modifier
                    .size(40.dp)
                    //Clip image to be shaped as circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

            )
            //Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.body2
                )
                //Add Vertical space between the author and the message
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.padding(all = 4.dp)
                )

            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun MessageCardPreview() {
        ComposeTheme {
            MessageCard(
                msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
           items(messages){ message->
               MessageCard(msg = message)

           }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation(){
        ComposeTheme {
            Conversation(messages = conversationList)
        }
    }
}
