# YoTa
YoTa是一个基于XMPP协议的活动类社交应用,通过该app用户可以方便浏览和参与各种有趣的活动，另外，它还具备即时通讯功能，随时可以与好友进行聊天和分享活动信息，在活动群当中还可以和各种女神进行互动哦(^_^),心动不如行动，赶紧去下载使用吧！

技术架构：
      android端：XMPP+Volley
       服务器端：springmvc+hibernate
       开发环境：Andriod Studio 1.0, MyEclipse 2014
      web服务器：tomcat 7.0
 即时通讯服务器：openfire 3.9
         数据库：mysql 5.0
该项目只包含服务器端代码，以下是部署说明：
1.clone这个项目下来后，打开document文件夹，将yota.sql文件部署到MySql数据库当中
2.将YoTa.war拷贝到tomcat的webapp下，修改下配置文件\WEB-INF\classes\applicationContext.xml，更改连接数据库的username和password即可完成部署！
