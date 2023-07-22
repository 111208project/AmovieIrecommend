from flask import Flask #載入Flask
# import mysql.connector  

app=Flask(__name__) #建立 Application 物件


# # 設置MySQL連線
# db = mysql.connector.connect(
#     host="your_mysql_host",
#     user="root",
#     password="root",
#     database="112208db"
# )

# 處理API請求
@app.route('/your/api/endpoint', methods=['POST'])
def handle_request():
    data = request.json  # 從Android應用程式接收的資料
    # 在這裡執行資料庫操作
    cursor = db.cursor()
    # 執行MySQL查詢等操作
    # ...

    # 回傳回應給Android應用程式
    response = {'message': 'success'}
    return response

if __name__ == '__main__':
    app.run()
