package com.henriquesbraga.githubapiconsumer

import com.henriquesbraga.githubapiconsumer.controller.Controller
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.exitProcess


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Controller::class.java.getResource("/View.fxml"))
        val scene = Scene(fxmlLoader.load(), 500.0, 450.0)
        stage.title = "GitHub API!"
        stage.minWidth = 500.0
        stage.minHeight = 450.0
        stage.scene = scene
        stage.show()
        stage.setOnCloseRequest { Platform.exit(); exitProcess(0) }
    }

    fun main() = launch(HelloApplication::class.java)
}

