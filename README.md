# OOPII 物件導向程式設計_課程專案  
## 主題:SDGS x 打字遊戲  
## 專案內容:  
1.   以傳統的打字遊戲作為基礎，改良成一款結合 SDGs 的打字遊戲；遊戲以 SDGs 的三大理念--**環境保護、社會進步、經濟成長**作為關卡之背景設定，製作一款具有教育意義的打字遊戲。    
2.   以Java為主要開發語言，實現程式邏輯以及GUI之呈現。 

## 說明各檔案之性質
1.    101.png、201.png、301.png、401.png、501.png:遊戲設計之背景圖
2.    Start.java:打字遊戲主程檔式，執行此檔可開始整個打字遊戲。
3.    Choose.java:遊戲內選擇模式的程式檔。
4.    G1.java、G2.java、G3.java:關卡一到關卡三的程式檔。

## 開發軟體工具安裝之順序步驟與設定
1.安裝JDK

> JDK下載網址(此為java SE 15版本 ): 
[https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html "JDK下載網址(此為java SE 15版本)")

- (1) 根據電腦下載相對版本的執行檔
- (2) 運行執行檔進行安裝


2.安裝X2GO

>X2GO下載網址(此為2020版本):
[https://wiki.x2go.org/doku.php](https://wiki.x2go.org/doku.php "X2GO下載網址(此為2020版本)")

- (1)根據電腦下載相對版本的執行檔
- (2)運行執行檔進行安裝


## 自行開發程式碼之安裝設定

1. 根據開發軟體工具安裝之順序步驟與設定，安裝X2GO
2.	下載安裝winSCP(此為傳檔工具）

	WinSCP下載網址： 
> [https://201708.mediafire.com/file/4v8evc6mbald34t/WinSCPPortable_5.19.2_azo.exe/file](https://201708.mediafire.com/file/4v8evc6mbald34t/WinSCPPortable_5.19.2_azo.exe/file "WinSCP下載網址")

 - 下載後運行執行檔進行安裝
 - 開啟WinSCP後新增X2GO的遠端主機，並輸入帳號密碼登入
 - 登入後將遊戲程式檔上傳至該主機的資料夾中


3.使用X2GO進行編譯與執行

- 在遊戲檔案所在之目錄下輸入`javac Start.java`，
- 編譯成功後輸入`java Start` 即可執行遊戲


## 遊戲規則與操作說明:

	遊戲內總共分為三個關卡，玩家須靠敲擊對應字母的鍵盤來消除單一字母或單字，
	消除單字可獲得額外的獎勵，
	每一關卡的挑戰模式及消除單字的獎勵皆不同；
	結束後玩家可選擇要再重來一次或者回到選擇模式介面。


###第一關-環境保護:
主題為淨灘，玩家要在**生命值歸零前挑戰更高的分數**。


####規則說明:

- 分數越高，單字移動的速度越快
- **當消除環境保護SDGs相關單字時，生命值+1**
- 正確消除字母分數可加100，反之打錯字則會扣分數100
- **每場有3點生命值，當生命值歸零，則遊戲結束**


###第二關- 社會進步:
主題為幫助人取得水果，玩家靠打字的方式獲得帶著字詞的水果，**在時間內挑戰更高的分數。**

####規則說明:
- 限時45秒，時間到則遊戲結束
- 正確消除字母分數可加100，反之打錯字則會扣分數100
- 當消除帶著社會進步SDGs相關單字的時，加時10秒

###第三關- 經濟成長:
主題為在辦公室奮鬥、拚經濟的上班族，玩家需要以打字的方式幫助上班族解決成堆帶著字詞的工作，**挑戰用更短的時間完成工作。**
####規則說明:
- 打錯字會加時間
- 當消除社會進步SDGs相關字時，**時間計時減5秒**
- **完成全部單字時遊戲結束**

## 如何測試安裝成功之步驟
#####測試JAVA開發環境是否安裝成功:
- 於命令提示字元(cmd)輸入
    `java -version` 
  即可查看是否成功安裝正確的JAVA開發環境版本








