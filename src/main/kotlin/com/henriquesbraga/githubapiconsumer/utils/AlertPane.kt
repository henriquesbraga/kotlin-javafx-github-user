package com.henriquesbraga.githubapiconsumer.utils

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType


class AlertPane (private val type: Alert.AlertType, private val title: String, private val headerText: String?, private val contentText: String) {

    fun makeAlert(callback: () -> Unit) {
        val alert = Alert(type)
        alert.title = title
        alert.headerText = headerText
        alert.contentText = contentText
        alert.isResizable = true
        val result = alert.showAndWait()
        when (result.orElse(ButtonType.CANCEL)) {
            ButtonType.OK -> callback.invoke()
        }
    }
}