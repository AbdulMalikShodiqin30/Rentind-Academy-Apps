package com.malik.rentindacademy.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.malik.rentindacademy.data.ClassRepository
import com.malik.rentindacademy.ui.ClassViewModel
import com.malik.rentindacademy.ui.ViewModelFactory
import com.malik.rentindacademy.ui.components.DetailRow
import com.malik.rentindacademy.ui.components.DetailSection
import com.malik.rentindacademy.ui.theme.Purple
import com.malik.rentindacademy.ui.theme.RentindAcademyTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    classId: Int,
    viewModel: ClassViewModel = viewModel(factory = ViewModelFactory(ClassRepository())),
) {
    val detailClass = viewModel.getDetailFood(classId)
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        val str = detailClass.registrationLink
        val startIndex = str.indexOf("")
        val endIndex = str.lastIndex
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex + 1
        )

        addStringAnnotation(
            tag = "URL",
            annotation = detailClass.registrationLink,
            start = startIndex,
            end = endIndex
        )
    }
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ),
    ) {
        Box(
            modifier = Modifier
                .size(width = Dp.Infinity, height = 400.dp)
        ) {
            Image(
                painter = painterResource(id = detailClass.image),
                contentDescription = detailClass.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            ),
                            startY = 300f,
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 40.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = detailClass.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-24).dp),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                DetailRow(
                    modifier = Modifier.padding(bottom = 24.dp),
                    price = detailClass.price,
                    duration = "${detailClass.duration} jam",
                    session = "${detailClass.sessions} sesi"
                )
                DetailSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = "Description",
                    content = detailClass.description
                )
                DetailSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = "Mentor",
                    content = detailClass.mentor
                )
                DetailSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = "Registrasi",
                    content = detailClass.registrationTime,
                )
                DetailSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = "Mulai Kelas",
                    content = detailClass.startTime,
                )
                Text(
                    text = "Link Pendaftaran",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Purple
                )
                ClickableText(text = annotatedLinkString, onClick = {
                    annotatedLinkString
                        .getStringAnnotations("URL", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RentindAcademyTheme {
        DetailScreen(
            classId = 0,
        )
    }
}