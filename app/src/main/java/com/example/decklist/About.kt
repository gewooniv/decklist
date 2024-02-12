package com.example.decklist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.decklist.data.Contact
import com.example.decklist.data.Person
import java.text.SimpleDateFormat
import java.util.Calendar

@Preview
@Composable
fun About() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp)),
//                        .border(
//                            BorderStroke(1.dp, Color.Black),
//                            RoundedCornerShape(12.dp)
//                        ),
                    painter = rememberAsyncImagePainter(Ivo.picture),
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // personals
                AboutItem(Ivo.name)
                AboutItem(Ivo.job)
                AboutItem(Ivo.age.toString())

                Spacer(modifier = Modifier.padding(10.dp))

                // project description
                AboutItem(Ivo.description)

                Spacer(modifier = Modifier.padding(10.dp))

                // contact information
                AboutItemContact(Ivo.email)
                AboutItemContact(Ivo.linkedin)
                AboutItemContact(Ivo.github)

                Spacer(modifier = Modifier.padding(10.dp))

                // copyright
                AboutItem("Copyright " + Calendar.getInstance().get(Calendar.YEAR))
            }

        }
    }
}

@Composable
fun AboutItem(text: String) {
    Text(
        modifier = Modifier.padding(2.dp),
        text = text,
        fontSize = 14.sp
    )
}

@Composable
fun AboutItemContact(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = rememberAsyncImagePainter(contact.logo),
            contentDescription = "Logo"
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(6.dp),
            text = contact.url,
            fontSize = 12.sp
        )
    }
}

val Ivo = Person(
    name = "Ivo Eijgenraam",
    age = getAge("9-10-1986"),
    picture = "https://media.licdn.com/dms/image/D4E03AQFFafena73G2Q/profile-displayphoto-shrink_800_800/0/1703157010451?e=1712793600&v=beta&t=DWt_LQ_n_O0dw5KjOfSh4Cmquta7BsHe9eG58HrNKYI",
    email = Contact(
        "gewooniv@gmail.com",
        "https://www.google.com/gmail/about/static-2.0/images/logo-gmail.png?fingerprint=c2eaf4aae389c3f885e97081bb197b97",
    ),
    linkedin = Contact(
        "linkedin.com/in/ivoeijgenraam",
        "https://media.licdn.com/dms/image/C560BAQHaVYd13rRz3A/company-logo_100_100/0/1638831590218/linkedin_logo?e=1715817600&v=beta&t=eSx2a81kfcHIdAOfIGNeGh1hILVq7avVoxzCgIo6Cs4",
    ),
    github = Contact(
        "github.com/gewooniv",
        "https://media.licdn.com/dms/image/C560BAQFmuLSyL1nlPA/company-logo_100_100/0/1678231359044/github_logo?e=1715817600&v=beta&t=uvMg3pOBHnrjkQ38RVut6e2fRXRPuCBNre3_6a1r-4s",
    ),
    job = "Software Developer",
    description = "This project was created in preparation for a job interview. It was created with no previous knowledge of creating Android apps or writing in Kotlin. I enjoyed creating it in about three days and look forward to working with Android studio in the future."
)

@SuppressLint("SimpleDateFormat")
fun getAge(dobString: String): Int {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    val date = sdf.parse(dobString)

    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()

    if (date != null) {
        dob.time = date
    }

    val year = dob[Calendar.YEAR]
    val month = dob[Calendar.MONTH]
    val day = dob[Calendar.DAY_OF_MONTH]

    dob[year, month + 1] = day

    var age = today[Calendar.YEAR] - dob[Calendar.YEAR]

    if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
        age--
    }

    return age
}