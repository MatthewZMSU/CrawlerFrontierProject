package dao

import scala.concurrent.Future
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{JdbcProfile, MySQLProfile}
import slick.jdbc.MySQLProfile.api._
import models.Song
import play.api.Play

import javax.inject.Inject
import scala.concurrent.ExecutionContext



class SongsDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[MySQLProfile] {
//  val dbConfig = dbConfigProvider.get[MySQLProfile]
//  val db = Database.forConfig("MySQL")
  private val Songs = TableQuery[SongsTable]
  def all(): Future[Seq[Song]] = db.run(Songs.result)
  def insert(song: Song): Future[Unit] = db.run(Songs += song).map { _ => ()}
  private class SongsTable(tag: Tag) extends Table[Song](tag, "Songs") {
    def name = column[String]("NAME", O.PrimaryKey)
    def duration = column[Long]("DURATION")
    def * = (name, duration) <> (Song.tupled, Song.unapply)
  }
}


//class SongsDAO {
//
//}
