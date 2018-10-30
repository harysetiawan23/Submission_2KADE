package com.example.harry.submission_2kade.ComponentUI

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.harry.submission_2kade.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FixturesItemUI : AnkoComponent<ViewGroup> {

    companion object {
        val fixturesDate = 1
        val homeTeamName = 3
        val awayTeamName = 2
        val fixturesScore = 4
        val fixturesContainer = 5
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                margin = dip(6)
                cardElevation = dip(2).toFloat()
            }
            verticalLayout {
                id = fixturesContainer
                orientation = LinearLayout.VERTICAL

                textView {
                    id = fixturesDate
                    text = "Hallo"
                    textColor = Color.RED
                    gravity = Gravity.CENTER
                }.lparams(width = matchParent) {}


                view {
                }.lparams(width = matchParent) {
                    height = dip(0.5.toFloat())
                    leftMargin = dip(12)
                    rightMargin = dip(12)
                    topMargin = dip(12)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER


                    textView {
                        id = homeTeamName
                        textSize = dip(5).toFloat()
                        gravity = Gravity.LEFT
                    }.lparams(width = 0) {
                        weight = 1.toFloat()
                        height = wrapContent
                        leftPadding = dip(6)
                        rightPadding = dip(6)
                    }

                    textView {
                        id = fixturesScore
                        textColor = Color.RED
                        textSize = dip(6).toFloat()
                        gravity = Gravity.CENTER
                    }.lparams(width = wrapContent) {
                        height = wrapContent
                    }

                    textView {
                        id = awayTeamName
                        gravity = Gravity.RIGHT
                        textSize = dip(5).toFloat()
                    }.lparams(width = 0) {
                        weight = 1.toFloat()
                        height = wrapContent
                        leftPadding = dip(6)
                        rightPadding = dip(6)
                    }


                }.lparams(width = matchParent) {
                    topPadding = dip(12)
                    bottomPadding = dip(12)
                    height = wrapContent
                }

            }.lparams(
                    width = matchParent,
                    height = wrapContent
            ) {
                topMargin = dip(6)
                bottomMargin = dip(6)
                leftMargin = dip(6)
                rightMargin = dip(6)
            }

        }


    }

}