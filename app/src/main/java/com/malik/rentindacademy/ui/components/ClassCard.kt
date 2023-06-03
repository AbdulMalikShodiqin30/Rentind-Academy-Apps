package com.malik.rentindacademy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malik.rentindacademy.R
import com.malik.rentindacademy.model.FakeClassDataSource.dummyClass
import com.malik.rentindacademy.ui.theme.RentindAcademyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassCard(
    modifier: Modifier = Modifier,
    id: Int,
    classImage: Int,
    className: String,
    navigateToDetail: (Int) -> Unit,
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(350.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            8.dp,
        ),
        onClick = {
            navigateToDetail(id)
        }
    ) {
        Box {
            Image(
                painter = painterResource(id = classImage),
                contentDescription = null,
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
                            startY = 350f,
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = className,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClassCardPreview() {
    RentindAcademyTheme {
        ClassCard(
            id = 0,
            classImage = R.drawable.mobile,
            className = dummyClass[0].name,
            navigateToDetail = {}
        )
    }
}