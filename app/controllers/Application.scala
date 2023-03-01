package controllers

import dao.SongsDAO
import models.Song
import play.api.data.Form
import play.api.data.Forms._
import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext
import slick.jdbc.MySQLProfile.api._
import play.api.i18n.I18nSupport

@Singleton
class Application @Inject() (
  songsDao: SongsDAO,
  mcc: MessagesControllerComponents
)(implicit executionContext: ExecutionContext, val cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {  // May be to change

//  val db = Database.forConfig("MySQL")
  private val SongForm: Form[Song] = Form(
    mapping(
      "name" -> text(),
      "duration" -> longNumber
    )(Song.apply)(Song.unapply)
  )

  def index: Action[AnyContent] = Action.async { implicit request =>
    songsDao.all().map {
      songs => Ok(views.html.index(SongForm, songs))
    }
  }

  def insertSong(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val song: Song = SongForm.bindFromRequest().get
    songsDao.insert(song).map(_ => Redirect(routes.Application.index))
  }

}



