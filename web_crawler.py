import time
# from bs4 import BeautifulSoup  # 网页解析，获取数据
# import re  # 正则表达式，进行文字匹配`
import urllib.request, urllib.error
import selenium
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver import ActionChains



program_file_path = "C:\\Users\86158\Desktop\Java&Python_web_crawler_12306"  #revise it to THIS PROGRAM'S PATH OF YOUR COMPUTER.



'''
get website
'''
browser = webdriver.Edge(program_file_path+'\msedgedriver.exe')
browser.get('https://www.12306.cn/index')
# browser.add_argument("--disable-blink-features=AutomationControlled")



'''
from front-end input user's choices.
'''

with open(program_file_path+'\web_crawler_user\config.txt', 'r') as file:
    r=file.readlines()
 #   print(type(r[2]))


before_start = browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[1]/div/div/input[2]').clear()

start= browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[1]/div/div/input[2]').send_keys(r[0])  #r[0] is start place.

after_start = browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[1]/div/div/input[2]').send_keys(Keys.ENTER)

before_end = browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[2]/div/div/input[2]').clear()

end = browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[2]/div/div/input[2]').send_keys(r[1])  #r[1] is destination.

after_end = browser.find_element(By.XPATH,
                               '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                               '/div[1]/div[1]/div[1]/div[2]/div/div/input[2]').send_keys(Keys.ENTER)

before_date = browser.find_element(By.XPATH,
                            '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                            '/div[1]/div[1]/div[2]/div/div/input').clear()

date = browser.find_element(By.XPATH,
                            '/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]'
                            '/div[1]/div[1]/div[2]/div/div/input').send_keys(r[2]+'-'+r[3]+'-'+r[4])  #r[2][3][4] is year/month/date respectively.

'''
button automatic click
'''
click = ActionChains(browser)
click_element = browser.find_element(By.XPATH,
                                     '/html/body/div[1]/div[3]/div[2]/div/div[1]'
                                     '/div/div[2]/div[1]/div[1]/div[4]/a')
time.sleep(0.5)
click.click(click_element)
click.perform()
time.sleep(0.5)

browser.switch_to.window(browser.window_handles[1])

'''
Interestingly, web pages load much slower than expected.
If the waiting time is too small, the loading fails, and it is set to wait 3s before no error is reported
'''
time.sleep(3)

whole = browser.find_element(By.XPATH, '/html/body/div[2]/div[8]/div[9]/table/tbody')

text = whole.text

print(text)

with open(program_file_path+'\data.txt', 'w') as f:
    f.write(text)

'''
next, click to buy tickets.
'''
click2buy_elements = browser.find_elements(By.CLASS_NAME, "no-br")
click2buy = ActionChains(browser)
time.sleep(0.5)
click2buy.click(click2buy_elements[0])
click2buy.perform()

time.sleep(3)

before_login_user = browser.find_element(By.XPATH, '/html/body/div[2]/div[34]/div[2]'
                                            '/div[1]/div[1]/'
                                            'div[1]/input').clear()

login_user = browser.find_element(By.XPATH, '/html/body/div[2]/div[34]/div[2]'
                                            '/div[1]/div[1]/'
                                            'div[1]/input').send_keys("15801560690")

before_login_password = browser.find_element(By.XPATH, '/html/body/div[2]/div[34]'
                                                '/div[2]/div[1]/div[1]'
                                                '/div[2]/input').clear()

login_password = browser.find_element(By.XPATH, '/html/body/div[2]/div[34]'
                                                '/div[2]/div[1]/div[1]'
                                                '/div[2]/input').send_keys("caiyuyang138")


time.sleep(2)
click2login = ActionChains(browser)
click2login.click(browser.find_element(By.XPATH, '/html/body/div[2]/div[34]/div[2]'
                                                 '/div[1]/div[1]/div[4]/a'))
click2login.perform()
time.sleep(3)
slide_btn = browser.find_element(By.XPATH, '/html/body/div[2]/div[36]/div[2]/div[2]'
                                           '/div/div/div[2]/div/div[1]/span')


'''
def get_track(distance):
    
    获得移动轨迹，模仿人的滑动行为，先匀加速后匀减速匀变速运动基本公式:
    v0 = 0
    t = 0.2
    tracks = []
    current = 0
    mid = distance*7/8
    distance += 10
    while current <= distance:
        t = random.randint(1, 4)/10
        if current < mid:
            #加速度越小，单位时间的位移越小，模拟的轨迹就越多越详细
            a = 1000 #加速运动
        else:
            a = -800 #减速运动
        #0.2s时间的位移
        s = v0*t + 0.5*a*(t**2)
        #当前位置
        current+=s
        #添加到轨迹列表
        tracks.append(round(s))
        #当前速度，作为下次的初速度
        v0 = v0+a*t
    #反着滑动到大概准确位置
    for i in range(4):
        tracks.append(-random.randint(1, 3))
    return tracks

actionChains = ActionChains(browser)
actionChains.click_and_hold(slide_btn).perform()
tracks = get_track(388)
for track in tracks:
    actionChains.move_by_offset(xoffset=track, yoffset=0).perform()
actionChains.release(slide_btn).perform()

actionChains = ActionChains(browser)
actionChains.drag_and_drop_by_offset(slide_btn, 180, 0)
actionChains.perform()
actionChains.drag_and_drop_by_offset(slide_btn, 388, 0)
actionChains.perform()
    
'''