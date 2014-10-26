package wallet

import org.springframework.http.HttpRequest
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
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
import collection.JavaConverters._
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
import java.text.DateFormat
import org.springframework.data.annotation.Id
import java.util.List
import org.springframework.beans.factory.annotation.Autowired
import java.util.Arrays
import com.mongodb.casbah.Imports._
import org.springframework.http.HttpEntity
import com.google.gson.Gson





class lookup {

  @BeanProperty
  var message: String = _

  @BeanProperty
  var change_date: String = _

  @BeanProperty
  var office_code: String = _

  @BeanProperty
  var record_type_code: String = _

  @BeanProperty
  var new_routing_number: String = _

  @BeanProperty
  var rn: String = _

  @BeanProperty
  var state: String = _

  @BeanProperty
  var address: String = _

  @BeanProperty
  var telephone: String = _

  @BeanProperty
  var data_view_code: String = _

  @BeanProperty
  var code: String = _

  @BeanProperty
  var customer_name: String = _

  @BeanProperty
  var city: String = _

  @BeanProperty
  var routing_number: String = _

  @BeanProperty
  var institution_status_code: String = _

  @BeanProperty
  var zip: String = _
}


object JsonDateSerializer {

  private val ft = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
}

@Component
class JsonDateSerializer extends JsonSerializer[Date] {
  //private val ft = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z")

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
 @Id
  private var card_id: String = _

  @BeanProperty
  @NotEmpty
  var card_name: String = _

  @BeanProperty
  @NotEmpty
  var card_number: String = _

  val dateFormat:SimpleDateFormat   = new SimpleDateFormat("yyyy-MM-dd ")
  @BeanProperty
  var expiration_date: String= dateFormat.format(new Date)

  def getCard_id(): String =  card_id

  def setCard_id(card_id: String) {
    //val id = java.lang.Integer.parseInt(card_id.substring(2))
    this.card_id = card_id
}

   
}

class WebLogin extends Serializable {

@Id
  private var login_id: String = _

  @BeanProperty
  @NotEmpty
  var url: String = _

  @BeanProperty
  @NotEmpty
  var login: String = _

  @BeanProperty
  @NotEmpty
  var password: String = _

  def getLogin_id(): String = login_id

  def setLogin_id(login_id: String) {
   // val id = java.lang.Integer.parseInt(login_id.substring(2))
    this.login_id = login_id
  }
}

@SerialVersionUID(-7788619177798333712L)
class BankAccount extends Serializable {
@Id
  private var ba_id: String = _

  @BeanProperty
  var account_name: String = _

  @BeanProperty
  @NotEmpty
  var routing_number: String = _

  @BeanProperty
  @NotEmpty
  var account_number: String = _

  def getBa_id(): String =  ba_id

  def setBa_id(ba_id: String) {
    //val id = java.lang.Integer.parseInt(ba_id.substring(2))
    this.ba_id = ba_id
  }
}


@SerialVersionUID(-7788619177798333712L)
class User extends Serializable {

val dateFormat:DateFormat   = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
val updDateFormat:DateFormat   = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
updDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
  
 @Id
  private var user_id: String = _


  @BeanProperty
  @NotEmpty
  var email: String = _

  @BeanProperty
  @NotEmpty
  var password: String = _

  @BeanProperty
  var name: String = _

  var created_at: String = dateFormat.format(new Date)

  var updated_at: String = updDateFormat.format(new Date)



  def getuser_id(): String = user_id

  def setuser_id(user_id: String) {
    //val id = java.lang.Integer.parseInt(user_id.substring(2))
    //this.user_id = id
    this.user_id = user_id
  }

 /* @JsonSerialize(using = classOf[JsonDateSerializer])
  def getcreated_at(): Date = created_at

  def setcreated_at(created_at: Date) {
    this.created_at = created_at
  }

  
@JsonSerialize(using = classOf[JsonDateSerializer])
  def getupdated_at(): Date = updated_at

  def setupdated_at(updated_at: Date) {
    this.updated_at = updated_at
  }*/
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
class HelloConfig{

 

  private val logger = LoggerFactory.getLogger(classOf[HelloConfig])

  val uri = MongoClientURI("mongodb://cmpe273:cmpe273@ds047030.mongolab.com:47030/digitalwallet")
  //val mongo = MongoConnection(uri)
  //val db = mongo(uri.digitalwallet)
  //db.authenticate(uri.username, uri.password.foldLeft("")(_ + _.toString))
  //val collection = db("something")

    //adding mongodb part

  // val mongoClient = MongoConnection("localhost", 27017)
   val mongoClient = MongoClient(uri)
   val db = mongoClient("digitalwallet") //db name is DigitalWallet
   db.authenticate("cmpe273", "cmpe273")

   val coll = db("UserRepository")   //collection for User
    val coll1 = db("CardRepository")  //colle
    val coll2 = db("WebRepository")
    val coll3 = db("BankRepository")

  var userid: String = _ 
  var cardid: String = _
  var loginid: String = _
  var baid: String = _
 

    //hashmpas

    //var userData: Map[String, User] = new HashMap[String, User]()
	 //var IDCardData: Map[String, IDCard] = new HashMap[String, IDCard]()
    //var WebLoginData: Map[String, WebLogin] = new HashMap[String, WebLogin]()
   // var BankAccountData: Map[String, BankAccount] = new HashMap[String, BankAccount]()

 
 
  //Create User

  @RequestMapping(value = Array("api/v1/users"), method =Array(RequestMethod.POST))
  @ResponseBody
  //@ResponseStatus(value=HttpStatus.POST, reason="User created") 
  def createUser(@RequestBody @Valid user: User, response: HttpServletResponse) = {
   // println("I am here")
    logger.info("Start createUser.")
  //  user.setcreated_at(new Date())
    //user.setupdated_at(new Date())
    //if (user.getuser_id == "u-0") {
      //instead of setting by adding 1.
       //userid = userid + 1

       //do this
       
       userid = new Date().getTime.toString
      user.setuser_id("u-" + userid)
    //}
     val a  = MongoDBObject("user_id" -> user.getuser_id,
                           "email"-> user.getEmail,
                            "password"-> user.getPassword,
                           "name"-> user.getName,
                           "created_at"-> user.created_at,
                           "updated_at"-> user.updated_at)
      //coll.insert(a)
      
   // userData.put(user.getuser_id, user)
    response.setStatus(HttpServletResponse.SC_CREATED)
    //userData.save(user)
    
    coll.insert(a)
    val dbObject = MongoDBObject("user_id" -> user.getuser_id)
    val user1 = coll.findOne(dbObject,MongoDBObject("_id"->0)).get
    user1
  }


//GetUSER
val request: WebRequest = null
   var gen: JsonGenerator = _
  @JsonSerialize(using = classOf[JsonDateSerializer])
  @RequestMapping(value = Array("api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getUser(@PathVariable("user_id") user_id: String,request: WebRequest) = {
    logger.info("Start getUser. user_id=" + user_id)
 
// HashMap Part
  /* val user1 = userData.get(user_id)
    val lastModified = user1.getupdated_at.getTime
   if (request.checkNotModified(lastModified)) {
     return null
     userData.get(user_id)


   val field = MongoDBObject("updated_at" -> 1, "_id" -> 0)
   val userObject = MongoFactory.userCollection.findOne(query, field).get
   val updDate = userObject.get("updated_at").asInstanceOf[String]
   var date: Date = dateFormat.parse(updDate)
   var longDate = date.getTime()
    }*/
    val updDateFormat:DateFormat   = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    updDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
  
   //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
     //MongoDB Part 
     val dbObject = MongoDBObject("user_id" -> user_id)
     val field = MongoDBObject("_id" -> 0)
     val user1 = coll.findOne(dbObject,field).get
     println(user1)
    
    //conditional get
   val updDate = user1.get("updated_at").asInstanceOf[String]
   var date: Date = updDateFormat.parse(updDate)
   var lastModified: Long = date.getTime()

   // val lastModified:Long = user1.updated_at
    if (request.checkNotModified(lastModified)) {
    null
   }
     user1
  }


  //val UPDATE_USER = "/users/{user_id}"

	@RequestMapping(value =Array("api/v1/users/{user_id}"), method = Array(RequestMethod.PUT))
  @ResponseBody
  def updateUser(@PathVariable("user_id") user_id: String, @RequestBody user: User,response: HttpServletResponse) = {
    logger.info("Start getUser. user_id=" + user_id)

    //HashMap Part
   /* val user1 = userData.get(user_id)
    //println(user1)
    user1.setName(user.getName)
    user1.setEmail(user.getEmail)
    user1.setPassword(user.getPassword)

    user1.setupdated_at(new Date())
       response.setStatus(HttpServletResponse.SC_CREATED)
    
    user1*/

  //MongoDB Part
  val updDateFormat:DateFormat   = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
  updDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))

   val query = MongoDBObject("user_id" -> user_id)
   val update = $set("email" -> user.getEmail
      ,"name"->user.getName,"password"->user.getPassword
      ,"updated_at"->updDateFormat.format(new Date()) )
   val result = coll.update( query, update )
   val userObject= coll.findOne( query,MongoDBObject("_id"->0) ).get
  
   response.setStatus(HttpServletResponse.SC_CREATED)
   userObject

  }


  // val CREATE_IDCARD = "/users/{user_id}/idcards"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createIDCard(@RequestBody @Valid IDCard: IDCard, @PathVariable user_id: String,response: HttpServletResponse) = {
    println("I am in card")
    logger.info("Start createIDCard.")
    //if (IDCard.getCard_id == "c-0") {
       //cardid = cardid + 1
       cardid = new Date().getTime.toString
      IDCard.setCard_id("c-" + cardid)
   // }
   // IDCardData.put(IDCard.getCard_id, IDCard)
    //response.setStatus(HttpServletResponse.SC_CREATED)
   // IDCard

    //MongoDb Part

val a  = MongoDBObject( "user_id" -> user_id,
                        "card_id" -> IDCard.getCard_id,
                       "card_name"-> IDCard.card_name,
                       "card_number"-> IDCard.card_number,
                        "expiration_date"-> IDCard.expiration_date
                      ) 
    coll1.insert(a)
     response.setStatus(HttpServletResponse.SC_CREATED)
    val dbObject = MongoDBObject("card_id" -> IDCard.getCard_id)
    val idcard1 = coll1.findOne(dbObject,MongoDBObject("user_id"->0,"_id"->0)).get

    
    response.setStatus(HttpServletResponse.SC_CREATED)
   
   idcard1
    

  }

  //val LIST_AllIDCards = "/users/{user_id}/idcards"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getAllIDCard(@PathVariable user_id: String) = {
    logger.info("Start getAllIDCard.")

    //HashMap
   /* val IDCards = new ArrayList[IDCard]()
    val cardIdKeys = IDCardData.keySet
    for (i <- cardIdKeys) {
      IDCards.add(IDCardData.get(i))
    }
    IDCards*/

    //MongoDB part
   // User user = new User()


     val dbObject = MongoDBObject("user_id" -> user_id)
     val x =coll1.find(dbObject,MongoDBObject("user_id"->0,"_id"->0))
     x.toArray
     
    
  }

  //val DELETE_IDCARD = "/users/{user_id}/idcards/{card_id}"

  @RequestMapping(value = Array("api/v1/users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteIDCard(@PathVariable("card_id") card_id: String,response: HttpServletResponse) = {
    logger.info("Start deleteIDCard.")

    //Hashmap part
   /* if (IDCardData.get(card_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val card_id1 = IDCardData.get(card_id)
    IDCardData.remove(card_id)
    card_id1*/

    //MongoDB part
      val dbObject = MongoDBObject("card_id" -> card_id)
      val x = coll1.findOne(dbObject)
      println(x)

      if(x == None) 
          response.setStatus(HttpServletResponse.SC_NOT_FOUND)  
      else{
          val d = coll1.remove(dbObject)
          response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        }

      
     


  }

//val CREATE_WebLogin = "/users/{user_id}/weblogins"

  @RequestMapping(value = Array("api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createWebLogin(@RequestBody @Valid WebLogin: WebLogin,@PathVariable user_id: String,response: HttpServletResponse) = {
    logger.info("Start createWebLogin.")
    //if (WebLogin.getLogin_id == "l-0") {
      // loginid = loginid + 1

    loginid = new Date().getTime.toString
      WebLogin.setLogin_id("l-" + loginid)
   // }

    //HashMap Part
   /* WebLoginData.put(WebLogin.getLogin_id, WebLogin)
    response.setStatus(HttpServletResponse.SC_CREATED)
    WebLogin*/

    //MongoDB part

    val a  = MongoDBObject( "user_id" -> user_id,
                            "login_id" -> WebLogin.getLogin_id,
                            "url"-> WebLogin.url,
                            "login"-> WebLogin.login,
                            "password"-> WebLogin.password
                          ) 
    coll2.insert(a)
     response.setStatus(HttpServletResponse.SC_CREATED)
    val dbObject = MongoDBObject("login_id" -> WebLogin.getLogin_id)
    val login = coll2.findOne(dbObject,MongoDBObject("user_id"->0,"_id"->0)).get
    response.setStatus(HttpServletResponse.SC_CREATED)
    login
  }

//val LIST_AllWebsiteLogins = "/users/{user_id}/weblogins"

  @RequestMapping(value = Array("api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getWebsiteLogins(@PathVariable user_id: String)= {
    logger.info("Start getWebsiteLogins.")

    //hashmap part
   /* val WebLogins = new ArrayList[WebLogin]()
    val login_idKeys = WebLoginData.keySet
    for (i <- login_idKeys) {
      WebLogins.add(WebLoginData.get(i))
    }
    WebLogins*/

    //MongoDB part

   // val dbObject = MongoDBObject("user_id" -> user_id)
    // coll2.find(dbObject,MongoDBObject("user_id"->0)).toArray

     val dbObject = MongoDBObject("user_id" -> user_id)
    
    val x =coll2.find(dbObject,MongoDBObject("user_id"->0,"_id"->0))
    
     x.toArray


  }

//val DELETE_WebLogin = "/users/{user_id}/weblogins/{login_id}"

  @RequestMapping(value =  Array("api/v1/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteWebLogin(@PathVariable("login_id") login_id: String,@PathVariable user_id: String,response: HttpServletResponse)= {
    logger.info("Start deleteWebLogin.")
   /* if (WebLoginData.get(login_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val login_id1 = WebLoginData.get(login_id)
    WebLoginData.remove(login_id)
    login_id1*/

    //MongoDB

       val dbObject = MongoDBObject("login_id" -> login_id)
       val x = coll2.findOne(dbObject)
       if(x == None) 
          response.setStatus(HttpServletResponse.SC_NOT_FOUND)  
      else{
      val d = coll2.remove(dbObject)
      response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        }

  }

//val CREATE_BankAccount = "/users/{user_id}/bankaccounts"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createBankAccount(@RequestBody @Valid BankAccount: BankAccount,@PathVariable user_id: String,response: HttpServletResponse)= {
    logger.info("Start createBankAccount.")
   
//Hashmap part
   // if (BankAccount.getBa_id == "b-0") {
      //baid = baid + 1

      baid = new Date().getTime.toString
      BankAccount.setBa_id("b-" + baid)
    //}
   /*BankAccountData.put(BankAccount.getBa_id, BankAccount)
     response.setStatus(HttpServletResponse.SC_CREATED)
    BankAccount*/

//adding for part 2
   val headers  = new HttpHeaders()
    //headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN))
    val restTemplate = new RestTemplate()
    //val url = "http://www.routingnumbers.info/api/data.json?rn= " + 
     // BankAccount.getRouting_number

     val url = "http://www.routingnumbers.info/api/data.json?rn=" + BankAccount.getRouting_number

   // val result = restTemplate.getForObject(url, classOf[lookup])

      val requestEntity = new HttpEntity[String](headers)
       val entity = restTemplate.getForEntity(url, classOf[String],requestEntity,"customer_name")

    val result = entity.getBody()
    val data = new Gson().fromJson(result,classOf[lookup])


    if (data.getCode == "200") {
      BankAccount.setRouting_number(data.getRouting_number)
      BankAccount.setAccount_name(data.getCustomer_name)
    val a  = MongoDBObject(     "user_id" -> user_id,
                            "ba_id" -> BankAccount.getBa_id,
                            "account_name"-> BankAccount.account_name,
                            "routing_number"-> BankAccount.routing_number,
                            "account_number"-> BankAccount.account_number
                      ) 
    coll3.insert(a)
     response.setStatus(HttpServletResponse.SC_CREATED)
    val dbObject = MongoDBObject("ba_id" -> BankAccount.getBa_id)
    val bank_account = coll3.findOne(dbObject,MongoDBObject("user_id"->0,"_id"->0)).get
     response.setStatus(HttpServletResponse.SC_CREATED)
    bank_account

}
else 
{
   response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
    null
    //MongoDb
}

  }

//val LIST_AllBankAccounts = "/users/{user_id}/bankaccounts"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getAllBankAccounts(@PathVariable user_id: String)= {
    logger.info("Start getAllBankAccounts.")

   //HashMap
    /*val BankAccounts = new ArrayList[BankAccount]()
    val ba_idKeys = BankAccountData.keySet
    for (i <- ba_idKeys) {
      BankAccounts.add(BankAccountData.get(i))
    }
    BankAccounts*/

    //MongoDB Part
    val dbObject = MongoDBObject("user_id" -> user_id)
     val x =coll3.find(dbObject,MongoDBObject("user_id"->0,"_id"->0))
    
     x.toArray
     //coll3.find(dbObject,MongoDBObject("user_id"->0)).toArray
  
  }

  //val DELETE_BankAccount = "/users/{user_id}/bankaccounts/{ba_id}"

  @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteBankAccount(@PathVariable("ba_id") ba_id: String,@PathVariable user_id: String,response: HttpServletResponse)= {
    logger.info("Start deleteBankAccount.")
   
   //Hashmap
   /* if (BankAccountData.get(ba_id) == null) response.setStatus(HttpServletResponse.SC_NOT_FOUND) else response.setStatus(HttpServletResponse.SC_NO_CONTENT)
    val ba_id1 = BankAccountData.get(ba_id)
    BankAccountData.remove(ba_id)
    ba_id1*/

    //MongoDB
      val dbObject = MongoDBObject("ba_id" -> ba_id)
      
      val x = coll3.findOne(dbObject)
       if(x == None) 
          response.setStatus(HttpServletResponse.SC_NOT_FOUND)  
      else{
        val d = coll3.remove(dbObject)
        response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        }


  }
}




  





