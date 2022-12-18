package com.tastyrhino

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen

class GameScreen : KtxScreen {

    private val worldWidth = 100f

    private val uiStage = Stage(ExtendViewport(1920f, 1080f))
    private val worldStage = Stage(ExtendViewport(worldWidth, worldWidth * 1080f / 1920f))

    private val world = World(Vector2(0f, -0.2f), false)
    private var enableDebugRenderer = true
    private val debugRenderer = Box2DDebugRenderer()

    private val santa = Santa(world).apply {
        worldStage.addActor(this)
    }
    private val level = Level(world).apply {
        worldStage.addActor(this)
    }

    private var difficulty = 0.0f
    private var levelTimer = 0f

    init {
        world.setContactListener(object : ContactListener {
            override fun beginContact(contact: Contact?) {

                ktx.log.debug { "Collision" }
            }

            override fun endContact(contact: Contact?) {
            }

            override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
            }

            override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
            }
        })
    }

    private fun update(delta: Float) {
        worldStage.act(delta)
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


        levelTimer += delta

        if (levelTimer > 1f) {
            if (difficulty < 1f) {
                difficulty += delta / 1f
            }
        } else {
            difficulty = 0f
        }


        santa.update(delta, difficulty)

        level.moveLevel(santa.lateralVelocity * delta)

        world.step(1 / 60f, 8, 3)
    }

    override fun render(delta: Float) {
        update(delta)

        //clearScreen(111 / 255f, 76 / 255f, 76 / 255f)
        clearScreen(189 / 255f, 99 / 255f, 64 / 255f)

        worldStage.draw()

        if (enableDebugRenderer) {
            debugRenderer.render(world, worldStage.camera.combined)
        }
        super.render(delta)
    }
}