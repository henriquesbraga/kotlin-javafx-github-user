package com.henriquesbraga.githubapiconsumer.controller

import com.henriquesbraga.githubapiconsumer.api.ApiInterface
import com.henriquesbraga.githubapiconsumer.utils.AlertPane
import com.henriquesbraga.githubapiconsumer.model.UserRepo
import com.henriquesbraga.githubapiconsumer.api.ApiClient
import com.henriquesbraga.githubapiconsumer.model.User

import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.ImageView
import javafx.application.Platform
import javafx.fxml.Initializable
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.control.*
import kotlinx.coroutines.*
import javafx.fxml.FXML
import java.awt.Desktop
import java.net.URL
import java.util.*


class HelloController: Initializable {

    @FXML private lateinit var usrInput: TextField
    @FXML private lateinit var requestButton: Button
    @FXML private lateinit var usrImg: ImageView
    @FXML private lateinit var usrName: Label
    @FXML private lateinit var usrCompany: Label
    @FXML private lateinit var usrBio:Label
    @FXML private lateinit var usrFollowers: Label
    @FXML private lateinit var usrRepositories: Label
    @FXML private lateinit var repositoriesTable: TableView<UserRepo>
    @FXML private lateinit var repoNameColumn: TableColumn<UserRepo, String>
    @FXML private lateinit var repoUrlColumn: TableColumn<UserRepo, String>
    @FXML private lateinit var loadingPane: Pane

    private lateinit var apiInterface: ApiInterface

    override fun initialize(p0: URL?, p1: ResourceBundle?) {

        // Api Interface
        apiInterface = ApiClient.getApiClient()

        // Table columns
        repoNameColumn.cellValueFactory = PropertyValueFactory("name")
        repoUrlColumn.cellValueFactory = PropertyValueFactory("url")

        // Hide loading
        hideLoading()

        // On request button listener
        requestButton.setOnAction { getData(usrInput.text.toString()) }

        // On URL click listener
        repositoriesTable.setOnMousePressed {
            if (it.clickCount == 2) openRepositoryWebPage(repositoriesTable.selectionModel.selectedItem.url)
        }

        usrInput.text = "henriquesbraga"
    }


    private fun loading(value: Boolean) {
        loadingPane.isVisible = value
        usrFollowers.isVisible = !value
        requestButton.isVisible = !value
        usrRepositories.isVisible = !value
    }

    private fun showLoading() = loading(true)
    private fun hideLoading() = loading(false)

    private fun openRepositoryWebPage(url: String) {
        try {
            Desktop.getDesktop().browse(URL(url).toURI())
        }
        catch (e: Exception) {
            AlertPane(Alert.AlertType.ERROR,
                "Error", null, "Error while opening URL")
                .makeAlert { null }
        }
    }

    private fun getData(usr: String) {

        showLoading()
        CoroutineScope(Dispatchers.Default).launch {
            val response = apiInterface.getUser(usr)
            try {
                if (response.isSuccessful) {
                    bindData(response = response.body())
                    getRepos(usr)
                }
                else if (response.code() == 404){

                    Platform.runLater {
                        AlertPane(Alert.AlertType.ERROR,
                            "Not Found", null, "User not found!")
                            .makeAlert { hideLoading() }

                    }
                }
            }
            catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    private suspend fun getRepos(usr: String){

        val response = apiInterface.getRepos(usr)
        try {
            if (response.isSuccessful) {
                bindDataToTable(response.body())
                hideLoading()
            }

        } catch (e: Exception) {
            println(e.toString())
        }
    }

    private fun bindData(response: User?) {
        Platform.runLater {
            usrName.text = response?.username
            usrCompany.text = response?.company
            usrBio.text = response?.bio
            usrFollowers.text = "Followers: ${response?.usrFollowers.toString()}"
            usrRepositories.text = "Repositories: ${response?.usrRepositories.toString()}"
            usrImg.image = Image(response?.avatarUrl)
        }
    }

    private fun bindDataToTable(response: List<UserRepo>?) {
        if (repositoriesTable.items != null) {
            repositoriesTable.items.clear()
        }
        repositoriesTable.items.addAll(response!!)
    }

}