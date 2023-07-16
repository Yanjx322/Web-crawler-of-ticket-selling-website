# Web-crawler-of-ticket-selling-website
This program is a web-crawler which can gather information from a train ticket selling website and make it more visualized.

    This is a initially designed web-crawler using the language of Java and Python. We use it to collect information from www.12306.cn, which is a train ticket's online buying system. In order to accomplish our task:

    We first need to build a front-end program to display the interface or the screen for users to operate. A well-designed interface may include plenty of texts for users to see: train's start places, destination, train start time, arriving time, time duration...and so on. There will be buttons for users to click. These all suggest that the front-end program's design needs a high efficiency, so we choose Java. (Program File：web_crawler_user)

    Second, a back-end program was built to browse and collect information form webpage: www.12306.cn (we also decided to add more functions to it, such as purchasing for tickets, unfortunately we did not have time to do so.). In this program, a simple understanding of its function is: to enter our identity information to the website to log in, then get the user-entered texts and automatically type it to the website. Next, after the website listed all of the ticket information, crawl all of it to a folder. (Program: web_crawler.py)

    Finally, but more important, is the connection of the two programs above. In the front-end Java program, after users choosed the text, this text information needs to be sent to the back-end python program in order to be put into website. Also, the crawled ticket information needs to be sent back into the front-end program page for viewing.



#REQUIREMENT:

1. IntelliJ Idea:
javax.swing;
java.awt;
java.awt.event;
java.io;
static java.lang.Thread.sleep;

2. Pycharm:
time
bs4
urllib
selenium

*3.msedgedriver.exe: the exe in the folder is just an example, since everyone's edge version are not the same, you should first check your edge version and download msedgedriver of your own, the specific instruction is listed here: https://blog.csdn.net/weixin_50233989/article/details/113242332

#Revision:
If you want to run this code, you need to revise this program-file-path to that of your devices: 
1. web_crawler.py: #line 14, program_file_path = '' (path to this program file).
2. web_crawler_user\src\com\sxt: #line 16, program_file_path =''. #line 17: python_interpreter_path ='' (path of your python interpreter)

After that,  you just need to run 'web_crawler_user\src\com\sxt' program so that you can enter the user's page.




#statement：This program is my friend's and my first-time-design, just for interest and curiosity, therefore it may not be professional, which means there may be flaws and imperfections that make the robustness turn down. However, the program can work now and we are now looking forward to more and more functions adding to it, such as purchasing for tickets and predicting ticket's trend...Hope one day we will figure it out.
