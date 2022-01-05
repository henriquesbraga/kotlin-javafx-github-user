package com.henriquesbraga.githubapiconsumer

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.exitProcess


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("view.fxml"))
        val scene = Scene(fxmlLoader.load(), 500.0, 450.0)
        stage.title = "GitHub API!"
        //stage.isResizable = false
        stage.minWidth = 500.0
        stage.minHeight = 450.0
        stage.scene = scene
        stage.show()
        stage.setOnCloseRequest { Platform.exit(); exitProcess(0) }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

