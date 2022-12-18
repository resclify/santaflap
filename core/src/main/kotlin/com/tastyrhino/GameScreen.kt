package com.tastyrhino

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import kotlin.math.max as kotlinMathMax

class GameScreen : KtxScreen {

    enum class State {
        STARTING_PHASE,
        RUNNING,
        GAME_OVER,
    }

    private val worldWidth = 100f

    private val uiStage = Stage(ExtendViewport(1920f, 1080f))
    private val worldStage = Stage(ExtendViewport(worldWidth, worldWidth * 1080f / 1920f))

    private val world = World(Vector2(0f, 0f), false)
    private var enableDebugRenderer = false
    private val debugRenderer = Box2DDebugRenderer()

    private val santa = Santa(world).apply {
        worldStage.addActor(this)
    }
    private val level = Level(world).apply {
        worldStage.addActor(this)
    }

    private var scoreTimer = 0f
    private var score = 0
    private var best = 0

    private val font = BitmapFont(Gdx.files.internal("gomarice_no_continue.fnt"))
    private val scoreLabel = Label("Score: 0000", Label.LabelStyle(font, Color.WHITE)).apply {
        uiStage.addActor(this)
        setPosition(1920 - 700f, 1080 - 50f)
    }

    private val introductionHint = Label(
        "Santa Claus has ended up on the wrong side of the world.\n" +
                "Help Santa get out of this hellhole.\n" +
                "Don't fly too high or you will be shot.\n\n" +
                "Press and hold to fly upwards.\n" +
                "Click to start.",
        Label.LabelStyle(font, Color.WHITE)
    ).apply {
        uiStage.addActor(this)
        setPosition(100f, 320f)
    }

    private val gameOverWindow = GameOverWindow(uiStage, font)
    private var gameState = State.STARTING_PHASE

    private val music = Gdx.audio.newMusic(Gdx.files.internal("Rudolph2.ogg")).apply {
        isLooping = true
        volume = 0.2f
        play()
    }
    private val sound = Gdx.audio.newSound(Gdx.files.internal("snap.ogg"))

    init {
        Gdx.input.inputProcessor = InputMultiplexer(uiStage, worldStage)
        world.setContactListener(object : ContactListener {
            override fun beginContact(contact: Contact?) {
                ktx.log.debug { "Game Over" }
                sound.play()
                gameOverWindow.show(score)
                gameState = State.GAME_OVER
            }

            override fun endContact(contact: Contact?) {
            }

            override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
            }

            override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
            }
        })

        gameOverWindow.clicked {
            resetGame()
        }

        resetGame()
    }

    private fun resetGame() {
        gameOverWindow.hide()
        gameState = State.STARTING_PHASE
        santa.resetPosition()
        level.resetLevel()
        setScore(0)
        introductionHint.isVisible = true
    }

    private fun setScore(newScore: Int) {
        score = newScore
        best = kotlinMathMax(score, best)
        scoreLabel.setText("Score: " + "%05d".format(score) + "   Best: " + "%05d".format(best))
    }

    var lastTouched = false
    private fun update(delta: Float) {
        worldStage.act(delta)
        uiStage.act(delta)

        //gameOverWindow.show(3230)
        //  reindeer1.setOrigin(Align.center)
        //  reindeer1.rotateBy(-0.5f)
        //  if (reindeer1.rotation > 0f) {
        //      reindeer1.moveBy(0f, 0.8f * MathUtils.sinDeg(reindeer1.rotation))
        //  } else {
        //      reindeer1.moveBy(0f, 2.5f * MathUtils.sinDeg(reindeer1.rotation))
        //  }
        //  if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
        //      reindeer1.rotateBy(50f)
        //  }
        //reindeer1Body.setTransform(reindeer1.x + 3f, reindeer1.y + 3f, reindeer1.rotation * MathUtils.degreesToRadians)

        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            enableDebugRenderer = !enableDebugRenderer
        }

        if (gameState == State.STARTING_PHASE) {
            if ((!lastTouched && Gdx.input.isTouched) || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
                gameState = State.RUNNING
                introductionHint.isVisible = false
                level.startingPhase = false
            }
            scoreTimer = 0f
        } else if (gameState == State.RUNNING) {
            scoreTimer += delta

            if (scoreTimer > 0.5f) {
                scoreTimer -= 0.5f
                setScore(score + 1)

            }
        }

        lastTouched = Gdx.input.isTouched

        if (gameState == State.STARTING_PHASE || gameState == State.RUNNING) {
            level.moveLevel(santa.lateralVelocity * delta)
            level.update(delta)
            world.step(1 / 60f, 8, 3)
        }
        if (gameState == State.RUNNING) {
            santa.update(delta)
        }
    }

    override fun render(delta: Float) {
        update(delta)

        //clearScreen(111 / 255f, 76 / 255f, 76 / 255f)
        clearScreen(189 / 255f, 99 / 255f, 64 / 255f)

        worldStage.draw()
        uiStage.draw()

        if (enableDebugRenderer) {
            debugRenderer.render(world, worldStage.camera.combined)
        }
        super.render(delta)
    }
}