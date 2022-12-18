package com.tastyrhino

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align

class GameOverWindow(uiStage: Stage, font: BitmapFont) {

    fun show(score: Int) {
        window.isVisible = true
        gameOverLabel.isVisible = true
        this.score.isVisible = true
        this.score.setText("Score: $score")
        pressToContinue.isVisible = true
    }

    fun hide() {
        window.isVisible = false
        gameOverLabel.isVisible = false
        score.isVisible = false
        pressToContinue.isVisible = false
    }

    fun clicked(function: () -> Unit) {
        val clickListener = object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                function()
                super.clicked(event, x, y)
            }
        }
        window.addListener(clickListener)
        gameOverLabel.addListener(clickListener)
        score.addListener(clickListener)
        pressToContinue.addListener(clickListener)
    }

    private var window = Image(Texture("gameover.png")).apply {
        setSize(684f, 363f)
        setOrigin(Align.center)
        setPosition(1920f / 2f - 684f / 2f, 1080f / 2f - 363f / 2f)
        uiStage.addActor(this)
    }
    private var gameOverLabel = Label(
        "Game Over",
        Label.LabelStyle(font, Color.WHITE)
    ).apply {
        uiStage.addActor(this)
        setPosition(1920f / 2f, 600f)
    }
    private var score = Label(
        "Score: ",
        Label.LabelStyle(font, Color.WHITE)
    ).apply {
        uiStage.addActor(this)
        setPosition(1920f / 2f - 10f, 520f)
    }
    private var pressToContinue = Label(
        "Press to continue",
        Label.LabelStyle(font, Color.WHITE)
    ).apply {
        uiStage.addActor(this)
        setPosition(1920f / 2f - 90f, 440f)
    }
}