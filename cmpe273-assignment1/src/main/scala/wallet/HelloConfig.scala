package wallet

import org.springframework.http.HttpRequest

import com.fasterxml.jackson.core.JsonGenerationException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonGenerator
import org.springframework.web.context.request._
import javax.xml.ws.Response
import javax.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import scala.beans.{BeanProperty, BooleanBeanProperty}
import scala.collection.JavaConversions._
import java.io.Serializable
import java.util.Date
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.sun.istack.internal.NotNull
import java.util.ArrayList
import java.util.Date
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Set
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid
import org.hibernate.validator.constraints.NotEmpty
import java.text.SimpleDateFormat
import scala.collection.JavaConversions._
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonGenerator
import org.springframework.stereotype.Component
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import JsonDateSerializer._
import java.util.TimeZone

/*class UserRestURIConstants{


  val CREATE_USER = "/users"

  val VIEW_USER = "/users/{user_id}"

  val UPDATE_USER = "/users/{user_id}"

  val CREATE_IDCARD = "/users/{user_id}/idcards"

  val LIST_AllIDCards = "/users/{user_id}/idcards"

  val DELETE_IDCARD = "/users/{user_id}/idcards/{card_id}"

  val CREATE_WebLogin = "/users/{user_id}/weblogins"

  val LIST_AllWebsiteLogins = "/users/{user_id}/weblogins"

  val DELETE_WebLogin = "/users/{user_id}/weblogins/{login_id}"

  val CREATE_BankAccount = "/users/{user_id}/bankaccounts"

  val LIST_AllBankAccounts = "/users/{user_id}/bankaccounts"

  val DELETE_BankAccount = "/users/{user_id}/bankaccounts/{ba_id}"

}*/

object JsonDateSerializer {

  private val ft = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
}

@Component
class JsonDateSerializer extends JsonSerializer[Date] {

  override def serialize(date: Date, gen: JsonGenerator, provider: SerializerProvider) {
    val formattedDate = ft.format(date)
    gen.writeString(formattedDate)
  }
}


@SerialVersionUID(-7788619177798333712L)
class IDCard extends Serializable {

 var DATE_FORMAT: String = "MM-dd-yyyy"

  var sdf: SimpleDateFormat = new SimpleDateFormat(DATE_FORMAT)

 // val ft = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a zzz")
  private var card_id: Int = _

  @BeanProperty
  @NotEmpty
  var card_name: String = _

  @BeanProperty
  @NotEmpty
  var card_number: String = _

  val dateFormat:SimpleDateFormat   = new SimpleDateFormat("yyyy-MM-dd ")
  @BeanProperty
  var expiration_date: String= dateFormat.format(new Date)

  def getCard_id(): String = "c-" + card_id

  def setCard_id(card_id: String) {
    val id = java.lang.Integer.parseInt(card_id.substring(2))
    this.card_id = id
}

   
}

class WebLogin extends Serializable {

  private var login_id: Int = _

  @BeanProperty
  @NotEmpty
  var url: String = _

  @BeanProperty
  @NotEmpty
  var login: String = _

  @BeanProperty
  @NotEmpty
  var password: String = _

  def getLogin_id(): String = "l-" + login_id

  def setLogin_id(login_id: String) {
    val id = java.lang.Integer.parseInt(login_id.substring(2))
    this.login_id = id
  }
}

@SerialVersionUID(-7788619177798333712L)
class BankAccount extends Serializable {

  private var ba_id: Int = _

  @BeanProperty
  var account_name: String = _

  @BeanProperty
  @NotEmpty
  var routing_number: String = _

  @BeanProperty
  @NotEmpty
  var account_number: String = _

  def getBa_id(): String = "b-" + ba_id

  def setBa_id(ba_id: String) {
    val id = java.lang.Integer.parseInt(ba_id.substring(2))
    this.ba_id = id
  }
}


@SerialVersionUID(-7788619177798333712L)
class User extends Serializable {
  //@Context Request request,
 // @Context UriInfo ui 
  private var user_id: Int = _


  @BeanProperty
  @NotEmpty
  var email: String = _

  @BeanProperty
  @NotEmpty
  var password: String = _

  @BeanProperty
  var name: String = _

  private var created_at: Date = _

  private var updated_at: Date = _



  def getuser_id(): String = "u-" + user_id

  def setuser_id(user_id: String) {
    val id = java.lang.Integer.parseInt(user_id.substring(2))
    this.user_id = id
  }

  @JsonSerialize(using = classOf[JsonDateSerializer])
  def getcreated_at(): Date = created_at

  def setcreated_at(created_at: Date) {
    this.created_at = created_at
  }

  
@JsonSerialize(using = classOf[JsonDateSerializer])
  def getupdated_at(): Date = updated_at

  def setupdated_at(updated_at: Date) {
    this.updated_at = updated_at
  }
}

/**
 * This config class will trigger Spring @annotation scanning and auto configure Spring context.
 *
 * @author Rucha Brahmankar
 * @since 1.0
 */

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
class HelloConfig {

 

   private val logger = LoggerFactory.getLogger(classOf[HelloConfig])

   // val SERVER_URI = Array("api/v1")
	 var userData: Map[String, User] = new HashMap[String, User]()

	 var IDCardData: Map[String, IDCard] = new HashMap[String, IDCard]()

  var WebLoginData: Map[String, WebLogin] = new HashMap[String, WebLogin]()

  var BankAccountData: Map[String, BankAccount] = new HashMap[String, BankAccount]()

 
  var userid: Int = 0
  var cardid: Int = 0

  var loginid: Int = 0

  var baid: Int = 0

  //Create User

  @RequestMapping(value = Array("api/v1/users"), method =Array(RequestMethod.POST))
  @ResponseBody
  //@ResponseStatus(value=HttpStatus.POST, reason="User created") 
  def createUser(@RequestBody @Valid user: User, response: HttpServletResponse): User = {
    println("I am here")
    logger.info("Start createUser.")
    user.setcreated_at(new Date())
    user.setupdated_at(new Date())
    if (user.getuser_id == "u-0") {
      userid = userid + 1
      user.setuser_id("u-" + userid)
    }
    userData.put(user.getuser_id, user)
    response.setStatus(HttpServletResponse.SC_CREATED)
    user
  }


//GetUSER
val request: WebRequest = null
   var gen: JsonGenerator = _
  @JsonSerialize(using = classOf[JsonDateSerializer])
  @RequestMapping(value = Array("api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getUser(@PathVariable("user_id") user_id: String,request: WebRequest): User = {
    logger.info("Start getUser. user_id=" + user_id)
 
 logger.info("Start getUser. user_id=" + user_id)
    val user1 = userData.get(user_id)
    val lastModified = user1.getupdated_at.getTime
    if (request.checkNotModified(lastModified)) {
      return null
    }
    userData.get(user_id)
  }


  //val UPDATE_USER = "/users/{user_id}"

	@RequestMapping(value =Array("api/v1/users/{user_id}"), method = Array(RequestMethod.PUT))
  @ResponseBody
  def updateUser(@PathVariable("user_id") user_id: String, @RequestBody user: User,response: HttpServletResponse): User = {
    logger.info("Start getUser. user_id=" + user_id)
    val user1 = userData.get(user_id)
    //println(user1)
    user1.setName(user.getName)
    user1.setEmail(user.getEmail)
    user1.setPassword(user.getPassword)
    user1.setupdated_at(new Date())
    response.setStatus(HttpServletResponse.SC_CREATED)
    user1
  }
  // val CREATE_IDCARD = "/users/{user_id}/idcards"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createIDCard(@RequestBody @Valid IDCard: IDCard,response: HttpServletResponse): IDCard = {
    println("I am in card")
    logger.info("Start createIDCard.")
    if (IDCard.getCard_id == "c-0") {
      cardid = cardid + 1
      IDCard.setCard_id("u-" + cardid)
    }
    IDCardData.put(IDCard.getCard_id, IDCard)
    response.setStatus(HttpServletResponse.SC_CREATED)
    IDCard
  }

  //val LIST_AllIDCards = "/users/{user_id}/idcards"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getAllIDCard(): List[IDCard] = {
    logger.info("Start getAllIDCard.")
    val IDCards = new ArrayList[IDCard]()
    val cardIdKeys = IDCardData.keySet
    for (i <- cardIdKeys) {
      IDCards.add(IDCardData.get(i))
    }
    IDCards
  }

  //val DELETE_IDCARD = "/users/{user_id}/idcards/{card_id}"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteIDCard(@PathVariable("card_id") card_id: String,response: HttpServletResponse): IDCard = {
    logger.info("Start deleteIDCard.")
     if (IDCardData.get(card_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val card_id1 = IDCardData.get(card_id)
    IDCardData.remove(card_id)
    card_id1
  }

//val CREATE_WebLogin = "/users/{user_id}/weblogins"

  @RequestMapping(value = Array("api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createWebLogin(@RequestBody @Valid WebLogin: WebLogin,response: HttpServletResponse): WebLogin = {
    logger.info("Start createWebLogin.")
    if (WebLogin.getLogin_id == "l-0") {
      loginid = loginid + 1
      WebLogin.setLogin_id("u-" + loginid)
    }
    WebLoginData.put(WebLogin.getLogin_id, WebLogin)
    response.setStatus(HttpServletResponse.SC_CREATED)
    WebLogin
  }

//val LIST_AllWebsiteLogins = "/users/{user_id}/weblogins"

  @RequestMapping(value = Array("api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getWebsiteLogins(): List[WebLogin] = {
    logger.info("Start getWebsiteLogins.")
    val WebLogins = new ArrayList[WebLogin]()
    val login_idKeys = WebLoginData.keySet
    for (i <- login_idKeys) {
      WebLogins.add(WebLoginData.get(i))
    }
    WebLogins
  }

//val DELETE_WebLogin = "/users/{user_id}/weblogins/{login_id}"

  @RequestMapping(value =  Array("api/v1/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteWebLogin(@PathVariable("login_id") login_id: String,response: HttpServletResponse): WebLogin = {
    logger.info("Start deleteWebLogin.")
    if (WebLoginData.get(login_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val login_id1 = WebLoginData.get(login_id)
    WebLoginData.remove(login_id)
    login_id1
  }

//val CREATE_BankAccount = "/users/{user_id}/bankaccounts"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createBankAccount(@RequestBody @Valid BankAccount: BankAccount,response: HttpServletResponse): BankAccount = {
    logger.info("Start createBankAccount.")
    if (BankAccount.getBa_id == "b-0") {
      baid = baid + 1
      BankAccount.setBa_id("b-" + baid)
    }
    BankAccountData.put(BankAccount.getBa_id, BankAccount)
     response.setStatus(HttpServletResponse.SC_CREATED)
    BankAccount
  }

//val LIST_AllBankAccounts = "/users/{user_id}/bankaccounts"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getAllBankAccounts(): List[BankAccount] = {
    logger.info("Start getAllBankAccounts.")
    val BankAccounts = new ArrayList[BankAccount]()
    val ba_idKeys = BankAccountData.keySet
    for (i <- ba_idKeys) {
      BankAccounts.add(BankAccountData.get(i))
    }
    BankAccounts
  }

  //val DELETE_BankAccount = "/users/{user_id}/bankaccounts/{ba_id}"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteBankAccount(@PathVariable("ba_id") ba_id: String,response: HttpServletResponse): BankAccount = {
    logger.info("Start deleteBankAccount.")
    if (BankAccountData.get(ba_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val ba_id1 = BankAccountData.get(ba_id)
    BankAccountData.remove(ba_id)
    ba_id1
  }
}




  





