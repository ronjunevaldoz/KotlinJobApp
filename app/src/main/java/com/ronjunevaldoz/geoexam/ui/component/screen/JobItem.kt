package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ronjunevaldoz.geoexam.network.data.ActualJob
import com.ronjunevaldoz.geoexam.ui.component.HtmlText

@Composable
fun JobItem(job: ActualJob) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = 2.dp
    ) {

        Column(modifier = Modifier.padding(8.dp)) {
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = job.reference,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text = job.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                val jobStatus = job.job_status
                if (jobStatus != null) {
                    val color = android.graphics.Color.parseColor(jobStatus.color_hex)
                    Text(
                        text = jobStatus.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(Color(color), shape = RoundedCornerShape(4.dp) )
                            .padding(2.dp)
                            .align(Alignment.TopEnd)
                    )
                }
            }
            val desc = job.description.orEmpty()
            if (desc.isEmpty()) {
                Text(text = "No description")
            } else {
                HtmlText(html = job.description.orEmpty())
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            if (job.clients.isEmpty()) {
                Text(text = "No clients")
            } else {
                job.clients.forEach { client ->
                    Text(text = "${client.first_name} ${client.last_name}")
                    Text(text = client.company_name)
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            val jobAddress = job.address
            if (jobAddress != null) {
                Text(text = "${jobAddress.address1}, ${jobAddress.city}, ${ jobAddress.state}, ${jobAddress.postCode}")
            } else {
                Text(text = "No address")
            }
        }
    }
}